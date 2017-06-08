package com.betamedia.atom.core.api.tp.entities.request;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;

/**
 * Request object for various marketing parameters.
 *
 * Created by Oleksandr Losiev on 4/25/17.
 */
//TODO duplication of CustomerRO fields! We needn't this class. Need to refactor to use CustomerRO instead.
public class MarketingParametersRO {

    private String origin;
    private String registrationIp;
    private String channel;
    private String referrer;
    private String siteId;
    private String af_siteid;

    private String param1;
    private String param2;
    private String param3;
    private String param4;
    private String param5;
    private String keyword;
    private String ofrtc;

    private String p1;
    private String p2;
    private String p3;
    private String p4;
    private String p5;
    private String kw;
    private String oftc;

    private MarketingParametersRO(String origin, String registrationIp, String channel, String referrer, String siteId, String af_siteid) {
        this.origin = origin;
        this.registrationIp = registrationIp;
        this.channel = channel;
        this.referrer = referrer;
        this.siteId = siteId;
        this.af_siteid = af_siteid;
    }


    private void setParamXParameters(String param1, String param2, String param3, String param4, String param5,
                                    String keyword, String ofrtc) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
        this.param5 = param5;
        this.keyword = keyword;
        this.ofrtc = ofrtc;
    }

    private void setPXParameters(String p1, String p2, String p3, String p4, String p5, String kw, String oftc) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.kw = kw;
        this.oftc = oftc;
    }


    public String getOrigin() {
        return origin;
    }

    public String getRegistrationIp() {
        return registrationIp;
    }

    public String getChannel() {
        return channel;
    }

    public String getReferrer() {
        return referrer;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getAf_siteid() {
        return af_siteid;
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

    public String getParam3() {
        return param3;
    }

    public String getParam4() {
        return param4;
    }

    public String getParam5() {
        return param5;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getOfrtc() {
        return ofrtc;
    }

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    public String getP3() {
        return p3;
    }

    public String getP4() {
        return p4;
    }

    public String getP5() {
        return p5;
    }

    public String getKw() {
        return kw;
    }

    public String getOftc() {
        return oftc;
    }

    public static MarketingParametersBuilder builder(boolean useLongAliases){
        return new MarketingParametersBuilder(useLongAliases);
    }

    /**
     * This is a builder for MarketingParametersRO, which, in turn, is used by mobile crm adatper.
     *
     * Created by Oleksandr Losiev on 4/25/17.
     *
     * @see MarketingParametersRO
     * @see AbstractMobileCRMHTTPAdapter
     */
    public static class MarketingParametersBuilder {

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

        private MarketingParametersBuilder(boolean useLongAliases) {
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

        public MarketingParametersRO build() {
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
}
