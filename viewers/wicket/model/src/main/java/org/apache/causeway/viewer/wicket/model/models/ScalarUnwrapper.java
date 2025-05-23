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
package org.apache.causeway.viewer.wicket.model.models;

import org.jspecify.annotations.NonNull;

import org.springframework.util.ClassUtils;

import org.apache.causeway.commons.internal.assertions._Assert;
import org.apache.causeway.commons.internal.base._Casts;
import org.apache.causeway.core.metamodel.object.ManagedObject;
import org.apache.causeway.core.metamodel.object.MmUnwrapUtils;

/**
 * Wraps and unwraps the contained value within {@link ManagedObject},
 * as provided by a {@link UiAttributeWkt}.
 */
record ScalarUnwrapper<T>(
    Class<T> type,
    UiAttributeWkt attributeModel) {

    // canonical constructor
    public ScalarUnwrapper(
            final @NonNull Class<T> type,
            final @NonNull UiAttributeWkt attributeModel) {
        this.type = type;
        this.attributeModel = attributeModel;
        _Assert.assertTrue(attributeModel.getElementType().isAssignableFrom(type), ()->
                String.format("cannot possibly unwrap model of type %s into target type %s",
                        attributeModel.getElementType().getCorrespondingClass(),
                        type));
    }

    public T getObject() {
        var objectAdapter = attributeModel().getObject();
        var pojo = unwrap(objectAdapter);
        return pojo;
    }

    public void setObject(final T object) {
        var attributeModel = attributeModel();
        if (object == null) {
            attributeModel.setObject(null);
        } else {
            var objectAdapter = attributeModel.getMetaModelContext().getObjectManager().adapt(object);
            attributeModel.setObject(objectAdapter);
        }
    }

    // -- HELPER

    private T unwrap(final ManagedObject objectAdapter) {
        var pojo = MmUnwrapUtils.single(objectAdapter);
        if(pojo==null
                || !ClassUtils.resolvePrimitiveIfNecessary(type)
                        .isAssignableFrom(ClassUtils.resolvePrimitiveIfNecessary(pojo.getClass()))) {
            return null;
        }
        return _Casts.uncheckedCast(pojo);
    }

}