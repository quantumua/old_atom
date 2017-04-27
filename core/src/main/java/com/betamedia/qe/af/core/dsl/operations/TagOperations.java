package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.Tag;
import com.betamedia.tp.api.model.enums.OptionSubType;

import java.util.List;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface TagOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    List<Tag> get(TagOperations.TagName tagName);

    enum TagName {
        NO_CATEGORY("INTRA DAY", null),
        SHORT_TERM_2_MIN_GAME_H3_TEXT("SHORT 2MIN", 120),
        SHORT_TERM_30_SEC_GAME_H3_TEXT("SHORT 0.5MIN", 30),
        SHORT_TERM_5_MIN_GAME_H3_TEXT("SHORT 5MIN", 300),
        SHORT_TERM_60_SEC_GAME_H3_TEXT("SHORT 1MIN", 60);
        public final String value;
        public final Integer shortTermDuration;

        TagName(String value, Integer shortTermDuration) {
            this.value = value;
            this.shortTermDuration = shortTermDuration;
        }

        public Integer getShortTermDuration() {
            return shortTermDuration;
        }
    }



    static OptionSubType getOptionSubtype(TagName tagName) {
        if (tagName == TagName.NO_CATEGORY) {
            return OptionSubType.NONE;
        }
        return OptionSubType.SHORT_TERM;
    }

    static int getDuration(TagName tagName) {
        switch (tagName) {
            case NO_CATEGORY:
                return 1;
            case SHORT_TERM_2_MIN_GAME_H3_TEXT:
                return 3;
            case SHORT_TERM_30_SEC_GAME_H3_TEXT:
                return 1;
            case SHORT_TERM_5_MIN_GAME_H3_TEXT:
                return 6;
            case SHORT_TERM_60_SEC_GAME_H3_TEXT:
                return 2;
            default:
                return 0;
        }
    }
}
