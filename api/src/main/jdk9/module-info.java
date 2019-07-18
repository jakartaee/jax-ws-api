/*
 * Copyright (c) 2017, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

/**
 * Jakarta XML Web Services API.
 *
 * <p>
 * References in this document to JAX-WS refer to the Jakarta XML Web Services unless otherwise noted.<br>
 * References in this document to SAAJ refer to the Jakarta SOAP with Attachments unless otherwise noted.<br>
 * References in this document to JAXB refer to the Jakarta XML Binding unless otherwise noted.<br>
 * References in this document to JWS refer to the Jakarta Web Services Metadata unless otherwise noted.
 */
module java.xml.ws {
    requires java.xml.bind;
    requires java.logging;
    requires java.xml.soap;
    requires java.annotation;

    exports javax.xml.ws;
    exports javax.xml.ws.handler;
    exports javax.xml.ws.handler.soap;
    exports javax.xml.ws.http;
    exports javax.xml.ws.soap;
    exports javax.xml.ws.spi;
    exports javax.xml.ws.spi.http;
    exports javax.xml.ws.wsaddressing;

    uses javax.xml.ws.spi.Invoker;
    uses javax.xml.ws.spi.Provider;
    uses javax.xml.ws.spi.ServiceDelegate;
    uses javax.xml.ws.spi.WebServiceFeatureAnnotation;
}
