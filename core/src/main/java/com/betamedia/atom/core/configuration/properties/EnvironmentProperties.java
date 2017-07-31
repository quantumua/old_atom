package com.betamedia.atom.core.configuration.properties;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author mbelyaev
 * @since 7/4/17
 */
public class EnvironmentProperties<T extends EnvironmentDependent> {
    @NestedConfigurationProperty
    private CRMProperties<T> crm;
    @NestedConfigurationProperty
    private EntityProperties<T> entity;
    @NestedConfigurationProperty
    private DataSourceProperties db;

    public CRMProperties<T> getCrm() {
        return crm;
    }

    public void setCrm(CRMProperties<T> crm) {
        this.crm = crm;
    }

    public EntityProperties<T> getEntity() {
        return entity;
    }

    public void setEntity(EntityProperties<T> entity) {
        this.entity = entity;
    }

    public DataSourceProperties getDb() {
        return db;
    }

    public void setDb(DataSourceProperties db) {
        this.db = db;
    }

}
