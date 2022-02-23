/*
 * Copyright (c) 2005, 2022 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws.soap;

import jakarta.xml.ws.WebServiceFeature;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.Service;

/**
 * This feature represents the use of MTOM with a 
 * web service.
 *
 * This feature can be used during the creation of SEI proxy, and
 * {@link jakarta.xml.ws.Dispatch} instances on the client side and {@link Endpoint}
 * instances on the server side. This feature cannot be used for {@link Service}
 * instance creation on the client side.
 *
 * <p>
 * The following describes the affects of this feature with respect
 * to being enabled or disabled:
 * <ul>
 *  <li> ENABLED: In this Mode, MTOM will be enabled. A receiver MUST accept
 * both a non-optimized and an optimized message, and a sender MAY send an
 * optimized message, or a non-optimized message. The heuristics used by a
 * sender to determine whether to use optimization or not are
 * implementation-specific.
 *  <li> DISABLED: In this Mode, MTOM will be disabled
 * </ul>
 * <p>
 * The {@link #threshold} property can be used to set the threshold 
 * value used to determine when binary data should be XOP encoded.
 *
 * @since 1.6, JAX-WS 2.1
 */       
public final class MTOMFeature extends WebServiceFeature {
    /** 
     * Constant value identifying the MTOMFeature
     */
    public static final String ID = "http://www.w3.org/2004/08/soap/features/http-optimization";
  
   
    /**
     * Property for MTOM threshold value. This property serves as a hint when 
     * MTOM is enabled, binary data above this size in bytes SHOULD be sent 
     * as attachment.
     * The value of this property MUST always be {@literal >=} 0. Default value is 0.
     */
    private int threshold;
    

    /**
     * Create an {@code MTOMFeature}.
     * The instance created will be enabled.
     */
    public MTOMFeature() {
        this.enabled = true;
        this.threshold = 0;
    }
    
    /**
     * Creates a {@code MTOMFeature}.
     * 
     * @param enabled specifies if this feature should be enabled or not
     */
    public MTOMFeature(boolean enabled) {
        this.enabled = enabled;
        this.threshold = 0;
    }


    /**
     * Creates a {@code MTOMFeature}.
     * The instance created will be enabled.
     *
     * @param threshold the size in bytes that binary data SHOULD be before
     * being sent as an attachment.
     *
     * @throws WebServiceException if threshold is {@literal <} 0
     */
    public MTOMFeature(int threshold) {
        if (threshold < 0)
            throw new WebServiceException("MTOMFeature.threshold must be >= 0, actual value: "+threshold);
        this.enabled = true;
        this.threshold = threshold;
    }    
    
    /**
     * Creates a {@code MTOMFeature}.
     * 
     * @param enabled specifies if this feature should be enabled or not
     * @param threshold the size in bytes that binary data SHOULD be before
     * being sent as an attachment.
     *
     * @throws WebServiceException if threshold is {@literal <} 0
     */
    public MTOMFeature(boolean enabled, int threshold) {
        if (threshold < 0)
            throw new WebServiceException("MTOMFeature.threshold must be >= 0, actual value: "+threshold);
        this.enabled = enabled;
        this.threshold = threshold;
    }    
    
    @Override
    public String getID() {
        return ID;
    }
    
    /**
     * Gets the threshold value used to determine when binary data 
     * should be sent as an attachment.
     *
     * @return the current threshold size in bytes
     */
    public int getThreshold() {
        return threshold;
    }
}
