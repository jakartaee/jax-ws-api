/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.handler.soap;

import javax.xml.namespace.QName;
import jakarta.xml.ws.handler.Handler;
import java.util.Set;

/** The {@code SOAPHandler} class extends {@code Handler}
 *  to provide typesafety for the message context parameter and add a method
 *  to obtain access to the headers that may be processed by the handler.
 *
 * @param <T> message context
 *  @since 1.6, JAX-WS 2.0
**/
public interface SOAPHandler<T extends SOAPMessageContext>
    extends Handler<T> {

  /** Gets the header blocks that can be processed by this Handler
   *  instance.
   *
   *  @return Set of {@code QNames} of header blocks processed by this
   *           handler instance. {@code QName} is the qualified
   *           name of the outermost element of the Header block.
  **/
  Set<QName> getHeaders();
}
