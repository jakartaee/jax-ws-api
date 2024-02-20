/*
 * Copyright (c) 2015, 2024 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.spi;

import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Shared ServiceLoader/FactoryFinder Utils shared among Jakarta SOAP with Attachments, Jakarta XML Binding
 * and Jakarta XML Web Services.
 * Class duplicated to all those projects.
 *
 * @author Miroslav.Kos@oracle.com
 */
class ServiceLoaderUtil {

    static <P, T extends Exception> P firstByServiceLoader(Class<P> spiClass,
                                                           Logger logger,
                                                           ExceptionHandler<T> handler) throws T {
        logger.log(Level.FINE, "Using java.util.ServiceLoader to find {0}", spiClass.getName());
        // service discovery
        try {
            ServiceLoader<P> serviceLoader = ServiceLoader.load(spiClass);

            for (P impl : serviceLoader) {
                logger.fine("ServiceProvider loading Facility used; returning object [" +
                        impl.getClass().getName() + "]");

                return impl;
            }
        } catch (Throwable t) {
            throw handler.createException(t, "Error while searching for service [" + spiClass.getName() + "]");
        }
        return null;
    }

    static void checkPackageAccess(String className) {
        // make sure that the current thread has the access to the package of the given name.
        SecurityManager s = System.getSecurityManager();
        if (s != null) {
            int i = className.lastIndexOf('.');
            if (i != -1) {
                s.checkPackageAccess(className.substring(0, i));
            }
        }
    }

    @SuppressWarnings({"unchecked"})
    static <T> Class<T> nullSafeLoadClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
        if (classLoader == null) {
            return (Class<T>) Class.forName(className);
        } else {
            return (Class<T>) classLoader.loadClass(className);
        }
    }

    // Returns instance of required class. It checks package access (security)
    // unless it is defaultClassname. It means if you are trying to instantiate
    // default implementation (fallback), pass the class name to both first and second parameter.
    static <T, E extends Exception> T newInstance(String className,
                                                    String defaultImplClassName, ClassLoader classLoader,
                                                    final ExceptionHandler<E> handler) throws E {
        try {
            Class<T> cls = safeLoadClass(className, defaultImplClassName, classLoader);
            return cls.getConstructor().newInstance();
        } catch (ClassNotFoundException x) {
            throw handler.createException(x, "Provider " + className + " not found");
        } catch (Exception x) {
            throw handler.createException(x, "Provider " + className + " could not be instantiated: " + x);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <T> Class<T> safeLoadClass(String className,
                               String defaultImplClassName,
                               ClassLoader classLoader) throws ClassNotFoundException {

        try {
            checkPackageAccess(className);
        } catch (SecurityException se) {
            // anyone can access the platform default factory class without permission
            if (defaultImplClassName != null && defaultImplClassName.equals(className)) {
                return (Class<T>) Class.forName(className);
            }
            // not platform default implementation ...
            throw se;
        }
        return nullSafeLoadClass(className, classLoader);
    }

    static <T extends Exception> ClassLoader contextClassLoader(ExceptionHandler<T> exceptionHandler) throws T {
        try {
            return Thread.currentThread().getContextClassLoader();
        } catch (Exception x) {
            throw exceptionHandler.createException(x, x.toString());
        }
    }

    static abstract class ExceptionHandler<T extends Exception> {

        public abstract T createException(Throwable throwable, String message);

    }

}
