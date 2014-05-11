/*
 * #%L
 * Wisdom-Framework
 * %%
 * Copyright (C) 2013 - 2014 Wisdom Framework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.wisdom.router.parameter;

import org.wisdom.api.content.ParameterConverters;
import org.wisdom.api.http.Context;
import org.wisdom.api.router.RouteUtils;

/**
 * The handler managing @Parameter.
 */
public class ParameterHandler implements RouteParameterHandler {

    @Override
    public Object create(RouteUtils.Argument argument, Context context, ParameterConverters engine) {
        // First try from path.
        String value = context.parameterFromPath(argument.getName());
        if (value != null) {
            return engine.convertValue(value, argument.getRawType(), argument.getGenericType(), argument.defaultValue());
        }

        // If not in path, check whether we can handle multiple-values.
        if (Bindings.supportMultipleValues(argument.getRawType())) {
            return engine.convertValues(context.parameterMultipleValues(argument.getName()), argument.getRawType(),
                    argument.getGenericType(), argument.defaultValue());
        } else {
            return engine.convertValue(context.parameter(argument.getName()), argument.getRawType(), argument.getGenericType(),
                    argument.defaultValue());
        }
    }
}
