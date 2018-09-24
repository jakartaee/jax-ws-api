/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package javax.xml.ws.soap;

import javax.xml.soap.SOAPFault;

/** The {@code SOAPFaultException} exception represents a
 *  SOAP 1.1 or 1.2 fault.
 *
 *  <p>A {@code SOAPFaultException} wraps a SAAJ {@code SOAPFault}
 *  that manages the SOAP-specific representation of faults.
 *  The {@code createFault} method of
 *  {@code javax.xml.soap.SOAPFactory} may be used to create an instance
 *  of {@code javax.xml.soap.SOAPFault} for use with the
 *  constructor. {@code SOAPBinding} contains an accessor for the
 *  {@code SOAPFactory} used by the binding instance.
 *
 *  <p>Note that the value of {@code getFault} is the only part of the
 *  exception used when searializing a SOAP fault.
 *
 *  <p>Refer to the SOAP specification for a complete
 *  description of SOAP faults.
 *
 *  @see javax.xml.soap.SOAPFault
 *  @see javax.xml.ws.soap.SOAPBinding#getSOAPFactory
 *  @see javax.xml.ws.ProtocolException
 *
 *  @since 1.6, JAX-WS 2.0
 **/
public class SOAPFaultException extends javax.xml.ws.ProtocolException  {
    
    private SOAPFault fault;
    
    /** Constructor for SOAPFaultException
     *  @param fault   {@code SOAPFault} representing the fault
     *
     *  @see javax.xml.soap.SOAPFactory#createFault
     **/
    public SOAPFaultException(SOAPFault fault) {
        super(fault.getFaultString());
        this.fault = fault;
    }
    
    /** Gets the embedded {@code SOAPFault} instance.
     *
     *  @return {@code javax.xml.soap.SOAPFault} SOAP
     *          fault element
     **/
    public javax.xml.soap.SOAPFault getFault() {
        return this.fault;
    }
}
