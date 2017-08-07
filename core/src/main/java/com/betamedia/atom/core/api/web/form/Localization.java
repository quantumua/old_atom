package com.betamedia.atom.core.api.web.form;

import com.betamedia.atom.core.fwdataaccess.annotations.ClasspathLocation;
import com.opencsv.bean.CsvBindByName;

/**
 * Created by vsnigur on 8/4/17.
 */

@ClasspathLocation("/data/localizations.csv")
public class Localization {
    @CsvBindByName(column = "Element")
    private String element;
    @CsvBindByName(column = "English")
    private String english;
    @CsvBindByName(column = "French")
    private String french;
    @CsvBindByName(column = "German")
    private String german;
    @CsvBindByName(column="Italian")
    private String italian;
    @CsvBindByName(column="Spain")
    private String spain;
    @CsvBindByName(column="Russian")
    private String russian;
    @CsvBindByName(column="Arabian")
    private String arabian;
    @CsvBindByName(column="Portuguese")
    private String portuguese;
    @CsvBindByName(column="Swedish")
    private String swedish;
    @CsvBindByName(column="Netherlands")
    private String netherlands;
    @CsvBindByName(column="Norwegian")
    private String norwegian;
    @CsvBindByName(column="Polish")
    private String polish;

    public void setElement(String element) {
        this.element = element;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public void setFrench(String french) {
        this.french = french;
    }

    public void setGerman(String german) {
        this.german = german;
    }

    public void setItalian(String italian) {
        this.italian = italian;
    }

    public void setSpain(String spain) {
        this.spain = spain;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public void setArabian(String arabian) {
        this.arabian = arabian;
    }

    public void setPortuguese(String portuguese) {
        this.portuguese = portuguese;
    }

    public void setSwedish(String swedish) {
        this.swedish = swedish;
    }

    public void setNetherlands(String netherlands) {
        this.netherlands = netherlands;
    }

    public void setNorwegian(String norwegian) {
        this.norwegian = norwegian;
    }

    public void setPolish(String polish) {
        this.polish = polish;
    }

    public String getElement() {
        return element;
    }

    public String getEnglish() {
        return english;
    }

    public String getFrench() {
        return french;
    }

    public String getGerman() {
        return german;
    }

    public String getItalian() {
        return italian;
    }

    public String getSpain() {
        return spain;
    }

    public String getRussian() {
        return russian;
    }

    public String getArabian() {
        return arabian;
    }

    public String getPortuguese() {
        return portuguese;
    }

    public String getSwedish() {
        return swedish;
    }

    public String getNetherlands() {
        return netherlands;
    }

    public String getNorwegian() {
        return norwegian;
    }

    public String getPolish() {
        return polish;
    }
}

