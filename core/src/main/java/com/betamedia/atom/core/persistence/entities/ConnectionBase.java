package com.betamedia.atom.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by vsnigur on 7/12/17.
 */
@Entity
@Table(name = "ConnectionBase")
public class ConnectionBase {
    @Id
    @Column(name = "ConnectionId")
    private String connectionId;
    @Column(name = "Record1Id")
    private String record1Id;
    @Column(name = "Record2Id")
    private String record2Id;
    @Column(name = "Record1RoleId")
    private String record1RoleId;
    @Column(name = "Record2RoleId")
    private String record2RoleId;


    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getRecord1Id() {
        return record1Id;
    }

    public void setRecord1Id(String record1Id) {
        this.record1Id = record1Id;
    }

    public String getRecord2Id() {
        return record2Id;
    }

    public void setRecord2Id(String record2Id) {
        this.record2Id = record2Id;
    }

    public String getRecord1RoleId() {
        return record1RoleId;
    }

    public void setRecord1RoleId(String record1RoleId) {
        this.record1RoleId = record1RoleId;
    }

    public String getRecord2RoleId() {
        return record2RoleId;
    }

    public void setRecord2RoleId(String record2RoleId) {
        this.record2RoleId = record2RoleId;
    }
}
