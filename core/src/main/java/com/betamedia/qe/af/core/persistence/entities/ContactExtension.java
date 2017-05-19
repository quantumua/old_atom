package com.betamedia.qe.af.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Entity
@Table(name = "contactextensionbase")
public class ContactExtension {

    public ContactExtension() {
    }

    public ContactExtension(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "contactid")
    private String id;

    @Column(name = "bt_haspersonalinformationanswers")
    private boolean fnsPersonal;

    @Column(name = "bt_hastradingexperienceanswers")
    private boolean fnsTrading;

    @Column(name = "bt_riskwarning")
    private boolean riskWarning;

    @Column(name = "bt_experiencelevel")
    private Integer experienceLevel;

    @Column(name = "bt_Accounttype")
    private Integer accountType;

    @Column(name = "bt_poistatus")
    private Integer poiStatus;

    @Column(name = "bt_porstatus")
    private Integer porStatus;

    @Column(name = "bt_countryofbirth")
    private Integer countryOfBirth;

    @Column(name = "bt_nationality")
    private Integer nationality;

    @Column(name = "bt_hasregulationanswers")
    private boolean hasRegulationAnswers;

    @Column(name = "bt_risklimits")
    private String riskLimitsId;

    public void setFnsPersonal(boolean fnsPersonal) {
        this.fnsPersonal = fnsPersonal;
    }

    public void setFnsTrading(boolean fnsTrading) {
        this.fnsTrading = fnsTrading;
    }

    public void setRiskWarning(boolean riskWarning) {
        this.riskWarning = riskWarning;
    }

    public void setExperienceLevel(Integer experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public void setPoiStatus(Integer poiStatus) {
        this.poiStatus = poiStatus;
    }

    public void setPorStatus(Integer porStatus) {
        this.porStatus = porStatus;
    }

    public void setCountryOfBirth(Integer countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public void setHasRegulationAnswers(boolean hasRegulationAnswers) {
        this.hasRegulationAnswers = hasRegulationAnswers;
    }

    public void setRiskLimitsId(String riskLimitsId) {
        this.riskLimitsId = riskLimitsId;
    }

    public String getId() {
        return id;
    }

    public boolean isFnsPersonal() {
        return fnsPersonal;
    }

    public boolean isFnsTrading() {
        return fnsTrading;
    }

    public boolean isRiskWarning() {
        return riskWarning;
    }

    public Integer getExperienceLevel() {
        return experienceLevel;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public Integer isPoiStatus() {
        return poiStatus;
    }

    public Integer isPorStatus() {
        return porStatus;
    }

    public Integer getCountryOfBirth() {
        return countryOfBirth;
    }

    public Integer getNationality() {
        return nationality;
    }

    public boolean getHasRegulationAnswers() {
        return hasRegulationAnswers;
    }

    public String getRiskLimitsId() {
        return riskLimitsId;
    }
}