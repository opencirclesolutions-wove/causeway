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
package org.apache.causeway.core.metamodel.facets.properties.property.entitychangepublishing;

import java.util.Objects;
import java.util.function.BiConsumer;

import org.apache.causeway.applib.annotation.Publishing;
import org.apache.causeway.core.metamodel.facetapi.Facet;
import org.apache.causeway.core.metamodel.facetapi.FacetAbstract;
import org.apache.causeway.core.metamodel.facetapi.FacetHolder;

import lombok.Getter;
import org.jspecify.annotations.NonNull;

public abstract class EntityPropertyChangePublishingPolicyFacetAbstract
extends FacetAbstract
implements EntityPropertyChangePublishingPolicyFacet {

    private static final Class<? extends Facet> type() {
        return EntityPropertyChangePublishingPolicyFacet.class;
    }

    @Getter(onMethod_ = {@Override})
    private final @NonNull Publishing entityChangePublishing;

    protected EntityPropertyChangePublishingPolicyFacetAbstract(
            final Publishing entityChangePublishing,
            final FacetHolder holder) {
        super(type(), holder);
        this.entityChangePublishing = entityChangePublishing;
    }

    @Override
    public void visitAttributes(final BiConsumer<String, Object> visitor) {
        super.visitAttributes(visitor);
        visitor.accept("entityChangePublishing", getEntityChangePublishing());
    }

    @Override
    public boolean semanticEquals(final @NonNull Facet other) {
        return other instanceof EntityPropertyChangePublishingPolicyFacet
                ? Objects.equals(
                        this.getEntityChangePublishing(),
                        ((EntityPropertyChangePublishingPolicyFacet)other).getEntityChangePublishing())
                : false;
    }

}
