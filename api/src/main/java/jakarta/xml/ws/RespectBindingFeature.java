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

import jakarta.xml.ws.soap.AddressingFeature;

/**
 * This feature clarifies the use of the {@code wsdl:binding}
 * in a Jakarta XML Web Services runtime.
 * <p>
 * This feature can be used during the creation of SEI proxy, and
 * {@link Dispatch} instances on the client side and {@link Endpoint}
 * instances on the server side. This feature cannot be used for {@link Service}
 * instance creation on the client side.
 * <p>
 * This feature is only useful with web services that have an
 * associated WSDL. Enabling this feature requires that a Jakarta XML Web Services
 * implementation inspect the {@code wsdl:binding} for an
 * endpoint at runtime to make sure that all {@code wsdl:extensions}
 * that have the {@code required} attribute set to {@code true}
 * are understood and are being used.
 * <p>
 * The following describes the affects of this feature with respect
 * to be enabled or disabled:
 * <ul>
 *  <li> ENABLED: In this Mode, a Jakarta XML Web Services runtime MUST assure that all
 *  required {@code wsdl:binding} extensions(including policies) are
 *  either understood and used by the runtime, or explicitly disabled by the
 *  web service application. A web service can disable a particular
 *  extension if there is a corresponding {@link WebServiceFeature} or annotation.
 *  Similarly, a web service client can disable
 *  particular extension using the corresponding {@code WebServiceFeature} while
 *  creating a proxy or Dispatch instance.
 *  The runtime MUST also make sure that binding of
 *  SEI parameters/return values respect the {@code wsdl:binding}.
 *  With this feature enabled, if a required ({@code wsdl:required="true"})
 *  {@code wsdl:binding} extension is in the WSDL and it is not
 *  supported by a Jakarta XML Web Services runtime and it has not
 *  been explicitly turned off by the web service developer, then
 *  that Jakarta XML Web Services runtime MUST behave appropriately based on whether it is
 *  on the client or server:
 *  <UL>
 *    <li>Client: runtime MUST throw a
 *  {@link WebServiceException} no sooner than when one of the methods
 *  above is invoked but no later than the first invocation of an endpoint
 *  operation.
 *    <li>Server: throw a {@link WebServiceException} and the endpoint MUST fail to deploy
 *  </ul>
 *
 *  <li> DISABLED: In this Mode, an implementation may choose whether
 *  to inspect the {@code wsdl:binding} or not and to what degree
 *  the {@code wsdl:binding} will be inspected.  For example,
 *  one implementation may choose to behave as if this feature is enabled,
 *  another implementation may only choose to verify the SEI's
 *  parameter/return type bindings.
 * </ul>
 *
 * @see AddressingFeature
 *
 * @since 1.6, JAX-WS 2.1
 */
public final class RespectBindingFeature extends WebServiceFeature {
    /**
     *
     * Constant value identifying the RespectBindingFeature
     */
    public static final String ID = "jakarta.xml.ws.RespectBindingFeature";


    /**
     * Creates an {@code RespectBindingFeature}.
     * The instance created will be enabled.
     */
    public RespectBindingFeature() {
        this.enabled = true;
    }

    /**
     * Creates an RespectBindingFeature
     *
     * @param enabled specifies whether this feature should
     * be enabled or not.
     */
    public RespectBindingFeature(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getID() {
        return ID;
    }
}
