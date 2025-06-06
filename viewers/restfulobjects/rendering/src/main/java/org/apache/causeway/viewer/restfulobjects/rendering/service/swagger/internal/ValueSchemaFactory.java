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
package org.apache.causeway.viewer.restfulobjects.rendering.service.swagger.internal;

import java.util.Optional;

import org.jspecify.annotations.Nullable;

import org.apache.causeway.core.metamodel.spec.ObjectSpecification;

import io.swagger.v3.oas.models.media.Schema;

public interface ValueSchemaFactory {

    Optional<Schema<?>> schemaForValue(@Nullable Class<?> cls);

    default Optional<Schema<?>> schemaForValue(
            final @Nullable ObjectSpecification objectSpecification) {
        return Optional.ofNullable(objectSpecification)
                .map(ObjectSpecification::getCorrespondingClass)
                .flatMap(this::schemaForValue);
    }

}
