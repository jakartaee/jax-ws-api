/*
 * Copyright (c) 2005, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws;

/** The {@code ProtocolException} class is a
 *  base class for exceptions related to a specific protocol binding. Subclasses
 *  are used to communicate protocol level fault information to clients and may
 *  be used on the server to control the protocol specific fault representation.
 *
 *  @since 1.6, JAX-WS 2.0
**/
public class ProtocolException extends WebServiceException {

    private static final long serialVersionUID = -5162391758599890377L;

    /**
     * Constructs a new protocol exception with {@code null} as its detail message. The
     * cause is not initialized, and may subsequently be initialized by a call
     * to {@code Throwable.initCause(java.lang.Throwable)}.
     */
    public ProtocolException() {
        super();
    }

    /**
     * Constructs a new protocol exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@code Throwable.initCause(java.lang.Throwable)}.
     *
     * @param message the detail message. The detail message is saved for later
     *   retrieval by the Throwable.getMessage() method.
     */
    public ProtocolException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.
     *
     * Note that the detail message associated with  cause is not automatically
     * incorporated in  this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval  by
     *   the Throwable.getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the
     * {@code Throwable.getCause()} method). (A {@code null} value is  permitted, and indicates
     * that the cause is nonexistent or  unknown.)
     */
    public ProtocolException(String message,  Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a  detail
     * message of {@code (cause==null ? null : cause.toString())}  (which typically
     * contains the class and detail message of  cause). This constructor is
     * useful for runtime exceptions  that are little more than wrappers for
     * other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     * {@code Throwable.getCause()} method). (A {@code null} value is  permitted, and indicates
     * that the cause is nonexistent or  unknown.)
     */
    public ProtocolException(Throwable cause) {
        super(cause);
    }
}
