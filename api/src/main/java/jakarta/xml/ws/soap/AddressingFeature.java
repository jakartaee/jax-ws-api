/*
 * Copyright (c) 2005, 2022 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.soap;

import jakarta.xml.ws.WebServiceFeature;
import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.Service;

/**
 * AddressingFeature represents the use of WS-Addressing with either
 * the SOAP 1.1/HTTP or SOAP 1.2/HTTP binding. Using this feature
 * with any other binding is undefined.
 * <p>
 * This feature can be used during the creation of SEI proxy, and
 * {@link jakarta.xml.ws.Dispatch} instances on the client side and {@link Endpoint}
 * instances on the server side. This feature cannot be used for {@link Service}
 * instance creation on the client side.
 * <p>
 * The following describes the effects of this feature with respect
 * to be enabled or disabled:
 * <ul>
 *  <li> ENABLED: In this Mode, WS-Addressing will be enabled. It means
 *       the endpoint supports WS-Addressing but does not require its use.
 *       A sender could send messages with WS-Addressing headers or without
 *       WS-Addressing headers. But a receiver MUST consume both types of
 *       messages.
 *  <li> DISABLED: In this Mode, WS-Addressing will be disabled.
 *       At runtime, WS-Addressing headers MUST NOT be used by a sender or
 *       receiver.
 * </ul>
 * <p>
 * If the feature is enabled, the {@code required} property determines
 * whether the endpoint requires WS-Addressing. If it is set true,
 * WS-Addressing headers MUST be present on incoming and outgoing messages.
 * By default the {@code required} property is {@code false}.
 *
 * <p>
 * If the web service developer has not explicitly enabled this feature,
 * WSDL's wsam:Addressing policy assertion is used to find
 * the use of WS-Addressing. By using the feature explicitly, an application
 * overrides WSDL's indication of the use of WS-Addressing. In some cases,
 * this is really required. For example, if an application has implemented
 * WS-Addressing itself, it can use this feature to disable addressing. That
 * means a Jakarta XML Web Services implementation doesn't consume or produce WS-Addressing
 * headers.
 *
 * <p>
 * If addressing is enabled, a corresponding wsam:Addressing policy assertion
 * must be generated in the WSDL as per
 * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicyassertions">
 * 3.1 WS-Policy Assertions</a>
 *
 * <p>
 * <b>Example 1: </b>Possible Policy Assertion in the generated WSDL for
 * {@code @Addressing}
 * <pre> {@code
 *   <wsam:Addressing wsp:Optional="true">
 *     <wsp:Policy/>
 *   </wsam:Addressing> }
 * </pre>
 *
 * <p>
 * <b>Example 2: </b>Possible Policy Assertion in the generated WSDL for
 * {@code @Addressing(required=true)}
 * <pre> {@code
 *   <wsam:Addressing>
 *     <wsp:Policy/>
 *   </wsam:Addressing> }
 * </pre>
 *
 * <p>
 * <b>Example 3: </b>Possible Policy Assertion in the generated WSDL for
 * {@code @Addressing(required=true, responses=Responses.ANONYMOUS)}
 * <pre> {@code
 *   <wsam:Addressing>
 *      <wsp:Policy>
 *        <wsam:AnonymousResponses/>
 *      </wsp:Policy>
 *   </wsam:Addressing> }
 * </pre>
 *
 * <p>
 * See <a href="http://www.w3.org/TR/2006/REC-ws-addr-core-20060509/">
 * Web Services Addressing - Core</a>,
 * <a href="http://www.w3.org/TR/2006/REC-ws-addr-soap-20060509/">
 * Web Services Addressing 1.0 - SOAP Binding</a>,
 * and <a href="http://www.w3.org/TR/ws-addr-metadata/">
 * Web Services Addressing 1.0 - Metadata</a>
 * for more information on WS-Addressing.
 * 
 * @see Addressing
 * @since 1.6, JAX-WS 2.1
 */

public final class AddressingFeature extends WebServiceFeature {
    /** 
     * Constant value identifying the AddressingFeature
     */
    public static final String ID = "http://www.w3.org/2005/08/addressing/module";
  
    /**
     * If addressing is enabled, this property determines whether the endpoint
     * requires WS-Addressing. If required is true, WS-Addressing headers MUST
     * be present on incoming and outgoing messages.
     */
    private boolean required;

    /**
     * If addressing is enabled, this property determines if endpoint requires
     * the use of only anonymous responses, or only non-anonymous responses, or all.
     *
     * <p>
     * {@link Responses#ALL} supports all response types and this is the default
     * value.
     *
     * <p>
     * {@link Responses#ANONYMOUS} requires the use of only anonymous
     * responses. It will result into wsam:AnonymousResponses nested assertion
     * as specified in
     * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicyanonresponses">
     * 3.1.2 AnonymousResponses Assertion</a> in the generated WSDL.
     *
     * <p>
     * {@link Responses#NON_ANONYMOUS} requires the use of only non-anonymous
     * responses. It will result into
     * wsam:NonAnonymousResponses nested assertion as specified in
     * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicynonanonresponses">
     * 3.1.3 NonAnonymousResponses Assertion</a> in the generated WSDL.
     *
     * @since 1.7, JAX-WS 2.2
     */
    public enum Responses {
        /**
         * Specifies the use of only anonymous
         * responses. It will result into wsam:AnonymousResponses nested assertion
         * as specified in
         * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicyanonresponses">
         * 3.1.2 AnonymousResponses Assertion</a> in the generated WSDL.
         */
        ANONYMOUS,

        /**
         * Specifies the use of only non-anonymous
         * responses. It will result into
         * wsam:NonAnonymousResponses nested assertion as specified in
         * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicynonanonresponses">
         * 3.1.3 NonAnonymousResponses Assertion</a> in the generated WSDL.
         */
        NON_ANONYMOUS, 

        /**
         * Supports all response types and this is the default
         */
        ALL
    }

    private final Responses responses;

    /**
     * Creates and configures an {@code AddressingFeature} with the
     * use of addressing requirements. The created feature enables
     * ws-addressing i.e. supports ws-addressing but doesn't require
     * its use. It is also configured to accept all the response types.
     */
    public AddressingFeature() {
        this(true, false, Responses.ALL);
    }
    
    /**
     * Creates and configures an {@code AddressingFeature} with the
     * use of addressing requirements. If {@code enabled} is true,
     * it enables ws-addressing i.e. supports ws-addressing but doesn't
     * require its use. It also configures to accept all the response types.
     * 
     * @param enabled true enables ws-addressing i.e.ws-addressing
     * is supported but doesn't require its use
     */
    public AddressingFeature(boolean enabled) {
        this(enabled, false, Responses.ALL);
    }

    /** 
     * Creates and configures an {@code AddressingFeature} with the
     * use of addressing requirements. If {@code enabled} and
     * {@code required} are true, it enables ws-addressing and
     * requires its use. It also configures to accept all the response types.
     *
     * @param enabled true enables ws-addressing i.e.ws-addressing
     * is supported but doesn't require its use
     * @param required true means requires the use of ws-addressing .
     */
    public AddressingFeature(boolean enabled, boolean required) {
        this(enabled, required, Responses.ALL);
    }

    /**
     * Creates and configures an {@code AddressingFeature} with the
     * use of addressing requirements. If {@code enabled} and
     * {@code required} are true, it enables ws-addressing and
     * requires its use. Also, the response types can be configured using
     * {@code responses} parameter.
     *
     * @param enabled true enables ws-addressing i.e.ws-addressing
     * is supported but doesn't require its use
     * @param required true means requires the use of ws-addressing .
     * @param responses specifies what type of responses are required
     *
     * @since 1.7, JAX-WS 2.2
     */
    public AddressingFeature(boolean enabled, boolean required, Responses responses) {
        this.enabled = enabled;
        this.required = required;
        this.responses = responses;
    }
    
    @Override
    public String getID() {
        return ID;
    }

    /**
     * If addressing is enabled, this property determines whether the endpoint
     * requires WS-Addressing. If required is true, WS-Addressing headers MUST
     * be present on incoming and outgoing messages.
     *
     * @return the current required value
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * If addressing is enabled, this property determines whether endpoint
     * requires the use of anonymous responses, or non-anonymous responses,
     * or all responses.
     *
     * @return {@link Responses#ALL} when endpoint supports all types of
     * responses,
     *         {@link Responses#ANONYMOUS} when endpoint requires the use of
     * only anonymous responses,
     *         {@link Responses#NON_ANONYMOUS} when endpoint requires the use
     * of only non-anonymous responses
     *
     * @since 1.7, JAX-WS 2.2
     */
    public Responses getResponses() {
        return responses;
    }

}
