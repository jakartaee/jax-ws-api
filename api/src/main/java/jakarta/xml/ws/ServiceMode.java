/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
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
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Inherited;

/**
 * Used to indicate whether a {@link Provider} implementation wishes to work
 * with entire protocol messages or just with protocol message payloads.
 *
 *  @since 1.6, JAX-WS 2.0
**/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ServiceMode {
  /**
   * Service mode. {@code PAYLOAD} indicates that the {@code Provider} implementation
   * wishes to work with protocol message payloads only. {@code MESSAGE} indicates
   * that the {@code Provider} implementation wishes to work with entire protocol
   * messages.
   *
   * @return Service mode.
   **/
  public Service.Mode value() default Service.Mode.PAYLOAD;
}
