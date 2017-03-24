package com.betamedia.qe.af.core.api.tp.entities.response;

import com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser.AddBonusDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/24/17.
 */
@JsonDeserialize(using = AddBonusDeserializer.class)
public class AddBonus {
    public static final String BONUS_ID = "bonusId";
    private String bonusDisplayId;

    @JsonCreator
    public AddBonus(@JsonProperty(BONUS_ID) String bonusDisplayId) {
        this.bonusDisplayId = bonusDisplayId;
    }

    public String getBonusDisplayId() {
        return bonusDisplayId;
    }

    @Override
    public String toString() {
        return "AddBonus{" +
                "bonusDisplayId='" + bonusDisplayId + '\'' +
                '}';
    }
}
