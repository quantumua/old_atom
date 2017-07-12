package com.betamedia.atom.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by vsnigur on 7/12/17.
 */
@Entity
@Table(name = "ConnectionRoleBase")
public class ConnectionRoleBase {
    @Id
    @Column(name = "ConnectionRoleId")
    private String connectionRoleId;
    @Column(name = "Name")
    private String Name;

    public String getConnectionRoleId() {
        return connectionRoleId;
    }

    public void setConnectionRoleId(String connectionRoleId) {
        this.connectionRoleId = connectionRoleId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
