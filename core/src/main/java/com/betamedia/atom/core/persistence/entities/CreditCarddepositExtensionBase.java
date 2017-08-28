package com.betamedia.atom.core.persistence.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leonid Artemiev
 * @since 8/28/17
 */
@Entity
@Table(name = "bt_creditcarddepositExtensionBase")
public class CreditCarddepositExtensionBase {

    @Id
    @Column(name = "bt_AmountToTransfer")
    private float amountToTransfer;
    @Column(name = "bt_CustomerId")
    private String customerId;



    public String getCustomerId() {
        return customerId;
    }

    public float getAmountToTransfer() {
        return amountToTransfer;
    }
}
