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
import java.io.IOException;

/**
 * A handler which is invoked to process HTTP requests.
 * <p>
 * Jakarta XML Web Services runtime provides the implementation for this and sets
 * it using {@link HttpContext#setHandler(HttpHandler)} during
 * {@link Endpoint#publish(HttpContext) }
 *
 * @author Jitendra Kotamraju
 * @since 1.7, JAX-WS 2.2
 */
public abstract class HttpHandler {
    /**
     * Handles a given request and generates an appropriate response.
     * See {@link HttpExchange} for a description of the steps
     * involved in handling an exchange. Container invokes this method
     * when it receives an incoming request.
     *
     * @param exchange the exchange containing the request from the 
     *      client and used to send the response
     * @throws IOException when an I/O error happens during request
     *      handling
     */
    public abstract void handle(HttpExchange exchange) throws IOException;
}
