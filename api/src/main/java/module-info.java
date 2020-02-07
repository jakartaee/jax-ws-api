/*
 * Copyright (c) 2017, 2020 Oracle and/or its affiliates. All rights reserved.
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
module jakarta.xml.ws {
    requires transitive java.xml;
    requires transitive jakarta.xml.bind;
    requires transitive jakarta.xml.soap;

    requires java.logging;

    exports jakarta.xml.ws;
    exports jakarta.xml.ws.handler;
    exports jakarta.xml.ws.handler.soap;
    exports jakarta.xml.ws.http;
    exports jakarta.xml.ws.soap;
    exports jakarta.xml.ws.spi;
    exports jakarta.xml.ws.spi.http;
    exports jakarta.xml.ws.wsaddressing;

    opens jakarta.xml.ws.wsaddressing to jakarta.xml.bind;

    uses jakarta.xml.ws.spi.Provider;
}
