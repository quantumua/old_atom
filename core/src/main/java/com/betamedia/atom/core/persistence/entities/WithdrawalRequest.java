package com.betamedia.atom.core.persistence.entities;

import javax.persistence.*;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Entity
@Table(name = "bt_withdrawalrequestExtensionBase")
public class WithdrawalRequest {

    @Id
    @Column(name = "bt_withdrawalrequestId")
    private String withdrawalrequestId;
    @Column(name = "bt_name")
    private String  name;
    @Column(name = "bt_amountusd")
    private Double amountusd;
    @Column(name = "bt_ExternalRequestId")
    private String ExternalRequestId;
    @Column(name = "bt_fee")
    private Integer fee;
    @Column(name = "bt_id")
    private Integer id;
    @Column(name = "bt_requestedamount")
    private Double requestedamount;
    @Column(name = "bt_requestedcurrency")
    private Integer requestedcurrency;
    @Column(name = "bt_customer")
    private String customer;

    public void setWithdrawalrequestId(String withdrawalrequestId) {
        this.withdrawalrequestId = withdrawalrequestId;
    }
      
	public String getWithdrawalrequestId() {
		return withdrawalrequestId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmountusd() {
		return amountusd;
	}

	public void setAmountusd(Double amountusd) {
		this.amountusd = amountusd;
	}

	public String getExternalRequestId() {
		return ExternalRequestId;
	}

	public void setExternalRequestId(String externalRequestId) {
		ExternalRequestId = externalRequestId;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getRequestedamount() {
		return requestedamount;
	}

	public void setRequestedamount(Double requestedamount) {
		this.requestedamount = requestedamount;
	}

	public Integer getRequestedcurrency() {
		return requestedcurrency;
	}

	public void setRequestedcurrency(Integer requestedcurrency) {
		this.requestedcurrency = requestedcurrency;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
}
