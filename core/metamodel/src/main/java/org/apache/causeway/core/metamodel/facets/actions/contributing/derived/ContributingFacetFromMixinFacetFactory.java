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
package org.apache.causeway.core.metamodel.facets.actions.contributing.derived;

import jakarta.inject.Inject;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.core.metamodel.context.MetaModelContext;
import org.apache.causeway.core.metamodel.facetapi.FacetUtil;
import org.apache.causeway.core.metamodel.facetapi.FeatureType;
import org.apache.causeway.core.metamodel.facets.FacetFactoryAbstract;
import org.apache.causeway.core.metamodel.facets.actions.contributing.ContributingFacetAbstract;
import org.apache.causeway.core.metamodel.facets.object.mixin.MixinFacet;

public class ContributingFacetFromMixinFacetFactory
extends FacetFactoryAbstract {

    @Inject
    public ContributingFacetFromMixinFacetFactory(final MetaModelContext mmc) {
        super(mmc, FeatureType.ACTIONS_ONLY);
    }

    @Override
    public void process(final ProcessMethodContext processMethodContext) {

        if(!processMethodContext.isMixinMain()) {
            // skip processing if not mixin main
            return;
        }

        var method = processMethodContext.getMethod();
        var declaringClass = method.getDeclaringClass();
        var spec = getSpecificationLoader().loadSpecification(declaringClass);

        if(!spec.lookupNonFallbackFacet(MixinFacet.class).isPresent()) {
            return;
        }

        var facetedMethod = processMethodContext.getFacetHolder();

        //[1998] if @Action or @ActionLayout detected on type level infer:
        //@ActionLayout(contributed=ACTION)
        var isForceContributedAsAction =
                // not reporting ambiguity here, this is done else where
                processMethodContext.synthesizeOnMethodOrMixinType(Action.class, ()->{}).isPresent()
                || processMethodContext.synthesizeOnMethodOrMixinType(ActionLayout.class, ()->{}).isPresent();

        if(isForceContributedAsAction) {
            FacetUtil.addFacet(ContributingFacetAbstract.createAsAction(facetedMethod));
        }

    }

}
