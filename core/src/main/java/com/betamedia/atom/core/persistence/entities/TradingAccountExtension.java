package com.betamedia.atom.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Entity
@Table(name = "bt_tradingaccountextensionbase")
public class TradingAccountExtension {

    public TradingAccountExtension() {
    }

    public TradingAccountExtension(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "bt_tradingaccountid")
    private String id;

    @Column(name = "bt_customerid")
    private String customerId;

    @Column(name = "bt_leverage")
    private Integer leverage;

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Integer getLeverage() {
        return leverage;
    }
}
