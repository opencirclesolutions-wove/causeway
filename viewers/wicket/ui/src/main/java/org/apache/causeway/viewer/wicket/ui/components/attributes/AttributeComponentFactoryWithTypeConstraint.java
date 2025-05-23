/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.causeway.viewer.wicket.ui.components.attributes;

import org.apache.causeway.commons.collections.Can;
import org.apache.causeway.viewer.commons.model.attrib.UiAttribute;
import org.apache.causeway.viewer.wicket.model.models.UiAttributeWkt;

import lombok.Getter;
import lombok.experimental.Accessors;

public abstract class AttributeComponentFactoryWithTypeConstraint
extends AttributeComponentFactory {

    /**
     * Provides discrimination based on {@link UiAttribute#isElementTypeAnyOf(Can)}.
     * <p>
     * If empty, no type constraints are applied.
     */
    @Getter @Accessors(fluent=true)
    private final Can<Class<?>> elementTypes;

    protected AttributeComponentFactoryWithTypeConstraint(
            final Class<?> componentClass,
            final Class<?> elementType) {
        this(componentClass, Can.ofSingleton(elementType));
    }

    protected AttributeComponentFactoryWithTypeConstraint(
            final Class<?> componentClass,
            final Can<Class<?>> elementTypes) {
        super(componentClass);
        this.elementTypes = elementTypes;
    }

    @Override
    protected final ApplicationAdvice appliesTo(final UiAttributeWkt attributeModel) {

        // discriminates based on given scalarTypes, if any
        if(elementTypes.isNotEmpty()
                && !attributeModel.isElementTypeAnyOf(elementTypes)) {
            return ApplicationAdvice.DOES_NOT_APPLY;
        }

        // if has any choices, use select-2 component instead
        return appliesIf( !attributeModel.hasChoices()
                && !attributeModel.hasAutoComplete() );
    }

}
