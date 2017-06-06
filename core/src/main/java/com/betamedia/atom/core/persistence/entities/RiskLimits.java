package com.betamedia.atom.core.persistence.entities;

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

    @Column(name = "bt_verifysettlefrom")
    private double verifySettleFrom;

    public String getId() {
        return id;
    }

    public double getVerifySettleFrom() {
        return verifySettleFrom;
    }

    public void setVerifySettleFrom(double verifySettleFrom) {
        this.verifySettleFrom = verifySettleFrom;
    }
}
