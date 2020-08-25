/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.spi;

import java.util.logging.Logger;
import jakarta.xml.ws.WebServiceException;

class FactoryFinder {

    private static final Logger logger = Logger.getLogger("jakarta.xml.ws");

    private static final ServiceLoaderUtil.ExceptionHandler<WebServiceException> EXCEPTION_HANDLER =
            new ServiceLoaderUtil.ExceptionHandler<WebServiceException>() {
                @Override
                public WebServiceException createException(Throwable throwable, String message) {
                    return new WebServiceException(message, throwable);
                }
            };

    /**
     * Finds the implementation {@code Class} object for the given
     * factory name.
     * <P>
     * This method is package private so that this code can be shared.
     *
     * @return the {@code Class} object of the specified message factory;
     *         may not be {@code null}
     *
     * @param factoryClass          the name of the factory to find, which is
     *                              a system property
     * @exception WebServiceException if provider not found
     *
     */
    @SuppressWarnings("unchecked")
    static <T> T find(Class<T> factoryClass) {
        String factoryId = factoryClass.getName();
        ClassLoader classLoader = ServiceLoaderUtil.contextClassLoader(EXCEPTION_HANDLER);
        T provider = ServiceLoaderUtil.firstByServiceLoader(factoryClass, logger, EXCEPTION_HANDLER);

        // Use the system property
        if (provider == null) {
            provider = (T) fromSystemProperty(factoryId, classLoader);
        }

        // handling Glassfish (platform specific default)
        if (provider == null && isOsgi()) {
            provider = (T) lookupUsingOSGiServiceLoader(factoryId);
        }

        if (provider == null) {
            throw new WebServiceException(
                    "Provider for " + factoryId + " cannot be found", null);
        }

        return provider;
    }

    private static Object fromSystemProperty(String factoryId,
                                             ClassLoader classLoader) {
        try {
            String systemProp = System.getProperty(factoryId);
            if (systemProp != null) {
                return ServiceLoaderUtil.newInstance(systemProp,
                        null, classLoader, EXCEPTION_HANDLER);
            }
        } catch (SecurityException ignored) {
        }
        return null;
    }

    private static final String OSGI_SERVICE_LOADER_CLASS_NAME = "org.glassfish.hk2.osgiresourcelocator.ServiceLoader";

    private static boolean isOsgi() {
        try {
            Class.forName(OSGI_SERVICE_LOADER_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException ignored) {
        }
        return false;
    }

    private static Object lookupUsingOSGiServiceLoader(String factoryId) {
        try {
            // Use reflection to avoid having any dependendcy on ServiceLoader class
            Class serviceClass = Class.forName(factoryId);
            Class[] args = new Class[]{serviceClass};
            Class target = Class.forName(OSGI_SERVICE_LOADER_CLASS_NAME);
            java.lang.reflect.Method m = target.getMethod("lookupProviderInstances", Class.class);
            java.util.Iterator iter = ((Iterable) m.invoke(null, (Object[]) args)).iterator();
            return iter.hasNext() ? iter.next() : null;
        } catch (Exception ignored) {
            // log and continue
            return null;
        }
    }

}
