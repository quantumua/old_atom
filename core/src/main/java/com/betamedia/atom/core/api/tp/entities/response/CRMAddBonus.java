package com.betamedia.atom.core.api.tp.entities.response;

import com.betamedia.atom.core.api.tp.parsers.json.deserializer.CRMAddBonusDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/24/17.
 */
@JsonDeserialize(using = CRMAddBonusDeserializer.class)
public class CRMAddBonus {
    public static final String BONUS_ID = "bonusId";
    private String bonusDisplayId;

    @JsonCreator
    public CRMAddBonus(@JsonProperty(BONUS_ID) String bonusDisplayId) {
        this.bonusDisplayId = bonusDisplayId;
    }

    public String getBonusDisplayId() {
        return bonusDisplayId;
    }

    @Override
    public String toString() {
        return "CRMAddBonus{" +
                "bonusDisplayId='" + bonusDisplayId + '\'' +
                '}';
    }
}
