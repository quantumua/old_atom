package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.common.search.Page;
import com.betamedia.common.search.Paging;
import com.betamedia.common.search.Sorting;
import com.betamedia.qe.af.core.connectors.tp.FWTPConnector;
import com.betamedia.qe.af.core.dsl.operations.*;
import com.betamedia.tp.api.model.*;
import com.betamedia.tp.api.model.enums.OptionSubType;
import com.betamedia.tp.api.model.enums.OptionType;
import com.betamedia.tp.api.model.scheduling.OptionTemplateScheduler;
import com.betamedia.tp.api.model.scheduling.TradingCalendar;
import com.betamedia.tp.api.service.IOptionTemplateService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.*;

/**
 * Created by Oleksandr Losiev on 4/20/17.
 */
public class AbstractOptionTemplateOperationsTest {

    @InjectMocks
    private AbstractOptionTemplateOperations optionTemplateOperations;

    @Mock
    private FWTPConnector tpConnector;

    @Mock
    private AccountGroupOperations accountGroupOperations;

    @Mock
    private AssetOperations assetOperations;

    @Mock
    private TradingCalendarOperations tradingCalendarOperations;

    @Mock
    private SchedulerOperations schedulerOperations;

    @Mock
    private TagOperations tagOperations;

    @Mock
    private Asset mockAsset;

    @Mock
    private RelatedEntityHolder mockRelatedEntityHolder;

    @Mock
    private TradingCalendar mockTradingCalendar;

    @Mock
    private OptionTemplateScheduler mockScheduler;

    @Mock
    private IOptionTemplateService mockOptionTemplateService;

    private OptionTemplate expectedOptionTemplate;
    private OptionTemplate expectedHighLowOptionTemplate;
    private AccountGroup expectedAccountGroup;
    private Tag expectedTag;

    private String templateId = "templateId";
    private String assetId = "assetId";
    private String tagId = "tagId";
    private String calendarId = "calendarId";
    private String timezoneId = "timezoneId";
    private String accountGroupName = "accountGroupName";
    private String accountGroupDescription = "accountGroupDescription";

    @BeforeClass
    public void setupClass() {
        expectedAccountGroup = new AccountGroup();
        expectedAccountGroup.setName(accountGroupName);
        expectedAccountGroup.setDescription(accountGroupDescription);

        expectedTag = new Tag();
        expectedTag.setId(tagId);
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(accountGroupOperations.get()).thenReturn(expectedAccountGroup);
        when(tagOperations.get(TagOperations.TagName.NO_CATEGORY)).thenReturn(Collections.singletonList(expectedTag));
        when(assetOperations.get(assetId)).thenReturn(mockAsset);
        when(mockAsset.getTradingCalendar()).thenReturn(mockRelatedEntityHolder);
        when(mockRelatedEntityHolder.getId()).thenReturn(calendarId);
        when(tradingCalendarOperations.get(calendarId)).thenReturn(mockTradingCalendar);
        when(mockTradingCalendar.getTimezoneId()).thenReturn(timezoneId);
        when(schedulerOperations.create(timezoneId, TagOperations.TagName.NO_CATEGORY)).thenReturn(mockScheduler);
        when(schedulerOperations.get(timezoneId, TagOperations.TagName.NO_CATEGORY)).thenReturn(Collections.singletonList(mockScheduler));

        expectedOptionTemplate = getExpectedOptionTemplate();
        expectedHighLowOptionTemplate = getExpectedHiLoOptionTemplate();
        when(tpConnector.readById(OptionTemplate.class, templateId)).thenReturn(expectedOptionTemplate);
        when(tpConnector.serviceProxy(IOptionTemplateService.class)).thenReturn(mockOptionTemplateService);
        when(mockOptionTemplateService.readOptionTemplates(eq(expectedAccountGroup), any(), isNull(Paging.class), isNull(Sorting.class)))
                .thenReturn(new Page<>(Collections.singletonList(expectedOptionTemplate), null));

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                OptionTemplate optionTemplate = invocationOnMock.getArgumentAt(0, OptionTemplate.class);
                optionTemplate.setId(templateId);
                return optionTemplate;
            }
        }).when(tpConnector).create(any(OptionTemplate.class));
    }

    @Test
    public void testGetTemplateById() {
        OptionTemplate actualTemplate = optionTemplateOperations.get(templateId);
        assertThat(expectedOptionTemplate, new ReflectionEquals(actualTemplate));
    }

    @Test
    public void testCreateNewTemplate() {
        OptionTemplate actualTemplate = optionTemplateOperations.create(assetId, OptionType.FOREX, TagOperations.TagName.NO_CATEGORY);
        assertThat(expectedOptionTemplate, new ReflectionEquals(actualTemplate));
        verify(mockOptionTemplateService).addRemoveAccountGroups(Collections.singletonList(expectedAccountGroup.getId()), null, expectedOptionTemplate);
    }

    @Test
    public void testCreateNewHighLowTemplate() {
        OptionTemplate actualTemplate = optionTemplateOperations.create(assetId, OptionType.HILO, TagOperations.TagName.NO_CATEGORY);
        assertThat(expectedHighLowOptionTemplate, new ReflectionEquals(actualTemplate));
        verify(mockOptionTemplateService).addRemoveAccountGroups(Collections.singletonList(expectedAccountGroup.getId()), null, expectedHighLowOptionTemplate);
    }

    @Test
    public void testGetMultipleTemplates() {
        List<OptionTemplate> optionTemplates = optionTemplateOperations.getOptionTemplates(assetId, OptionType.FOREX, TagOperations.TagName.NO_CATEGORY);
        assertEquals(1, optionTemplates.size());
        assertThat(expectedOptionTemplate, new ReflectionEquals(optionTemplates.get(0)));
    }

    private OptionTemplate getExpectedHiLoOptionTemplate() {
        HiloOptionTemplate optionTemplate = new HiloOptionTemplate();
        optionTemplate.setLoss(0);
        optionTemplate.setProfit(150);
        optionTemplate.setOrderSupport(true);
        optionTemplate.setActive(true);
        optionTemplate.setOptionConfiguration(getExpectedConfiguration(OptionType.HILO));
        optionTemplate.setScheduler(mockScheduler);
        optionTemplate.setId(templateId);
        return optionTemplate;
    }

    private OptionTemplate getExpectedOptionTemplate() {
        SpreadOptionTemplate optionTemplate = new SpreadOptionTemplate();
        optionTemplate.setProfit(Collections.singletonList(200));
        optionTemplate.setActive(true);
        optionTemplate.setOptionConfiguration(getExpectedConfiguration(OptionType.FOREX));
        optionTemplate.setScheduler(mockScheduler);
        optionTemplate.setId(templateId);
        return optionTemplate;
    }

    private OptionConfiguration getExpectedConfiguration(OptionType optionType) {
        OptionConfiguration configuration = new OptionConfiguration();
        configuration.setAssetId(assetId);
        configuration.setCategoryTagId(tagId);
        configuration.setIsEarlyCloseable(true);
        configuration.setIsMarketPriceOnly(true);
        configuration.setNoMoreTrades(5);
        configuration.setType(optionType);
        configuration.setSubType(OptionSubType.NONE);
        return configuration;
    }
}