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
package org.apache.causeway.viewer.graphql.model.marshallers;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;

import org.springframework.stereotype.Component;

import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.core.config.CausewayConfiguration;
import org.apache.causeway.viewer.graphql.applib.marshallers.ScalarMarshallerAbstract;

import graphql.scalars.ExtendedScalars;

/**
 * Maps to a string to avoid loss of precision.
 */
@Component
@Priority(PriorityPrecedence.LATE)
public class ScalarMarshallerLongWrapper extends ScalarMarshallerAbstract<Long> {

    @Inject
    public ScalarMarshallerLongWrapper(final CausewayConfiguration causewayConfiguration) {
        super(Long.class, ExtendedScalars.GraphQLLong, causewayConfiguration);
    }

    @Override
    public Long unmarshal(final Object graphValue, final Class<?> targetType) {
        return Long.valueOf((String)graphValue);
    }

}
