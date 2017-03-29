package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.qe.af.common.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.operations.*;
import com.betamedia.tp.api.model.*;
import com.betamedia.tp.api.model.enums.OptionSubType;
import com.betamedia.tp.api.model.enums.OptionType;
import com.betamedia.tp.api.model.scheduling.OptionTemplateScheduler;
import com.betamedia.tp.api.model.scheduling.TradingCalendar;
import com.betamedia.tp.api.service.IOptionTemplateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertNotNull;

/**
 * Created by mbelyaev on 3/24/17.
 */
@Component
public class OptionTemplateOperationsImpl implements OptionTemplateOperations {
    private static final Logger logger = LogManager.getLogger(OptionTemplateOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;
    @Autowired
    private AssetOperations assetOperations;
    @Autowired
    private TradingCalendarOperations tradingCalendarOperations;
    @Autowired
    private SchedulerOperations schedulerOperations;
    @Autowired
    private TagOperations tagOperations;


    @Override
    public OptionTemplate get() {
        return null;
    }

    @Override
    public OptionTemplate get(String id) {
        OptionTemplate optionTemplate = tpConnector.readById(OptionTemplate.class, id);
        assertNotNull(optionTemplate, "OptionTemplate id=" + id + " is not available in GS");
        return optionTemplate;
    }

    @Override
    public OptionTemplate create(String assetId, AccountGroup accountGroup, OptionType type, TagOperations.TagName tagName) {
        String tagId = tagOperations.get(tagName).get(0).getId();
        Asset asset = assetOperations.get(assetId);
        OptionTemplate optionTemplate = getDefaultTemplate(type);
        optionTemplate.setActive(true);
        optionTemplate.setOptionConfiguration(getConfiguration(assetId, tagId, type, TagOperations.getOptionSubtype(tagName)));
        TradingCalendar tradingCalendar = tradingCalendarOperations.get(asset.getTradingCalendar().getId());
        String timezoneId = tradingCalendar.getTimezoneId();
        List<OptionTemplateScheduler> schedulers = schedulerOperations.get(timezoneId, tagName);
        optionTemplate.setScheduler(schedulers.get(0));
        optionTemplate = tpConnector.create(optionTemplate);
        tpConnector.serviceProxy(IOptionTemplateService.class).addRemoveAccountGroups(Collections.singletonList(accountGroup.getId()), null, optionTemplate);
        return optionTemplate;
    }

    @Override
    public List<OptionTemplate> getOptionTemplates(String assetId, AccountGroup accountGroup, OptionType type, TagOperations.TagName tagName) {
        Asset asset = assetOperations.get(assetId);
        TradingCalendar tradingCalendar = tradingCalendarOperations.get(asset.getTradingCalendar().getId());
        List<OptionTemplateScheduler> schedulers = schedulerOperations.get(tradingCalendar.getTimezoneId(), tagName);
        if (schedulers.isEmpty()) {
            schedulers = Collections.singletonList(schedulerOperations.create(tradingCalendar.getTimezoneId(), tagName));
        }
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
