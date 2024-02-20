/*
 * Copyright (c) 2005, 2024 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.handler;
import java.util.Map;

/**
 * The interface {@code MessageContext} abstracts the message
 * context that is processed by a handler in the {@code handle}
 * method.
 *
 * <p>The {@code MessageContext} interface provides methods to
 * manage a property set. {@code MessageContext} properties
 * enable handlers in a handler chain to share processing related
 * state.
 *
 * @since 1.6, JAX-WS 2.0
 */
public interface MessageContext extends Map<String, Object> {

    /**
     * Standard property: message direction, {@code true} for
     * outbound messages, {@code false} for inbound.
     * <p>Type: boolean
     */
    String MESSAGE_OUTBOUND_PROPERTY =
            "jakarta.xml.ws.handler.message.outbound";

    /**
     * Standard property: Map of attachments to a message for the inbound
     * message, key is  the MIME Content-ID, value is a DataHandler.
     * <p>Type: {@code java.util.Map<String, DataHandler>}
     */
    String INBOUND_MESSAGE_ATTACHMENTS =
            "jakarta.xml.ws.binding.attachments.inbound";

    /**
     * Standard property: Map of attachments to a message for the outbound
     * message, key is the MIME Content-ID, value is a DataHandler.
     * <p>Type: {@code java.util.Map<String, DataHandler>}
     */
    String OUTBOUND_MESSAGE_ATTACHMENTS =
            "jakarta.xml.ws.binding.attachments.outbound";

    /**
     * Standard property: input source for WSDL document.
     * <p>Type: org.xml.sax.InputSource
     */
    String WSDL_DESCRIPTION =
            "jakarta.xml.ws.wsdl.description";

    /**
     * Standard property: name of WSDL service.
     * <p>Type: javax.xml.namespace.QName
     */
    String WSDL_SERVICE =
            "jakarta.xml.ws.wsdl.service";

    /**
     * Standard property: name of WSDL port.
     * <p>Type: javax.xml.namespace.QName
     */
    String WSDL_PORT =
            "jakarta.xml.ws.wsdl.port";

    /**
     * Standard property: name of wsdl interface (2.0) or port type (1.1).
     * <p>Type: javax.xml.namespace.QName
     */
    String WSDL_INTERFACE =
            "jakarta.xml.ws.wsdl.interface";

    /**
     * Standard property: name of WSDL operation.
     * <p>Type: javax.xml.namespace.QName
     */
    String WSDL_OPERATION =
            "jakarta.xml.ws.wsdl.operation";

    /**
     * Standard property: HTTP response status code.
     * <p>Type: java.lang.Integer
     */
    String HTTP_RESPONSE_CODE =
            "jakarta.xml.ws.http.response.code";

    /**
     * Standard property: HTTP request headers.
     * <p>Type: {@code java.util.Map<java.lang.String, java.util.List<java.lang.String>>}
     */
    String HTTP_REQUEST_HEADERS =
            "jakarta.xml.ws.http.request.headers";

    /**
     * Standard property: HTTP response headers.
     * <p>Type: {@code java.util.Map<java.lang.String, java.util.List<java.lang.String>>}
     */
    String HTTP_RESPONSE_HEADERS =
            "jakarta.xml.ws.http.response.headers";

    /**
     * Standard property: HTTP request method.
     * <p>Type: java.lang.String
     */
    String HTTP_REQUEST_METHOD =
            "jakarta.xml.ws.http.request.method";

    /**
     * Standard property: servlet request object.
     * <p>Type: jakarta.servlet.http.HttpServletRequest
     */
    String SERVLET_REQUEST =
            "jakarta.xml.ws.servlet.request";

    /**
     * Standard property: servlet response object.
     * <p>Type: jakarta.servlet.http.HttpServletResponse
     */
    String SERVLET_RESPONSE =
            "jakarta.xml.ws.servlet.response";

    /**
     * Standard property: servlet context object.
     * <p>Type: jakarta.servlet.ServletContext
     */
    String SERVLET_CONTEXT =
            "jakarta.xml.ws.servlet.context";

    /**
     * Standard property: Query string for request.
     * <p>Type: String
     **/
    String QUERY_STRING =
            "jakarta.xml.ws.http.request.querystring";

    /**
     * Standard property: Request Path Info
     * <p>Type: String
     */
    String PATH_INFO =
            "jakarta.xml.ws.http.request.pathinfo";

    /**
     * Standard property: WS Addressing Reference Parameters.
     * The list MUST include all SOAP headers marked with the
     * wsa:IsReferenceParameter="true" attribute.
     * <p>Type: {@code List<Element>}
     *
     * @since 1.6, JAX-WS 2.1
     */
    String REFERENCE_PARAMETERS =
            "jakarta.xml.ws.reference.parameters";

    /**
     * Property scope. Properties scoped as {@code APPLICATION} are
     * visible to handlers,
     * client applications and service endpoints; properties scoped as
     * {@code HANDLER}
     * are only normally visible to handlers.
     */
    enum Scope {

        /**
         * Application visibility.
         */
        APPLICATION,

        /**
         * Handler visibility.
         */
        HANDLER}

    /**
     * Sets the scope of a property.
     *
     * @param name Name of the property associated with the
     *             {@code MessageContext}
     * @param scope Desired scope of the property
     * @throws java.lang.IllegalArgumentException if an illegal
     *             property name is specified
     */
    void setScope(String name, Scope scope);

    /**
     * Gets the scope of a property.
     *
     * @param name Name of the property
     * @return Scope of the property
     * @throws java.lang.IllegalArgumentException if a non-existing
     *             property name is specified
     */
    Scope getScope(String name);
}
