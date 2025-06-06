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
package org.apache.causeway.core.metamodel.facets;

import org.apache.causeway.commons.collections.Can;
import org.apache.causeway.commons.collections.ImmutableEnumSet;
import org.apache.causeway.commons.internal.reflection._GenericResolver.ResolvedMethod;
import org.apache.causeway.core.metamodel.context.MetaModelContext;
import org.apache.causeway.core.metamodel.facetapi.FeatureType;
import org.apache.causeway.core.metamodel.facets.propcoll.accessor.PropertyOrCollectionAccessorFacet;
import org.apache.causeway.core.metamodel.methods.MethodPrefixBasedFacetFactoryAbstract;
import org.apache.causeway.core.metamodel.spec.ObjectSpecification;

public abstract class AccessorFacetFactoryAbstract
extends MethodPrefixBasedFacetFactoryAbstract
implements AccessorFacetFactory {

    protected AccessorFacetFactoryAbstract(
            final MetaModelContext mmc,
            final ImmutableEnumSet<FeatureType> featureTypes,
            final Can<String> prefixes) {
        super(mmc, featureTypes, OrphanValidation.DONT_VALIDATE, prefixes);
    }

    @Override
    public final boolean supportsProperties() {
        return super.getFeatureTypes().contains(FeatureType.PROPERTY);
    }

    @Override
    public final boolean supportsCollections() {
        return super.getFeatureTypes().contains(FeatureType.COLLECTION);
    }

    @Override
    public final void process(final ProcessMethodContext processMethodContext) {
        var accessorMethod = processMethodContext.getMethod().asMethodElseFail();
        processMethodContext.removeMethod(accessorMethod);

        var cls = processMethodContext.getCls();
        var typeSpec = getSpecificationLoader().loadSpecification(cls);
        var facetHolder = processMethodContext.getFacetHolder();

        addFacet(createFacet(typeSpec, accessorMethod, facetHolder));
    }

    protected abstract PropertyOrCollectionAccessorFacet createFacet(
        ObjectSpecification typeSpec, ResolvedMethod accessorMethod, FacetedMethod facetHolder);

}
