/*
 * Copyright (c) 2009, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.spi.http;

import jakarta.xml.ws.Endpoint;
import java.util.Set;

/**
 * HttpContext represents a mapping between the root URI path of a web
 * service to a {@link HttpHandler} which is invoked to handle requests
 * destined for that path on the associated container.
 * <p>
 * Container provides the implementation for this and it matches
 * web service requests to corresponding HttpContext objects.
 *
 * @author Jitendra Kotamraju
 * @since 1.7, JAX-WS 2.2
 */
public abstract class HttpContext {

    /**
     * The handler to set for this context.
     */
    protected HttpHandler handler;

    /**
     * Jakarta XML Web Services runtime sets its handler during
     * {@link Endpoint#publish(HttpContext)} to handle
     * HTTP requests for this context. Container or its extensions
     * use this handler to process the requests.
     *
     * @param handler the handler to set for this context
     */
    public void setHandler(HttpHandler handler) {
        this.handler = handler;
    }

    /**
     * Returns the path for this context. This path uniquely identifies
     * an endpoint inside an application and the path is relative to
     * application's context path. Container should give this
     * path based on how it matches request URIs to this HttpContext object.
     *
     * <p>
     * For servlet container, this is typically a url-pattern for an endpoint.
     *
     * <p>
     * Endpoint's address for this context can be computed as follows:
     * <pre>
     *  HttpExchange exch = ...;
     *  String endpointAddress =
     *      exch.getScheme() + "://"
     *      + exch.getLocalAddress().getHostName()
     *      + ":" + exch.getLocalAddress().getPort()
     *      + exch.getContextPath() + getPath();
     * </pre>
     *
     * @return this context's path
     */
    public abstract String getPath();

    /**
     * Returns an attribute value for container's configuration
     * and other data that can be used by Jakarta XML Web Services runtime.
     *
     * @param name attribute name
     * @return attribute value
     */
    public abstract Object getAttribute(String name);

    /**
     * Returns all attribute names for container's configuration
     * and other data that can be used by Jakarta XML Web Services runtime.
     *
     * @return set of all attribute names
     */
    public abstract Set<String> getAttributeNames();

}
