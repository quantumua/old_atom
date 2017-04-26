package com.betamedia.qe.af.core.api.tp.entities.request;

/**
 * Request object for various marketing parameters.
 *
 * Created by Oleksandr Losiev on 4/25/17.
 */
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

    public MarketingParametersRO(String origin, String registrationIp, String channel, String referrer, String siteId, String af_siteid) {
        this.origin = origin;
        this.registrationIp = registrationIp;
        this.channel = channel;
        this.referrer = referrer;
        this.siteId = siteId;
        this.af_siteid = af_siteid;
    }


    public void setParamXParameters(String param1, String param2, String param3, String param4, String param5,
                                    String keyword, String ofrtc) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
        this.param5 = param5;
        this.keyword = keyword;
        this.ofrtc = ofrtc;
    }

    public void setPXParameters(String p1, String p2, String p3, String p4, String p5, String kw, String oftc) {
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
}
