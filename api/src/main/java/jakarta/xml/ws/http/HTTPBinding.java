/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.http;


import jakarta.xml.ws.Binding;

/** The {@code HTTPBinding} interface is an
 *  abstraction for the XML/HTTP binding.
 * 
 *  @since 1.6, JAX-WS 2.0
**/
public interface HTTPBinding extends Binding {

  /**
   * A constant representing the identity of the XML/HTTP binding.
   */
  public static final String HTTP_BINDING = "http://www.w3.org/2004/08/wsdl/http";
}
