package com.betamedia.qe.af.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Entity
@Table(name = "bt_risklimitsextensionbase")
public class RiskLimits {

    public RiskLimits() {
    }

    public RiskLimits(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "bt_risklimitsid")
    private String id;

    @Column(name = "bt_maxsumdaily")
    private double dailyLimit;

    public String getId() {
        return id;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }
}
