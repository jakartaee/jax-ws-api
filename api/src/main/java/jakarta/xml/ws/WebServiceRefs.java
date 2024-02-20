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

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * The {@code WebServiceRefs} annotation allows
 * multiple web service references to be declared at the
 * class level.
 *
 * <p>
 * It can be used to inject both service and proxy
 * instances. These injected references are not thread safe.
 * If the references are accessed by multiple threads,
 * usual synchronization techniques can be used to
 * support multiple threads.
 *
 * <p>
 * There is no way to associate web service features with
 * the injected instances. If an instance needs to be
 * configured with web service features, use @WebServiceRef
 * to inject the resource along with its features.
 *
 * <p>
 * <b>Example</b>: The {@code StockQuoteProvider}
 * proxy instance, and the {@code StockQuoteService} service
 * instance are injected using @WebServiceRefs.
 *
 * {@snippet :
 *  @WebServiceRefs({
 *      @WebServiceRef(name="service/stockquoteservice", value=StockQuoteService.class),
 *      @WebServiceRef(name="service/stockquoteprovider", type=StockQuoteProvider.class, value=StockQuoteService.class
 *  })
 *  public class MyClient {
 *      void init() {
 *          Context ic = new InitialContext();
 *          StockQuoteService service = (StockQuoteService) ic.lookup("java:comp/env/service/stockquoteservice");
 *          StockQuoteProvider port = (StockQuoteProvider) ic.lookup("java:comp/env/service/stockquoteprovider");
 *          ...
 *      }
 *      ...
 *  }
 * }
 *
 * @see WebServiceRef
 * @since 1.6, JAX-WS 2.0
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface WebServiceRefs {
   /**
    * Array used for multiple web service reference declarations.
    *
    * @return multiple web service reference declarations
    */
   WebServiceRef[] value();
}
