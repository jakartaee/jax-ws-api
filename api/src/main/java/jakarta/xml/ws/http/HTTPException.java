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


/** The {@code HTTPException} exception represents a
 *  XML/HTTP fault.
 *
 *  <p>Since there is no standard format for faults or exceptions
 *  in XML/HTTP messaging, only the HTTP status code is captured. 
 * 
 *  @since 1.6, JAX-WS 2.0
**/
public class HTTPException extends jakarta.xml.ws.ProtocolException  {
  
  private int statusCode;

  /** Constructor for the HTTPException
   *  @param statusCode   {@code int} for the HTTP status code
  **/
  public HTTPException(int statusCode) { 
    super();
    this.statusCode = statusCode;
  }

  /** Gets the HTTP status code.
   *
   *  @return HTTP status code
  **/
  public int getStatusCode() {
    return statusCode;
  }
}
