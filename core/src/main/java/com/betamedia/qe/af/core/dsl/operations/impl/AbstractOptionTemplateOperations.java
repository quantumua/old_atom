package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.qe.af.core.connectors.tp.FWTPConnector;
import com.betamedia.qe.af.core.dsl.operations.*;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.*;
import com.betamedia.tp.api.model.enums.OptionSubType;
import com.betamedia.tp.api.model.enums.OptionType;
import com.betamedia.tp.api.model.scheduling.OptionTemplateScheduler;
import com.betamedia.tp.api.model.scheduling.TradingCalendar;
import com.betamedia.tp.api.service.IOptionTemplateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * This class is designed to facilitate the execution of common operations related to option template operations.
 * It can be used as a "building block" when writing integration tests.
 * <p>
 * Created by mbelyaev on 3/24/17.
 *
 * @see OptionTemplate
 */
public abstract class AbstractOptionTemplateOperations<T extends EnvironmentDependent> implements OptionTemplateOperations<T> {
    private static final Logger logger = LogManager.getLogger(AbstractOptionTemplateOperations.class);

    @Autowired
    private FWTPConnector<T> tpConnector;
    @Autowired
    private AccountGroupOperations<T> accountGroupOperations;
    @Autowired
    private AssetOperations<T> assetOperations;
    @Autowired
    private TradingCalendarOperations<T> tradingCalendarOperations;
    @Autowired
    private SchedulerOperations<T> schedulerOperations;
    @Autowired
    private TagOperations<T> tagOperations;


    @Override
    public OptionTemplate get() {
        return null;
    }

    /**
     * Returns an template found by id
     */
    @Override
    public OptionTemplate get(String id) {
        OptionTemplate optionTemplate = tpConnector.readById(OptionTemplate.class, id);
        assertNotNull(optionTemplate, "OptionTemplate id=" + id + " is not available in GS");
        return optionTemplate;
    }

    /**
     * Creates a new option template with a given id, type and tag name.
     *
     * @param assetId used to get trading calendar
     * @param type    determines a type of default template to use
     * @param tagName used to determine tag id
     * @return created option template
     */
    @Override
    public OptionTemplate create(String assetId, OptionType type, TagOperations.TagName tagName) {
        AccountGroup accountGroup = accountGroupOperations.get();
        String tagId = tagOperations.get(tagName).get(0).getId();
        Asset asset = assetOperations.get(assetId);
        TradingCalendar tradingCalendar = tradingCalendarOperations.get(asset.getTradingCalendar().getId());
        OptionTemplateScheduler scheduler = schedulerOperations.create(tradingCalendar.getTimezoneId(), tagName);
        OptionTemplate optionTemplate = getDefaultTemplate(type);
        optionTemplate.setActive(true);
        optionTemplate.setOptionConfiguration(getConfiguration(assetId, tagId, type, TagOperations.getOptionSubtype(tagName)));
        optionTemplate.setScheduler(scheduler);
        optionTemplate = tpConnector.create(optionTemplate);
        logger.info("Created option template id=" + optionTemplate.getId());
        tpConnector.serviceProxy(IOptionTemplateService.class).addRemoveAccountGroups(Collections.singletonList(accountGroup.getId()), null, optionTemplate);
        return optionTemplate;
    }

    /**
     * Returns a list of found option templates for given criteria.
     *
     * @param assetId used to get trading calendar
     * @param type    option template type to search for
     * @param tagName used to determine tag id
     * @return list of option templates for given criteria
     */
    @Override
    public List<OptionTemplate> getOptionTemplates(String assetId, OptionType type, TagOperations.TagName tagName) {
        AccountGroup accountGroup = accountGroupOperations.get();
        Asset asset = assetOperations.get(assetId);
        TradingCalendar tradingCalendar = tradingCalendarOperations.get(asset.getTradingCalendar().getId());
        List<OptionTemplateScheduler> schedulers = schedulerOperations.get(tradingCalendar.getTimezoneId(), tagName);
        assertFalse(schedulers.isEmpty());
        String tagId = tagOperations.get(tagName).get(0).getId();
        SearchCriteria<OptionTemplate> criteria = forAssetOptionTypeOptionSubtypeTagScheduler(assetId, type, TagOperations.getOptionSubtype(tagName), tagId, schedulers.get(0).getId());
        return tpConnector.serviceProxy(IOptionTemplateService.class).readOptionTemplates(accountGroup, criteria, null, null).getContent();
    }

    private OptionTemplate getDefaultTemplate(OptionType type) {
        if (type == OptionType.HILO) {
            return defaultHighLowTemplate();
        }
        return defaultSpreadTemplate(type);
    }

    private HiloOptionTemplate defaultHighLowTemplate() {
        HiloOptionTemplate hlTemplate = new HiloOptionTemplate();
        hlTemplate.setLoss(0);
        hlTemplate.setProfit(150);
        hlTemplate.setOrderSupport(true);
        return hlTemplate;
    }

    private SpreadOptionTemplate defaultSpreadTemplate(OptionType type) {
        SpreadOptionTemplate spreadTemplate = new SpreadOptionTemplate();
        if (type == OptionType.MAX_PROFIT) {
            spreadTemplate.setProfit(Arrays.asList(200, 250, 300, 400));
        } else {
            spreadTemplate.setProfit(Collections.singletonList(200));
        }
        return spreadTemplate;
    }

    private OptionConfiguration getConfiguration(String assetId, String tagId, OptionType type, OptionSubType subType) {
        OptionConfiguration configuration = new OptionConfiguration();
        configuration.setAssetId(assetId);
        configuration.setCategoryTagId(tagId);
        configuration.setIsEarlyCloseable(true);
        configuration.setIsMarketPriceOnly(true);
        configuration.setNoMoreTrades(5);
        configuration.setType(type);
        configuration.setSubType(subType);
        return configuration;
    }

    private SearchCriteria<OptionTemplate> forAssetOptionTypeOptionSubtypeTagScheduler(String assetId,
                                                                                       OptionType optionType,
                                                                                       OptionSubType optionSubtype,
                                                                                       String tagId,
                                                                                       String schedulerId) {
        return new SearchCriteria<>(OptionTemplate.class)
                .and(OptionTemplate.EP_ASSET_ID.equalsTo(assetId))
                .and(OptionTemplate.EP_OPTION_TYPE.equalsTo(optionType))
                .and(OptionTemplate.EP_OPTION_SUBTYPE.equalsTo(optionSubtype))
                .and(OptionTemplate.EP_TAG_CATEGORY_ID.equalsTo(tagId))
                .and(OptionTemplate.EP_OPTION_TEMPLATE_SCHEDULER_ID.equalsTo(schedulerId))
                .and(OptionTemplate.EP_ORDER_SUPPORT.isTrue())
                .and(OptionTemplate.EP_ACTIVE.isTrue());
    }
}
