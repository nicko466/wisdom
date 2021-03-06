/*
 * #%L
 * Wisdom-Framework
 * %%
 * Copyright (C) 2015 Wisdom Framework
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

package org.wisdom.source.ast.model;

import org.wisdom.source.ast.visitor.Visitor;

/**
 * Model of a wisdom route parameter.
 *
 * @author barjo
 * @param <T> the type of the parameter pass to the visitor.
 */
public class RouteParamModel<T> implements Model<T> {

    private String paramName;

    private String name;

    private String valueType;

    private String defaultValue;

    private ParamType paramType = null;

    /**
     * Get the name of this parameter. (Given through the annotation, or like the {@link #getParamName()}).
     * @return The parameter name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this parameter.
     * @param name the parameter name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The type of the parameter value (java type as String).
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * Set this parameter value type.
     * @param valueType The parameter value type as String. (Java type).
     */
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    /**
     * @return this parameter type.
     */
    public ParamType getParamType() {
        return paramType;
    }

    /**
     * Set this parameter ParamType.
     * @param type The ParamType.
     */
    public void setParamType(ParamType type) {
        this.paramType = type;
    }

    /**
     * Get the default value of this parameter. As annotated by {@link org.wisdom.api.annotations.DefaultValue}.
     * @return the default value.
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Set the default value of this parameter.
     * @param defaultValue the default value.
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Get this parameter name. (from its java name).
     * @return this parameter name
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Set the name of this parameter.
     * @param paramName the parameter name.
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(Visitor visitor, T anything) {
        visitor.visit(this,anything);
    }

    /**
     * The known parameter type.
     */
    public enum ParamType{
        BODY,QUERY,PARAM,FORM,PATH_PARAM
    }
}
