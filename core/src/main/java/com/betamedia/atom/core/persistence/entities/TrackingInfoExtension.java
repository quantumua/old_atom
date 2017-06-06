package com.betamedia.atom.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Entity
@Table(name = "bt_trackinginfoextensionbase")
public class TrackingInfoExtension {

    public TrackingInfoExtension() {
    }

    public TrackingInfoExtension(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    @Id
    @Column(name = "bt_trackinginfoid")
    private String id;

    @Column(name = "bt_customer")
    private String customerId;

    @Column(name = "bt_cookiecreationtime")
    private Date cookieCreationTime;

    @Column(name = "bt_keyword")
    private String keyword;

    @Column(name = "bt_param1")
    private String param1;

    @Column(name = "bt_param2")
    private String param2;

    @Column(name = "bt_param3")
    private String param3;

    @Column(name = "bt_param4")
    private String param4;

    @Column(name = "bt_param5")
    private String param5;

    @Column(name = "bt_referrer")
    private String referrer;

    @Column(name = "bt_trackingcode")
    private String trackingCode;

    @Column(name = "bt_siteid")
    private String siteId;

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Date getCookieCreationTime() {
        return cookieCreationTime;
    }

    public String getKeyword() {
        return keyword;
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

    public String getReferrer() {
        return referrer;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public String getSiteId() {
        return siteId;
    }
}
