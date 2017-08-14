package com.betamedia.atom.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Entity
@Table(name = "ContactExtensionBase")
public class ContactExtension {

    @Id
    @Column(name = "ContactId")
    private String contactId;
    @Column(name = "bt_username")
    private String username;
    @Column(name = "bt_haspersonalinformationanswers")
    private Boolean fnsPersonal;
    @Column(name = "bt_hastradingexperienceanswers")
    private Boolean fnsTrading;
    @Column(name = "bt_riskwarning")
    private Boolean riskWarning;
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
    private Boolean hasRegulationAnswers;
    @Column(name = "bt_risklimits")
    private String riskLimitsId;
    @Column(name = "bt_compliant")
    private Boolean customerCompliant;
    @Column(name = "bt_poiocrstatus")
    private Integer poiOcrStatus;
    @Column(name = "bt_porocrstatus")
    private Integer porOcrStatus;
    @Column(name = "bt_access")
    private Integer access;
    @Column(name = "bt_ExperienceLevel")
    private Integer experienceLevel;
    @Column(name = "bt_ExperienceScore")
    private Double experienceScore;
    @Column(name = "bt_trafficsource")
    private int trafficSource;
    @Column(name = "bt_acceptbulkemail")
    private int acceptbulkemail;

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

    public void setCustomerCompliant(boolean customerCompliant) {
        this.customerCompliant = customerCompliant;
    }

    public void setPoiOcrStatus(Integer poiOcrStatus) {
        this.poiOcrStatus = poiOcrStatus;
    }

    public void setPorOcrStatus(Integer porOcrStatus) {
        this.porOcrStatus = porOcrStatus;
    }

    public Integer getCountryOfBirth() {
        return countryOfBirth;
    }

    public Integer getNationality() {
        return nationality;
    }
    
    public String getContactId() {
        return contactId;
    }

    public String getRiskLimitsId() {
        return riskLimitsId;
    }

    public Integer getExperienceLevel() {
        return experienceLevel;
    }

    public Double getExperienceScore() {
        return experienceScore;
    }

    public void setExperienceScore(Double experienceScore) {
        this.experienceScore = experienceScore;
    }

    public Integer getAccess() {
        return access;
    }

    public int getTrafficSource() {
        return trafficSource;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public int getAcceptbulkemail() {
        return acceptbulkemail;
    }

    public void setAcceptbulkemail(int acceptbulkemail) {
        this.acceptbulkemail = acceptbulkemail;
    }

    public Integer getPoiStatus() {
        return poiStatus;
    }

    public Integer getPoiOcrStatus() {
        return poiOcrStatus;
    }

    public Integer getPorOcrStatus() {
        return porOcrStatus;
    }
}
