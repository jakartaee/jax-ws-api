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

/** The {@code LogicalHandler} extends
 *  Handler to provide typesafety for the message context parameter.
 *
 * @param <C> message context
 *  @since 1.6, JAX-WS 2.0
**/
public interface LogicalHandler<C extends LogicalMessageContext> extends Handler<C> {
}
