/*
 * Copyright (c) 2005, 2024 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws;

import java.util.concurrent.Future;


/** The {@code Dispatch} interface provides support
 *  for the dynamic invocation of a service endpoint operations. The
 *  {@code jakarta.xml.ws.Service}
 *  class acts as a factory for the creation of {@code Dispatch}
 *  instances.
 *
 * @param <T> The type of the message or payload
 *  @since 1.6, JAX-WS 2.0
**/
public interface Dispatch<T> extends BindingProvider {

    /** Invoke a service operation synchronously.
     * <p>
     * The client is responsible for ensuring that the {@code msg} object
     * when marshalled is formed according to the requirements of the protocol
     * binding in use.
     *
     * @param msg An object that will form the message or payload of
     *     the message used to invoke the operation.
     * @return The response message or message payload to the
     *     operation invocation.
     * @throws WebServiceException If a fault occurs during communication with
     *     the service
     * @throws WebServiceException If there is any error in the configuration of
     *     the {@code Dispatch} instance
    **/
    T invoke(T msg);

    /** Invoke a service operation asynchronously.  The
     *  method returns without waiting for the response to the operation
     *  invocation, the results of the operation are obtained by polling the
     *  returned {@code Response}.
     * <p>
     * The client is responsible for ensuring that the {@code msg} object
     * when marshalled is formed according to the requirements of the protocol
     * binding in use.
     *
     * @param msg An object that will form the message or payload of
     *     the message used to invoke the operation.
     * @return The response message or message payload to the
     *     operation invocation.
     * @throws WebServiceException If there is any error in the configuration of
     *     the {@code Dispatch} instance
    **/
    Response<T> invokeAsync(T msg);

    /** Invoke a service operation asynchronously. The
     *  method returns without waiting for the response to the operation
     *  invocation, the results of the operation are communicated to the client
     *  via the passed in {@code handler}.
     * <p>
     * The client is responsible for ensuring that the {@code msg} object
     * when marshalled is formed according to the requirements of the protocol
     * binding in use.
     *
     * @param msg An object that will form the message or payload of
     *     the message used to invoke the operation.
     * @param handler The handler object that will receive the
     *     response to the operation invocation.
     * @return A {@code Future} object that may be used to check the status
     *     of the operation invocation. This object MUST NOT be used to try to
     *     obtain the results of the operation - the object returned from
     *     {@code Future<?>.get()} is implementation dependent
     *     and any use of it will result in non-portable behaviour.
     * @throws WebServiceException If there is any error in the configuration of
     *     the {@code Dispatch} instance
    **/
    Future<?> invokeAsync(T msg, AsyncHandler<T> handler);

    /** Invokes a service operation using the one-way
     *  interaction mode. The operation invocation is logically non-blocking,
     *  subject to the capabilities of the underlying protocol, no results
     *  are returned. When
     *  the protocol in use is SOAP/HTTP, this method MUST block until
     *  an HTTP response code has been received or an error occurs.
     * <p>
     * The client is responsible for ensuring that the {@code msg} object
     * when marshalled is formed according to the requirements of the protocol
     * binding in use.
     *
     * @param msg An object that will form the message or payload of
     *     the message used to invoke the operation.
     * @throws WebServiceException If there is any error in the configuration of
     *     the {@code Dispatch} instance or if an error occurs during the
     *     invocation.
    **/
    void invokeOneWay(T msg);
}
