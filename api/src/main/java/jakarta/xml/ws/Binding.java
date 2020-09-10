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


/** The {@code Binding} interface is the base interface
 *  for Jakarta XML Web Services protocol bindings.
 *
 *  @since 1.6, JAX-WS 2.0
**/
public interface Binding {

   /** 
    * Gets a copy of the handler chain for a protocol binding instance.
    * If the returned chain is modified a call to {@code setHandlerChain}
    * is required to configure the binding instance with the new chain.
    *
    *  @return {@code java.util.List<Handler>} Handler chain
    */
    public java.util.List<jakarta.xml.ws.handler.Handler> getHandlerChain();

   /** 
    * Sets the handler chain for the protocol binding instance.
    *
    *  @param chain    A List of handler configuration entries
    *  @throws WebServiceException On an error in the configuration of
    *                  the handler chain
    *  @throws java.lang.UnsupportedOperationException If this
    *          operation is not supported. This may be done to
    *          avoid any overriding of a pre-configured handler
    *          chain.
    */
    public void setHandlerChain(java.util.List<jakarta.xml.ws.handler.Handler> chain);

    /** 
     * Get the URI for this binding instance. 
     *
     * @return String The binding identifier for the port.
     *    Never returns {@code null}
     *
     * @since 1.6, JAX-WS 2.1
     */
    String getBindingID();      
}
