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


/**
 * A WebServiceFeature is used to represent a feature that can be
 * enabled or disabled for a web service.
 * <p>
 * The Jakarta XML Web Services specification will define some standard features and
 * Jakarta XML Web Services implementors are free to define additional features if
 * necessary.  Vendor specific features may not be portable so
 * caution should be used when using them. Each Feature definition
 * MUST define a {@code public static final String ID}
 * that can be used in the Feature annotation to refer
 * to the feature. This ID MUST be unique across all features
 * of all vendors.  When defining a vendor specific feature ID,
 * use a vendor specific namespace in the ID string.
 *
 * @see jakarta.xml.ws.RespectBindingFeature
 * @see jakarta.xml.ws.soap.AddressingFeature
 * @see jakarta.xml.ws.soap.MTOMFeature
 *
 * @since 1.6, JAX-WS 2.1
 */
public abstract class WebServiceFeature {
   /**
    * Each Feature definition MUST define a public static final
    * String ID that can be used in the Feature annotation to refer
    * to the feature.
    */
   // public static final String ID = "some unique feature Identifier";

   /**
    * Get the unique identifier for this WebServiceFeature.
    *
    * @return the unique identifier for this feature.
    */
   public abstract String getID();

   /**
    * Specifies if the feature is enabled or disabled
    */
   protected boolean enabled = false;

    /**
     * Default constructor.
     */
    protected WebServiceFeature() {}

   /**
    * Returns {@code true} if this feature is enabled.
    *
    * @return {@code true} if and only if the feature is enabled .
    */
   public boolean isEnabled() {
       return enabled;
   }
}
