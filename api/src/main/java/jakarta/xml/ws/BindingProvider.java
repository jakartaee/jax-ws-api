/*
 * Copyright (c) 2005, 2024 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws;

import java.util.Map;
import jakarta.xml.ws.wsaddressing.W3CEndpointReference;

/**
 * The {@code BindingProvider} interface provides access to the
 * protocol binding and associated context objects for request and
 * response message processing.
 *
 * @since 1.6, JAX-WS 2.0
 *
 * @see jakarta.xml.ws.Binding
 **/
public interface BindingProvider {
    /**
     * Standard property: User name for authentication.
     * <p>Type: {@code java.lang.String}
     **/
    String USERNAME_PROPERTY =
            "jakarta.xml.ws.security.auth.username";

    /**
     * Standard property: Password for authentication.
     * <p>Type: {@code java.lang.String}
     **/
    String PASSWORD_PROPERTY =
            "jakarta.xml.ws.security.auth.password";

    /**
     * Standard property: Target service endpoint address. The
     * URI scheme for the endpoint address specification MUST
     * correspond to the protocol/transport binding for the
     * binding in use.
     * <p>Type: {@code java.lang.String}
     **/
    String ENDPOINT_ADDRESS_PROPERTY =
            "jakarta.xml.ws.service.endpoint.address";

    /**
     * Standard property: This boolean property is used by a service
     * client to indicate whether it wants to participate in
     * a session with a service endpoint. If this property is set to
     * {@code true}, the service client indicates that it wants the session
     * to be maintained. If set to {@code false}, the session is not maintained.
     * The default value for this property is {@code false}.
     * <p>Type: {@code java.lang.Boolean}
     **/
    String SESSION_MAINTAIN_PROPERTY =
            "jakarta.xml.ws.session.maintain";

    /**
     * Standard property for SOAPAction. This boolean property
     * indicates whether the value of the
     * {@code jakarta.xml.ws.soap.http.soapaction.uri} property
     * is used for the value of the SOAPAction. The
     * default value of this property is {@code false} indicating
     * that the
     * {@code jakarta.xml.ws.soap.http.soapaction.uri} property
     * is not used for the value of the SOAPAction, however,
     * if WS-Addressing is enabled, the default value is
     * {@code true}.
     *
     * <p>Type: {@code java.lang.Boolean}
     **/
    String SOAPACTION_USE_PROPERTY =
            "jakarta.xml.ws.soap.http.soapaction.use";

    /**
     * Standard property for SOAPAction. Indicates the SOAPAction
     * URI if the {@code jakarta.xml.ws.soap.http.soapaction.use}
     * property is set to {@code true}. If WS-Addressing
     * is enabled, this value will also be used for the value of the
     * WS-Addressing Action header.  If this property is not set,
     * the default SOAPAction and WS-Addressing Action will be sent.
     *
     * <p>Type: {@code java.lang.String}
     **/
    String SOAPACTION_URI_PROPERTY =
            "jakarta.xml.ws.soap.http.soapaction.uri";

    /**
     * Get the context that is used to initialize the message context
     * for request messages.
     * <p>
     * Modifications to the request context do not affect the message context of
     * either synchronous or asynchronous operations that have already been
     * started.
     *
     * @return The context that is used in processing request messages.
     **/
    Map<String, Object> getRequestContext();

    /**
     * Get the context that resulted from processing a response message.
     * <p>
     * The returned context is for the most recently completed synchronous
     * operation. Subsequent synchronous operation invocations overwrite the
     * response context. Asynchronous operations return their response context
     * via the Response interface.
     *
     * @return The context that resulted from processing the latest
     * response messages.
     **/
    Map<String, Object> getResponseContext();

    /**
     * Get the Binding for this binding provider.
     *
     * @return The Binding for this binding provider.
     **/
    Binding getBinding();



    /**
     * Returns the {@code EndpointReference} associated with
     * this {@code BindingProvider} instance.
     * <p>
     * If the Binding for this {@code bindingProvider} is
     * either SOAP1.1/HTTP or SOAP1.2/HTTP, then a
     * {@code W3CEndpointReference} MUST be returned.
     *
     * @return EndpointReference of the target endpoint associated with this
     * {@code BindingProvider} instance.
     *
     * @throws java.lang.UnsupportedOperationException If this
     * {@code BindingProvider} uses the XML/HTTP binding.
     *
     * @see W3CEndpointReference
     *
     * @since 1.6, JAX-WS 2.1
     */
    EndpointReference getEndpointReference();


    /**
     * Returns the {@code EndpointReference} associated with
     * this {@code BindingProvider} instance.  The instance
     * returned will be of type {@code clazz}.
     *
     * @param <T> the type of {@code EndpointReference}
     * @param clazz Specifies the type of {@code EndpointReference}
     * that MUST be returned.

     * @return EndpointReference of the target endpoint associated with this
     * {@code BindingProvider} instance. MUST be of type
     * {@code clazz}.

     * @throws WebServiceException If the Class {@code clazz}
     * is not supported by this implementation.
     * @throws java.lang.UnsupportedOperationException If this
     * {@code BindingProvider} uses the XML/HTTP binding.
     *
     * @since 1.6, JAX-WS 2.1
     */
    <T extends EndpointReference> T getEndpointReference(Class<T> clazz);
}
