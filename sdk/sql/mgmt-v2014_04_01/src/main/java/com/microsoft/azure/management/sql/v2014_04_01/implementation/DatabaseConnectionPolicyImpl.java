/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.sql.v2014_04_01.implementation;

import com.microsoft.azure.management.sql.v2014_04_01.DatabaseConnectionPolicy;
import com.microsoft.azure.arm.model.implementation.CreatableUpdatableImpl;
import rx.Observable;

class DatabaseConnectionPolicyImpl extends CreatableUpdatableImpl<DatabaseConnectionPolicy, DatabaseConnectionPolicyInner, DatabaseConnectionPolicyImpl> implements DatabaseConnectionPolicy, DatabaseConnectionPolicy.Definition, DatabaseConnectionPolicy.Update {
    private final SqlManager manager;
    private String resourceGroupName;
    private String serverName;
    private String databaseName;

    DatabaseConnectionPolicyImpl(String name, SqlManager manager) {
        super(name, new DatabaseConnectionPolicyInner());
        this.manager = manager;
        // Set resource name
        this.databaseName = name;
        //
    }

    DatabaseConnectionPolicyImpl(DatabaseConnectionPolicyInner inner, SqlManager manager) {
        super(inner.name(), inner);
        this.manager = manager;
        // Set resource name
        this.databaseName = inner.name();
        // set resource ancestor and positional variables
        this.resourceGroupName = IdParsingUtils.getValueFromIdByName(inner.id(), "resourceGroups");
        this.serverName = IdParsingUtils.getValueFromIdByName(inner.id(), "servers");
        this.databaseName = IdParsingUtils.getValueFromIdByName(inner.id(), "databases");
        //
    }

    @Override
    public SqlManager manager() {
        return this.manager;
    }

    @Override
    public Observable<DatabaseConnectionPolicy> createResourceAsync() {
        DatabaseConnectionPoliciesInner client = this.manager().inner().databaseConnectionPolicies();
        return client.createOrUpdateAsync(this.resourceGroupName, this.serverName, this.databaseName, this.inner())
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<DatabaseConnectionPolicy> updateResourceAsync() {
        DatabaseConnectionPoliciesInner client = this.manager().inner().databaseConnectionPolicies();
        return client.createOrUpdateAsync(this.resourceGroupName, this.serverName, this.databaseName, this.inner())
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<DatabaseConnectionPolicyInner> getInnerAsync() {
        DatabaseConnectionPoliciesInner client = this.manager().inner().databaseConnectionPolicies();
        return client.getAsync(this.resourceGroupName, this.serverName, this.databaseName);
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }


    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public String kind() {
        return this.inner().kind();
    }

    @Override
    public String location() {
        return this.inner().location();
    }

    @Override
    public String name() {
        return this.inner().name();
    }

    @Override
    public String proxyDnsName() {
        return this.inner().proxyDnsName();
    }

    @Override
    public String proxyPort() {
        return this.inner().proxyPort();
    }

    @Override
    public String redirectionState() {
        return this.inner().redirectionState();
    }

    @Override
    public String securityEnabledAccess() {
        return this.inner().securityEnabledAccess();
    }

    @Override
    public String state() {
        return this.inner().state();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

    @Override
    public String useServerDefault() {
        return this.inner().useServerDefault();
    }

    @Override
    public String visibility() {
        return this.inner().visibility();
    }

    @Override
    public DatabaseConnectionPolicyImpl withExistingDatabasis(String resourceGroupName, String serverName, String databaseName) {
        this.resourceGroupName = resourceGroupName;
        this.serverName = serverName;
        this.databaseName = databaseName;
        return this;
    }

    @Override
    public DatabaseConnectionPolicyImpl withProxyDnsName(String proxyDnsName) {
        this.inner().withProxyDnsName(proxyDnsName);
        return this;
    }

    @Override
    public DatabaseConnectionPolicyImpl withProxyPort(String proxyPort) {
        this.inner().withProxyPort(proxyPort);
        return this;
    }

    @Override
    public DatabaseConnectionPolicyImpl withRedirectionState(String redirectionState) {
        this.inner().withRedirectionState(redirectionState);
        return this;
    }

    @Override
    public DatabaseConnectionPolicyImpl withSecurityEnabledAccess(String securityEnabledAccess) {
        this.inner().withSecurityEnabledAccess(securityEnabledAccess);
        return this;
    }

    @Override
    public DatabaseConnectionPolicyImpl withState(String state) {
        this.inner().withState(state);
        return this;
    }

    @Override
    public DatabaseConnectionPolicyImpl withUseServerDefault(String useServerDefault) {
        this.inner().withUseServerDefault(useServerDefault);
        return this;
    }

    @Override
    public DatabaseConnectionPolicyImpl withVisibility(String visibility) {
        this.inner().withVisibility(visibility);
        return this;
    }

}
