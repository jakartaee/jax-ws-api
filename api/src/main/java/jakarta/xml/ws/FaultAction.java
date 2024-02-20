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
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code FaultAction} annotation is used inside an {@link Action}
 * annotation to allow an explicit association of a WS-Addressing
 * {@code Action} message addressing property with the {@code fault}
 * messages of the WSDL operation mapped from the exception class.
 * <p>
 * The {@code wsam:Action} attribute value in the {@code fault}
 * message in the generated WSDL operation mapped for {@code className}
 * class is equal to the corresponding value in the {@code FaultAction}.
 * For the exact computation of {@code wsam:Action} values for the
 * fault messages, refer to the algorithm in the Jakarta XML Web Services specification.
 *
 * <p>
 * <b>Example 1</b>: Specify explicit values for {@code Action} message addressing
 * property for the {@code input}, {@code output} and {@code fault} message
 * if the Java method throws only one service specific exception.
 *
 * {@snippet :
 *  @WebService(targetNamespace="http://example.com/numbers")
 *  public class AddNumbersImpl {
 *      @Action(
 *          fault = {
 *              @FaultAction(className=AddNumbersException.class, value="http://example.com/faultAction")   // @highlight type="bold"
 *          })
 *      public int addNumbers(int number1, int number2) throws AddNumbersException {
 *          return number1 + number2;
 *      }
 *  }
 * }
 *
 * The generated WSDL looks like:
 *
 * {@snippet lang="XML" :
 *  <definitions targetNamespace="http://example.com/numbers" ...>
 *    ...
 *    <portType name="AddNumbersPortType">
 *      <operation name="AddNumbers">
 *        ...
 *        <fault message="tns:AddNumbersException" name="AddNumbersException"
 *          wsam:Action="http://example.com/faultAction" />   // @highlight type="bold"
 *      </operation>
 *    </portType>
 *    ...
 *  </definitions>
 * }
 *
 * <p>
 * Example 2: Here is an example that shows if the explicit value for {@code Action}
 * message addressing property for the service specific exception is not present.
 *
 * {@snippet :
 *  @WebService(targetNamespace="http://example.com/numbers")
 *  public class AddNumbersImpl {
 *      public int addNumbers(int number1, int number2) throws AddNumbersException {
 *          return number1 + number2;
 *      }
 *  }
 * }
 *
 * The generated WSDL looks like:
 *
 * {@snippet lang="XML" :
 *  <definitions targetNamespace="http://example.com/numbers" ...>
 *    ...
 *    <portType name="AddNumbersPortType">
 *      <operation name="AddNumbers">
 *        ...
 *        <fault message="tns:addNumbersFault" name="InvalidNumbers"
 *          wsam:Action="http://example.com/numbers/AddNumbersPortType/AddNumbers/Fault/AddNumbersException" />   // @highlight type="bold"
 *      </operation>
 *    </portType>
 *    ...
 *  </definitions>
 * }
 *
 * <p>
 * Example 3: Here is an example that shows how to specify explicit values for {@code Action}
 * message addressing property if the Java method throws more than one service specific exception.
 *
 * {@snippet :
 *  @WebService(targetNamespace="http://example.com/numbers")
 *  public class AddNumbersImpl {
 *      @Action(
 *          fault = {
 *              @FaultAction(className=AddNumbersException.class, value="http://example.com/addFaultAction"),   // @highlight type="bold"
 *              @FaultAction(className=TooBigNumbersException.class, value="http://example.com/toobigFaultAction")   // @highlight type="bold"
 *          })
 *      public int addNumbers(int number1, int number2) throws AddNumbersException, TooBigNumbersException {
 *         return number1 + number2;
 *      }
 *  }
 * }
 *
 * The generated WSDL looks like:
 *
 * {@snippet lang="XML" :
 *  <definitions targetNamespace="http://example.com/numbers" ...>
 *    ...
 *    <portType name="AddNumbersPortType">
 *      <operation name="AddNumbers">
 *        ...
 *        <fault message="tns:addNumbersFault" name="AddNumbersException"
 *          wsam:Action="http://example.com/addFaultAction" />   // @highlight type="bold"
 *        <fault message="tns:tooBigNumbersFault" name="TooBigNumbersException"
 *          wsam:Action="http://example.com/toobigFaultAction" />   // @highlight type="bold"
 *      </operation>
 *    </portType>
 *    ...
 *  </definitions>
 * }
 *
 * @since 1.6, JAX-WS 2.1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FaultAction {
    /**
     * Name of the exception class.
     *
     * @return the name of the exception class
     */
    Class<? extends Exception> className();

    /**
     * Value of WS-Addressing {@code Action} message addressing property for the exception.
     *
     * @return WS-Addressing {@code Action} message addressing property for the exception
     */
    String value() default "";
}
