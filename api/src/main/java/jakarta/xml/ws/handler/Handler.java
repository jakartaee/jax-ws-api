/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.handler;

import jakarta.xml.ws.ProtocolException;

/** The {@code Handler} interface
 *  is the base interface for Jakarta XML Web Services handlers.
 *
 * @param <C> message context
 *  @since 1.6, JAX-WS 2.0
**/
public interface Handler<C extends MessageContext> {

  /** The {@code handleMessage} method is invoked for normal processing
   *  of inbound and outbound messages. Refer to the description of the handler
   *  framework in the Jakarta XML Web Services specification for full details.
   *
   *  @param context the message context.
   *  @return An indication of whether handler processing should continue for
   *  the current message
   *                 <ul>
   *                 <li>Return {@code true} to continue
   *                     processing.</li>
   *                 <li>Return {@code false} to block
   *                     processing.</li>
   *                  </ul>
   *  @throws RuntimeException Causes the Jakarta XML Web Services runtime to cease
   *    handler processing and generate a fault.
   *  @throws ProtocolException Causes the Jakarta XML Web Services runtime to switch to
   *    fault message processing.
  **/
  public boolean handleMessage(C context);

  /** The {@code handleFault} method is invoked for fault message
   *  processing.  Refer to the description of the handler
   *  framework in the Jakarta XML Web Services specification for full details.
   *
   *  @param context the message context
   *  @return An indication of whether handler fault processing should continue
   *  for the current message
   *                 <ul>
   *                 <li>Return {@code true} to continue
   *                     processing.</li>
   *                 <li>Return {@code false} to block
   *                     processing.</li>
   *                  </ul>
   *  @throws RuntimeException Causes the Jakarta XML Web Services runtime to cease
   *    handler fault processing and dispatch the fault.
   *  @throws ProtocolException Causes the Jakarta XML Web Services runtime to cease
   *    handler fault processing and dispatch the fault.
  **/
  public boolean handleFault(C context);

  /**
   * Called at the conclusion of a message exchange pattern just prior to
   * the Jakarta XML Web Services runtime dispatching a message, fault or exception.  Refer to
   * the description of the handler
   * framework in the Jakarta XML Web Services specification for full details.
   *
   * @param context the message context
  **/
  public void close(MessageContext context);
}
