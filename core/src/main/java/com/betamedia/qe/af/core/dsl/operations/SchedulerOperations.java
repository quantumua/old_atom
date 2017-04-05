package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.tp.api.model.scheduling.OptionTemplateScheduler;

import java.util.List;

/**
 * Created by mbelyaev on 3/24/17.
 */
public interface SchedulerOperations {
    List<OptionTemplateScheduler> get(String timezoneId, TagOperations.TagName tagName);

    OptionTemplateScheduler create(String timezoneId, TagOperations.TagName tagName);
}
