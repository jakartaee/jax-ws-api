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

import jakarta.xml.ws.soap.Addressing;
import jakarta.xml.ws.spi.WebServiceFeatureAnnotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The {@code WebServiceRef} annotation is used to
 * define a reference to a web service and
 * (optionally) an injection target for it.
 * It can be used to inject both service and proxy
 * instances. These injected references are not thread safe.
 * If the references are accessed by multiple threads,
 * usual synchronization techniques can be used to
 * support multiple threads.
 *
 * <p>
 * Web service references are resources in the Jakarta EE sense.
 * The annotations (for example, {@link Addressing}) annotated with
 * meta-annotation {@link WebServiceFeatureAnnotation}
 * can be used in conjunction with {@code WebServiceRef}.
 * The created reference MUST be configured with annotation's web service
 * feature.
 *
 * <p>
 * For example, in the code below, the injected
 * {@code StockQuoteProvider} proxy MUST
 * have WS-Addressing enabled as specified by the
 * {@link Addressing}
 * annotation.
 *
 * {@snippet :
 *  public class MyClient {
 *      @Addressing
 *      @WebServiceRef(StockQuoteService.class)
 *      private StockQuoteProvider stockQuoteProvider;
 *      ...
 *  }
 * }
 *
 * <p>
 * If a Jakarta XML Web Services implementation encounters an unsupported or unrecognized
 * annotation annotated with the {@code WebServiceFeatureAnnotation}
 * that is specified with {@code WebServiceRef}, an ERROR MUST be given.
 *
 * @see "jakarta.annotation.Resource"
 * @see WebServiceFeatureAnnotation
 *
 * @since 1.6, JAX-WS 2.0
 *
 **/

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(WebServiceRefs.class)
public @interface WebServiceRef {
    /**
     * The JNDI name of the resource.  For field annotations,
     * the default is the field name.  For method annotations,
     * the default is the JavaBeans property name corresponding
     * to the method.  For class annotations, there is no default
     * and this MUST be specified.
     * <p>
     * The JNDI name can be absolute(with any logical namespace) or relative
     * to JNDI {@code java:comp/env} namespace.
     *
     * @return absolute or relative JNDI name
     */
    String name() default "";

    /**
     * The Java type of the resource.  For field annotations,
     * the default is the type of the field.  For method annotations,
     * the default is the type of the JavaBeans property.
     * For class annotations, there is no default and this MUST be
     * specified.
     *
     * @return type of the resource
     */
    Class<?> type() default Object.class;

    /**
     * A product specific name that this resource should be mapped to.
     * The name of this resource, as defined by the {@code name}
     * element or defaulted, is a name that is local to the application
     * component using the resource.  (When a relative JNDI name
     * is specified, then it's a name in the JNDI
     * {@code java:comp/env} namespace.)  Many application servers
     * provide a way to map these local names to names of resources
     * known to the application server.  This mapped name is often a
     * <i>global</i> JNDI name, but may be a name of any form.
     * <p>
     * Application servers are not required to support any particular
     * form or type of mapped name, nor the ability to use mapped names.
     * The mapped name is product-dependent and often installation-dependent.
     * No use of a mapped name is portable.
     *
     * @return product specific resource name
     */
    String mappedName() default "";

    /**
     * The service class, always a type extending
     * {@code jakarta.xml.ws.Service}. This element MUST be specified
     * whenever the type of the reference is a service endpoint interface.
     *
     * @return the service class extending {@code jakarta.xml.ws.Service}
     */
     // 2.1 has Class value() default Object.class;
     // Fixing this raw Class type correctly in 2.2 API. This shouldn't cause
     // any compatibility issues for applications.
    Class<? extends Service> value() default Service.class;

    /**
     * A URL pointing to the WSDL document for the web service.
     * If not specified, the WSDL location specified by annotations
     * on the resource type is used instead.
     *
     * @return a URL pointing to the WSDL document
     */
    String wsdlLocation() default "";

    /**
     * A portable JNDI lookup name that resolves to the target
     * web service reference.
     *
     * @return portable JNDI lookup name
     * @since 1.7, JAX-WS 2.2
     */
    String lookup() default "";

}
