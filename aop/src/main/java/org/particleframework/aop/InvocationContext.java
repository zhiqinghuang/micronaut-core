/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.particleframework.aop;

import org.particleframework.core.convert.ConvertibleMultiValues;
import org.particleframework.core.convert.MutableConvertibleMultiValues;
import org.particleframework.inject.ArgumentValue;
import org.particleframework.inject.ExecutionHandle;
import org.particleframework.inject.MutableArgumentValue;

import java.util.List;
import java.util.Map;

/**
 * <p>An InvocationContext passed to one or many {@link Interceptor} instances. Attributes can be stored within the context and
 * shared between multiple {@link Interceptor} implementations. The {@link #proceed()} method should be called to proceed to
 * the next {@link Interceptor} with the last interceptor in the chain being the original decorated method implementation.</p>
 *
 * <p>The parameters to pass to the next {@link Interceptor} can be mutated using {@link MutableArgumentValue} interface returned by the {@link #getParameters()} method</p>
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public interface InvocationContext<T> extends ExecutionHandle<T>, MutableConvertibleMultiValues<Object> {

    /**
     * @return The bound {@link ArgumentValue} instances
     */
    Map<String, MutableArgumentValue<?>> getParameters();

    /**
     * @return The bound {@link ArgumentValue} instances
     */
    Object[] getParameterValues();

    /**
     * @return The target object
     */
    Object getTarget();

    /**
     * Proceeds with the invocation. If this is the last interceptor in the chain then the final implementation method is invoked
     *
     * @return The return value of the method
     */
    T proceed() throws RuntimeException;
}