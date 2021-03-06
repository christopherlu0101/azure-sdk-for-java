/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 *
 */

package com.microsoft.azure.management.automation.v2015_10_31.implementation;

import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import com.microsoft.azure.management.automation.v2015_10_31.Certificates;
import rx.Completable;
import rx.Observable;
import rx.functions.Func1;
import com.microsoft.azure.Page;
import com.microsoft.azure.management.automation.v2015_10_31.Certificate;

class CertificatesImpl extends WrapperImpl<CertificatesInner> implements Certificates {
    private final AutomationManager manager;

    CertificatesImpl(AutomationManager manager) {
        super(manager.inner().certificates());
        this.manager = manager;
    }

    public AutomationManager manager() {
        return this.manager;
    }

    @Override
    public CertificateImpl define(String name) {
        return wrapModel(name);
    }

    private CertificateImpl wrapModel(CertificateInner inner) {
        return  new CertificateImpl(inner, manager());
    }

    private CertificateImpl wrapModel(String name) {
        return new CertificateImpl(name, this.manager());
    }

    @Override
    public Observable<Certificate> listByAutomationAccountAsync(final String resourceGroupName, final String automationAccountName) {
        CertificatesInner client = this.inner();
        return client.listByAutomationAccountAsync(resourceGroupName, automationAccountName)
        .flatMapIterable(new Func1<Page<CertificateInner>, Iterable<CertificateInner>>() {
            @Override
            public Iterable<CertificateInner> call(Page<CertificateInner> page) {
                return page.items();
            }
        })
        .map(new Func1<CertificateInner, Certificate>() {
            @Override
            public Certificate call(CertificateInner inner) {
                return wrapModel(inner);
            }
        });
    }

    @Override
    public Observable<Certificate> getAsync(String resourceGroupName, String automationAccountName, String certificateName) {
        CertificatesInner client = this.inner();
        return client.getAsync(resourceGroupName, automationAccountName, certificateName)
        .map(new Func1<CertificateInner, Certificate>() {
            @Override
            public Certificate call(CertificateInner inner) {
                return wrapModel(inner);
            }
       });
    }

    @Override
    public Completable deleteAsync(String resourceGroupName, String automationAccountName, String certificateName) {
        CertificatesInner client = this.inner();
        return client.deleteAsync(resourceGroupName, automationAccountName, certificateName).toCompletable();
    }

}
