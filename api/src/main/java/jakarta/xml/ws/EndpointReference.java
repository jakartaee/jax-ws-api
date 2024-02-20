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

import jakarta.xml.bind.annotation.XmlTransient;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import jakarta.xml.ws.spi.Provider;
import jakarta.xml.ws.wsaddressing.W3CEndpointReference;
import java.io.StringWriter;

/**
 * This class represents an WS-Addressing EndpointReference
 * which is a remote reference to a web service endpoint.
 * See <a href="http://www.w3.org/TR/2006/REC-ws-addr-core-20060509/">
 * Web Services Addressing 1.0 - Core</a>
 * for more information on WS-Addressing EndpointReferences.
 * <p>
 * This class is immutable as the typical web service developer
 * need not be concerned with its contents.  The web service
 * developer should use this class strictly as a mechanism to
 * reference a remote web service endpoint. See the {@link Service} APIs
 * that clients can use to that utilize an {@code EndpointReference}.
 * See the {@link jakarta.xml.ws.Endpoint}, and
 * {@link jakarta.xml.ws.BindingProvider} APIs on how
 * {@code EndpointReferences} can be created for published
 * endpoints.
 * <p>
 * Concrete implementations of this class will represent
 * an {@code EndpointReference} for a particular version of Addressing.
 * For example the {@link W3CEndpointReference} is for use
 * with W3C Web Services Addressing 1.0 - Core Recommendation.
 * If Jakarta XML Web Services implementors need to support different versions
 * of addressing, they should write their own
 * {@code EndpointReference} subclass for that version.
 * This will allow a Jakarta XML Web Services implementation to create
 * a vendor specific {@code EndpointReferences} that the
 * vendor can use to flag a different version of
 * addressing.
 * <p>
 * Web service developers that wish to pass or return
 * {@code EndpointReference} in Java methods in an
 * SEI should use
 * concrete instances of an {@code EndpointReference} such
 * as the {@code W3CEndpointReference}.  This way the
 * schema mapped from the SEI will be more descriptive of the
 * type of endpoint reference being passed.
 * <p>
 * Jakarta XML Web Services implementors are expected to extract the XML infoset
 * from an {@code EndpointReference} using the
 * {@link EndpointReference#writeTo}
 * method.
 * <p>
 * Jakarta XML Binding will bind this class to xs:anyType. If a better binding
 * is desired, web services developers should use a concrete
 * subclass such as {@link W3CEndpointReference}.
 *
 * @see W3CEndpointReference
 * @see Service
 * @since 1.6, JAX-WS 2.1
 */
@XmlTransient // to treat this class like Object as far as databinding is concerned (proposed JAXB 2.1 feature)
public abstract class EndpointReference {
    //
    //Default constructor to be only called by derived types.
    //

    /**
     * Default constructor.
     */
    protected EndpointReference(){}

    /**
     * Factory method to read an EndpointReference from the infoset contained in
     * {@code eprInfoset}. This method delegates to the vendor specific
     * implementation of the {@link jakarta.xml.ws.spi.Provider#readEndpointReference} method.
     *
     * @param eprInfoset The {@code EndpointReference} infoset to be unmarshalled
     *
     * @return the EndpointReference unmarshalled from {@code eprInfoset}
     *    never {@code null}
     * @throws WebServiceException
     *    if an error occurs while creating the
     *    {@code EndpointReference} from the {@code eprInfoset}
     * @throws java.lang.IllegalArgumentException
     *     if the {@code null} {@code eprInfoset} value is given.
     */
    public static EndpointReference readFrom(Source eprInfoset) {
        return Provider.provider().readEndpointReference(eprInfoset);
    }

    /**
     * write this {@code EndpointReference} to the specified infoset format
     *
     * @param result for writing infoset
     * @throws WebServiceException
     *   if there is an error writing the
     *   {@code EndpointReference} to the specified {@code result}.
     *
     * @throws java.lang.IllegalArgumentException
     *      If the {@code null} {@code result} value is given.
     */
    public abstract void writeTo(Result result);


    /**
     * The {@code getPort} method returns a proxy. If there
     * are any reference parameters in the 
     * {@code EndpointReference} instance, then those reference
     * parameters MUST appear as SOAP headers, indicating them to be
     * reference parameters, on all messages sent to the endpoint.
     * The parameter  {@code serviceEndpointInterface} specifies
     * the service endpoint interface that is supported by the
     * returned proxy.
     * The {@code EndpointReference} instance specifies the
     * endpoint that will be invoked by the returned proxy.
     * In the implementation of this method, the Jakarta XML Web Services
     * runtime system takes the responsibility of selecting a protocol
     * binding (and a port) and configuring the proxy accordingly from
     * the WSDL Metadata from this {@code EndpointReference} or from
     * annotations on the {@code serviceEndpointInterface}.  For this method
     * to successfully return a proxy, WSDL metadata MUST be available and the
     * {@code EndpointReference} instance MUST contain an implementation understood
     * {@code serviceName} metadata.
     * <p>
     * Because this port is not created from a {@code Service} object, handlers
     * will not automatically be configured, and the {@code HandlerResolver}
     * and {@code Executor} cannot be get or set for this port. The
     * {@code BindingProvider().getBinding().setHandlerChain()}
     * method can be used to manually configure handlers for this port.
     *
     *
     * @param <T> Service endpoint interface
     * @param serviceEndpointInterface Service endpoint interface
     * @param features  An array of {@code WebServiceFeatures} to configure on the
     *                proxy.  Supported features not in the {@code features
     *                } parameter will have their default values.
     * @return Object Proxy instance that supports the
     *                  specified service endpoint interface
     * @throws WebServiceException
     *                  <UL>
     *                  <LI>If there is an error during creation
     *                      of the proxy
     *                  <LI>If there is any missing WSDL metadata
     *                      as required by this method
     *                  <LI>If this
     *                      {@code endpointReference}
     *                      is invalid
     *                  <LI>If an illegal
     *                      {@code serviceEndpointInterface}
     *                      is specified
     *                  <LI>If a feature is enabled that is not compatible with
     *                      this port or is unsupported.
     *                   </UL>
     *
     * @see java.lang.reflect.Proxy
     * @see WebServiceFeature
     **/
    public <T> T getPort(Class<T> serviceEndpointInterface,
                         WebServiceFeature... features) {
        return Provider.provider().getPort(this, serviceEndpointInterface,
                                           features);
    }

    /**
     * Displays EPR infoset for debugging convenience.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        StringWriter w = new StringWriter();
        writeTo(new StreamResult(w));
        return w.toString();
    }
}
