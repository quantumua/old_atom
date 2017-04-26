package com.betamedia.qe.af.core.api.tp.entities.builders;

import com.betamedia.qe.af.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.qe.af.core.api.tp.entities.request.MarketingParametersRO;

/**
 * This is a builder for MarketingParametersRO, which, in turn, is used by mobile crm adatper.
 *
 * Created by Oleksandr Losiev on 4/25/17.
 *
 * @see MarketingParametersRO
 * @see AbstractMobileCRMHTTPAdapter
 */
public class MarketingParametersBuilder {

    private String origin = "http://qa.24option.com";
    private String registrationIp = "192.168.15.8";
    private String channel = "o6gx";
    private String referrer = "http://www.meilleursbrokers.com";
    private String siteId = "https://www.24option.com/landing/competition/index.cshtml?lang%3Dfr";
    private String af_siteid;

    private String param1 = "24757";
    private String param2 = "2222";
    private String param3 = "3333";
    private String param4 = "102788911552151772524114102142";
    private String param5 = "5555";
    private String keyword = "TammyTest";
    private String ofrtc = "111";

    private boolean useLongAliases;

    public MarketingParametersBuilder(boolean useLongAliases) {
        this.useLongAliases = useLongAliases;
    }

    public MarketingParametersBuilder setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public MarketingParametersBuilder setRegistrationIp(String registrationIp) {
        this.registrationIp = registrationIp;
        return this;
    }

    public MarketingParametersBuilder setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public MarketingParametersBuilder setReferrer(String referrer) {
        this.referrer = referrer;
        return this;
    }

    public MarketingParametersBuilder setSiteId(String siteId) {
        this.siteId = siteId;
        return this;
    }

    public MarketingParametersBuilder setAf_siteid(String af_siteid) {
        this.af_siteid = af_siteid;
        return this;
    }

    public MarketingParametersBuilder setParam1(String param1) {
        this.param1 = param1;
        return this;
    }

    public MarketingParametersBuilder setParam2(String param2) {
        this.param2 = param2;
        return this;
    }

    public MarketingParametersBuilder setParam3(String param3) {
        this.param3 = param3;
        return this;
    }

    public MarketingParametersBuilder setParam4(String param4) {
        this.param4 = param4;
        return this;
    }

    public MarketingParametersBuilder setParam5(String param5) {
        this.param5 = param5;
        return this;
    }

    public MarketingParametersBuilder setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public MarketingParametersBuilder setOfrtc(String ofrtc) {
        this.ofrtc = ofrtc;
        return this;
    }

    public MarketingParametersRO createMarketingRO() {
        MarketingParametersRO marketingParametersRO = new MarketingParametersRO(origin, registrationIp, channel, referrer, siteId, af_siteid);
        if (useLongAliases) {
            marketingParametersRO.setParamXParameters(param1, param2, param3, param4, param5, keyword, ofrtc);
        }
        else {
            marketingParametersRO.setPXParameters(param1, param2, param3, param4, param5, keyword, ofrtc);
        }
        return marketingParametersRO;
    }
}
