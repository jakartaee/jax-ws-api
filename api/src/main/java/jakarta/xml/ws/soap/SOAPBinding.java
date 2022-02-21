/*
 * Copyright (c) 2005, 2022 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.soap;


import java.util.Set;
import jakarta.xml.ws.Binding;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.MessageFactory;

/** The {@code SOAPBinding} interface is an abstraction for
 *  the SOAP binding.
 *
 *  @since 1.6, JAX-WS 2.0
**/
public interface SOAPBinding extends Binding {

  /**
   * A constant representing the identity of the SOAP 1.1 over HTTP binding.
   */
  String SOAP11HTTP_BINDING = "http://schemas.xmlsoap.org/wsdl/soap/http";

  /**
   * A constant representing the identity of the SOAP 1.2 over HTTP binding.
   */
  String SOAP12HTTP_BINDING = "http://www.w3.org/2003/05/soap/bindings/HTTP/";

  /**
   * A constant representing the identity of the SOAP 1.1 over HTTP binding
   * with MTOM enabled by default.
   */
  String SOAP11HTTP_MTOM_BINDING = "http://schemas.xmlsoap.org/wsdl/soap/http?mtom=true";

  /**
   * A constant representing the identity of the SOAP 1.2 over HTTP binding
   * with MTOM enabled by default.
   */
  String SOAP12HTTP_MTOM_BINDING = "http://www.w3.org/2003/05/soap/bindings/HTTP/?mtom=true";
    
  
  /** Gets the roles played by the SOAP binding instance.
   *
   *  @return {@code Set<String>} The set of roles played by the binding instance.
  **/
  Set<String> getRoles();

  /** Sets the roles played by the SOAP binding instance.
   *
   *  @param roles    The set of roles played by the binding instance.
   *  @throws jakarta.xml.ws.WebServiceException On an error in the configuration of
   *                  the list of roles.
  **/
  void setRoles(Set<String> roles);

  /**
   * Returns {@code true} if the use of MTOM is enabled.
   *
   * @return {@code true} if and only if the use of MTOM is enabled.
  **/

  boolean isMTOMEnabled();
  
  /**
   * Enables or disables use of MTOM.
   *
   * @param flag   A {@code boolean} specifying whether the use of MTOM should
   *               be enabled or disabled.
   * @throws jakarta.xml.ws.WebServiceException If the specified setting is not supported
   *                  by this binding instance.
   * 
   **/
  void setMTOMEnabled(boolean flag);
  
  /**
   * Gets the Jakarta SOAP with Attachments' {@code SOAPFactory} instance used by this SOAP binding.
   *
   * @return SOAPFactory instance used by this SOAP binding.
  **/
  SOAPFactory getSOAPFactory();
  
  /**
   * Gets the Jakarta SOAP with Attachments' {@code MessageFactory} instance used by this SOAP binding.
   *
   * @return MessageFactory instance used by this SOAP binding.
  **/
  MessageFactory getMessageFactory();
}
