package com.betamedia.atom.core.persistence.entities;


import javax.persistence.*;

/**
 * @author Leonid Artemiev
 * @since 8/28/17
 */
@Entity
@Table(name = "bt_creditcarddepositExtensionBase")
public class CreditCarddepositExtensionBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWGUID", columnDefinition = "uniqueidentifier")
    private String rowguid;

    @Column(name = "bt_CustomerId")
    private String customerId;
    @Column(name = "bt_RequestedAmount")
    private Float amountToTransfer;


    public String getccId() {
        return rowguid;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Float getRequestedAmount() {
        return amountToTransfer;
    }
}
