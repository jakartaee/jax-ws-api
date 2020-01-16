/*
 * Copyright (c) 2009, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws;

import java.util.Set;

/**
 * {@code EndpointContext} allows multiple endpoints in an application
 * to share any information. For example, servlet application's war may
 * contain multiple endpoints and these endpoints can get addresses of each
 * other by sharing this context. If multiple endpoints share different
 * ports of a WSDL, then the multiple port addresses can be patched
 * when the WSDL is accessed. It also allows all endpoints to share any
 * other runtime information.
 *
 * <p>
 * This needs to be set by using {@link Endpoint#setEndpointContext}
 * before {@link Endpoint#publish} methods.
 *
 * @author Jitendra Kotamraju
 * @since 1.7, JAX-WS 2.2
 */
public abstract class EndpointContext {

    /**
     * This gives list of endpoints in an application. For e.g in
     * servlet container, a war file may contain multiple endpoints.
     * In case of http, these endpoints are hosted on the same http
     * server.
     *
     * @return list of all endpoints in an application
     */
    public abstract Set<Endpoint> getEndpoints();

}
