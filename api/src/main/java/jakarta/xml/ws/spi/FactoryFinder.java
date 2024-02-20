/*
 * Copyright (c) 2005, 2024 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.spi;

import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.xml.ws.WebServiceException;

class FactoryFinder {

    private static final Logger LOGGER;

    private static final ServiceLoaderUtil.ExceptionHandler<WebServiceException> EXCEPTION_HANDLER =
            new ServiceLoaderUtil.ExceptionHandler<>() {
                @Override
                public WebServiceException createException(Throwable throwable, String message) {
                    return new WebServiceException(message, throwable);
                }
            };

    private final static PrivilegedAction<String> propertyAction = () -> System.getProperty("jax-ws.debug");

    static {
        LOGGER = Logger.getLogger("jakarta.xml.ws");
        try {
            if (AccessController.doPrivileged(propertyAction) != null) {
                // disconnect the logger from a bigger framework (if any)
                // and take the matters into our own hands
                LOGGER.setUseParentHandlers(false);
                LOGGER.setLevel(Level.ALL);
                ConsoleHandler handler = new ConsoleHandler();
                handler.setLevel(Level.ALL);
                LOGGER.addHandler(handler);
            } else {
                // don't change the setting of this logger
                // to honor what other frameworks
                // have done on configurations.
            }
        } catch (Throwable t) {
            // just to be extra safe. in particular System.getProperty may throw
            // SecurityException.
            LOGGER.log(Level.SEVERE, "Exception during loading the class", t);
        }
    }
    /**
     * Finds the implementation {@code Class} object for the given
     * factory name, or if that fails, finds the {@code Class} object
     * for the given fallback class name. The arguments supplied MUST be
     * used in order. If using the first argument is successful, the second
     * one will not be used.
     * <P>
     * This method is package private so that this code can be shared.
     *
     * @param <T>                   type of the factory class
     * @param factoryClass          the name of the factory to find, which is
     *                              a system property
     * @param fallbackClassName     the implementation class name, which is
     *                              to be used only if nothing else
     *                              is found; {@code null} to indicate that
     *                              there is no fallback class name
     * @return the {@code Class} object of the specified message factory;
     *         may not be {@code null}
     *
     * @exception WebServiceException if there is an error
     */
    @SuppressWarnings("unchecked")
    static <T> T find(Class<T> factoryClass, String fallbackClassName) {
        ClassLoader classLoader = ServiceLoaderUtil.contextClassLoader(EXCEPTION_HANDLER);

        String factoryId = factoryClass.getName();

        // Use the system property
        T provider = fromSystemProperty(factoryId, fallbackClassName, classLoader);
        if (provider != null) return provider;

        provider = ServiceLoaderUtil.firstByServiceLoader(factoryClass, LOGGER, EXCEPTION_HANDLER);
        if (provider != null) return provider;

        // handling Glassfish (platform specific default)
        if (isOsgi()) {
            provider = lookupUsingOSGiServiceLoader(factoryId);
            if (provider != null) {
                return provider;
            }
        }

        if (fallbackClassName == null) {
            throw new WebServiceException(
                "Provider for " + factoryId + " cannot be found", null);
        }

        return ServiceLoaderUtil.newInstance(fallbackClassName,
                fallbackClassName, classLoader, EXCEPTION_HANDLER);
    }

    private static <T> T fromSystemProperty(String factoryId,
                                             String fallbackClassName,
                                             ClassLoader classLoader) {
        try {
            String systemProp = System.getProperty(factoryId);
            if (systemProp != null) {
                LOGGER.log(Level.FINE, "Found system property {0}", systemProp);
                return newInstance(systemProp, fallbackClassName, classLoader);
            }
        } catch (SecurityException se) {
            LOGGER.log(Level.SEVERE, "Access is not allowed to the system property with key " + factoryId, se);
        }
        return null;
    }

    private static final String OSGI_SERVICE_LOADER_CLASS_NAME = "org.glassfish.hk2.osgiresourcelocator.ServiceLoader";

    private static boolean isOsgi() {
        try {
            Class.forName(OSGI_SERVICE_LOADER_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException cnfe) {
            LOGGER.log(
                    Level.SEVERE,
                    "Class " + OSGI_SERVICE_LOADER_CLASS_NAME + " cannot be loaded",
                    cnfe
            );
        }
        return false;
    }

    private static <T> T lookupUsingOSGiServiceLoader(String factoryId) {
        try {
            LOGGER.fine("Trying to create the provider from the OSGi ServiceLoader");
            // Use reflection to avoid having any dependency on ServiceLoader class
            Class<?> serviceClass = Class.forName(factoryId);
            Class<?>[] args = new Class<?>[]{serviceClass};
            Class<?> target = Class.forName(OSGI_SERVICE_LOADER_CLASS_NAME);
            java.lang.reflect.Method m = target.getMethod("lookupProviderInstances", Class.class);
            @SuppressWarnings({"unchecked"})
            java.util.Iterator<? extends T> iter = ((Iterable<? extends T>) m.invoke(null, (Object[]) args)).iterator();
            return iter.hasNext() ? iter.next() : null;
        } catch (Throwable t) {
            // log and continue
            LOGGER.log(
                    Level.SEVERE,
                    "Access to the system property with key " + factoryId + " is not allowed",
                    t
            );
            return null;
        }
    }

    private static <T> T newInstance(String className, String defaultImplClassName, ClassLoader classLoader){
        @SuppressWarnings({"unchecked"})
        T newInstance = ServiceLoaderUtil.newInstance(className,
                defaultImplClassName, classLoader, EXCEPTION_HANDLER);
        if (LOGGER.isLoggable(Level.FINE)) {
            // extra check to avoid costly which operation if not logged
            Class<?> newInstanceClass = newInstance.getClass();
            LOGGER.log(
                    Level.FINE,
                    "loaded {0} from {1}", new Object[]{newInstanceClass.getName(), which(newInstanceClass)}
            );
        }
        return newInstance;
    }

    /**
     * Get the URL for the Class from it's ClassLoader.
     * <p>
     * Convenience method for {@link #which(Class, ClassLoader)}.
     * <p>
     * Equivalent to calling: which(clazz, clazz.getClassLoader())
     *
     * @param clazz
     *          The class to search for
     * @return
     *          the URL for the class or null if it wasn't found
     */
    static URL which(Class<?> clazz) {
        return which(clazz, getClassClassLoader(clazz));
    }

    /**
     * Search the given ClassLoader for an instance of the specified class and
     * return a string representation of the URL that points to the resource.
     *
     * @param clazz
     *          The class to search for
     * @param loader
     *          The ClassLoader to search.  If this parameter is null, then the
     *          system class loader will be searched
     * @return
     *          the URL for the class or null if it wasn't found
     */
    static URL which(Class<?> clazz, ClassLoader loader) {

        String classnameAsResource = clazz.getName().replace('.', '/') + ".class";

        if (loader == null) {
            loader = getSystemClassLoader();
        }

        return loader.getResource(classnameAsResource);
    }

    private static ClassLoader getSystemClassLoader() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        } else {
            return AccessController.doPrivileged(
                    (PrivilegedAction<ClassLoader>) ClassLoader::getSystemClassLoader);
        }
    }

    private static ClassLoader getClassClassLoader(final Class<?> c) {
        if (System.getSecurityManager() == null) {
            return c.getClassLoader();
        } else {
            return AccessController.doPrivileged(
                    (PrivilegedAction<ClassLoader>) c::getClassLoader);
        }
    }

}
