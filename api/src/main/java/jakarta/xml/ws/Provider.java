/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws;

/**
 *  <p>Service endpoints may implement the {@code Provider}
 *  interface as a dynamic alternative to an SEI.
 *
 *  <p>Implementations are required to support {@code Provider<Source>},
 *  {@code Provider<SOAPMessage>} and
 *  {@code Provider<DataSource>}, depending on the binding
 *  in use and the service mode.
 *
 *  <p>The {@code ServiceMode} annotation can be used to control whether
 *  the {@code Provider} instance will receive entire protocol messages
 *  or just message payloads.
 *
 * @param <T> The type of the request
 *  @since 1.6, JAX-WS 2.0
 *
 *  @see javax.xml.transform.Source
 *  @see jakarta.xml.soap.SOAPMessage
 *  @see jakarta.xml.ws.ServiceMode
**/
public interface Provider<T> {

  /** Invokes an operation according to the contents of the request
   *  message.
   *
   *  @param  request The request message or message payload.
   *  @return The response message or message payload. May be {@code null} if
              there is no response.
   *  @throws WebServiceException If there is an error processing request.
   *          The cause of the {@code WebServiceException} may be set to a subclass
   *          of {@code ProtocolException} to control the protocol level
   *          representation of the exception.
   *  @see jakarta.xml.ws.handler.MessageContext
   *  @see jakarta.xml.ws.ProtocolException
  **/
  public T invoke(T request);
}
