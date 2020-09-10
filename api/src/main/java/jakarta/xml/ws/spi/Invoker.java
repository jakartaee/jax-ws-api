/*
 * Copyright (c) 2009, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.spi;

import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.WebServiceFeature;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Invoker hides the detail of calling into application endpoint
 * implementation. Container hands over an implementation of Invoker
 * to Jakarta XML Web Services runtime, and Jakarta XML Web Services runtime calls {@link #invoke}
 * for a web service invocation. Finally, Invoker does the actual
 * invocation of web service on endpoint instance.
 *
 * Container also injects the provided {@code WebServiceContext} and takes
 * care of invoking {@code jakarta.annotation.PostConstruct} methods,
 * if present, on the endpoint implementation.
 *
 * @see Provider#createEndpoint(String, Class, Invoker, WebServiceFeature...)
 * @author Jitendra Kotamraju
 * @since 1.7, JAX-WS 2.2
 */

public abstract class Invoker {

    /**
     * Jakarta XML Web Services runtimes calls this method to ask container to inject
     * WebServiceContext on the endpoint instance. The
     * {@code WebServiceContext} object uses thread-local information
     * to return the correct information during the actual endpoint invocation
     * regardless of how many threads are concurrently being used to serve
     * requests.
     *
     * @param webServiceContext a holder for MessageContext
     * @throws IllegalAccessException if the injection done
     *         by reflection API throws this exception
     * @throws IllegalArgumentException if the injection done
     *         by reflection API throws this exception
     * @throws InvocationTargetException if the injection done
     *         by reflection API throws this exception
     */
    public abstract void inject(WebServiceContext webServiceContext)
    throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
    
    /**
     * Jakarta XML Web Services runtime calls this method to do the actual web service
     * invocation on endpoint instance. The injected
     * {@code WebServiceContext.getMessageContext()} gives the correct
     * information for this invocation.
     *
     * @param m Method to be invoked on the service
     * @param args Method arguments
     * @return return value of the method
     * @throws IllegalAccessException if the invocation done
     *         by reflection API throws this exception
     * @throws IllegalArgumentException if the invocation done
     *         by reflection API throws this exception
     * @throws InvocationTargetException if the invocation done
     *         by reflection API throws this exception

     * @see Method#invoke
     */
    public abstract Object invoke(Method m, Object... args)
    throws  IllegalAccessException, IllegalArgumentException, InvocationTargetException;

}
