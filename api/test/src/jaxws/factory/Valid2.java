/*
 * Copyright (c) 2015, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jaxws.factory;

import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.EndpointReference;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebServiceFeature;
import jakarta.xml.ws.spi.Provider;
import jakarta.xml.ws.spi.ServiceDelegate;
import jakarta.xml.ws.wsaddressing.W3CEndpointReference;
import java.net.URL;
import java.util.List;

/**
 * (Another) Valid Provider implementation class for tests
 * - several implementations necessary to test different configuration approaches
 */
public class Valid2 extends Provider {

    @Override
    public ServiceDelegate createServiceDelegate(URL wsdlDocumentLocation, QName serviceName, Class<? extends Service> serviceClass) {
        return null;
    }

    @Override
    public Endpoint createEndpoint(String bindingId, Object implementor) {
        return null;
    }

    @Override
    public Endpoint createAndPublishEndpoint(String address, Object implementor) {
        return null;
    }

    @Override
    public EndpointReference readEndpointReference(Source eprInfoset) {
        return null;
    }

    @Override
    public <T> T getPort(EndpointReference endpointReference, Class<T> serviceEndpointInterface, WebServiceFeature... features) {
        return null;
    }

    @Override
    public W3CEndpointReference createW3CEndpointReference(String address, QName serviceName, QName portName, List<Element> metadata, String wsdlDocumentLocation, List<Element> referenceParameters) {
        return null;
    }
}
