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

import jakarta.xml.ws.LogicalMessage;

/** The {@code LogicalMessageContext} interface extends
 *  {@code MessageContext} to
 *  provide access to a the contained message as a protocol neutral
 *  LogicalMessage
 * 
 *  @since 1.6, JAX-WS 2.0
**/
public interface LogicalMessageContext 
                    extends MessageContext {

  /** Gets the message from this message context
   *
   *  @return The contained message; returns {@code null} if no
   *          message is present in this message context
  **/
  public LogicalMessage getMessage();
}
