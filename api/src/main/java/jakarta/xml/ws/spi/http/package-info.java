/*
 * Copyright (c) 2009, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

/**
  Provides HTTP SPI that is used for portable deployment of Jakarta XML Web Services
  in containers (for example servlet containers). This SPI
  is not for end developers but provides a way for the container
  developers to deploy Jakarta XML Web Services portably.

  <p>
  References in this document to JAX-WS refer to the Jakarta XML Web Services unless otherwise noted.<br>
  References in this document to JAXB refer to the Jakarta XML Binding unless otherwise noted.<br>

  <p>
  The portable deployment is done as below:
  <ol>
  <li>Container creates {@link jakarta.xml.ws.Endpoint} objects for an
  application. The necessary information to create Endpoint objects
  may be got from web service deployment descriptor files.</li>
  <li>Container needs to create {@link jakarta.xml.ws.spi.http.HttpContext}
  objects for the deployment. For example, a HttpContext could be
  created using servlet configuration(for e.g url-pattern) for the
  web service in servlet container case.</li>
  <li>Then publishes all the endpoints using
  {@link jakarta.xml.ws.Endpoint#publish(HttpContext)}. During publish(),
  Jakarta XML Web Services runtime registers a {@link jakarta.xml.ws.spi.http.HttpHandler}
  callback to handle incoming requests or
  {@link jakarta.xml.ws.spi.http.HttpExchange} objects. The HttpExchange
  object encapsulates a HTTP request and a response.
  </ol>

  <pre>{@literal
  Container                               Jakarta XML Web Services runtime
  ---------                               --------------
  1. Creates Invoker1, ... InvokerN
  2. Provider.createEndpoint(...)     --> 3. creates Endpoint1
     configures Endpoint1
     ...
  4. Provider.createEndpoint(...)     --> 5. creates EndpointN
     configures EndpointN
  6. Creates ApplicationContext
  7. creates HttpContext1, ... HttpContextN
  8. Endpoint1.publish(HttpContext1)  --> 9. creates HttpHandler1
                                          HttpContext1.setHandler(HttpHandler1)
     ...
 10. EndpointN.publish(HttpContextN)  --> 11. creates HttpHandlerN
                                         HttpContextN.setHandler(HttpHandlerN)

  }</pre>

  The request processing is done as below(for every request):
  <pre>{@literal
  Container                               Jakarta XML Web Services runtime
  ---------                               --------------
  1. Creates a HttpExchange
  2. Gets handler from HttpContext
  3. HttpHandler.handle(HttpExchange) --> 4. reads request from HttpExchange
                                      <-- 5. Calls Invoker
  6. Invokes the actual instance
                                          7. Writes the response to HttpExchange
  }</pre>

  <p>
  The portable undeployment is done as below:
  <pre>
  Container
  ---------
  1. {@literal @}preDestroy on instances
  2. Endpoint1.stop()
  ...
  3. EndpointN.stop()
  </pre>

  @author Jitendra Kotamraju
  @since 1.7, JAX-WS 2.2
 */
package jakarta.xml.ws.spi.http;
