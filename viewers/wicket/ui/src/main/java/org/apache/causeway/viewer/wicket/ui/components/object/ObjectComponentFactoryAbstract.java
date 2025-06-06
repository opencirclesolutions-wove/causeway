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
package org.apache.causeway.viewer.wicket.ui.components.object;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

import org.apache.causeway.viewer.commons.model.components.UiComponentType;
import org.apache.causeway.viewer.wicket.model.models.UiObjectWkt;
import org.apache.causeway.viewer.wicket.ui.ComponentFactory;
import org.apache.causeway.viewer.wicket.ui.ComponentFactoryAbstract;

/**
 * Base {@link ComponentFactory} for factories creating {@link Component}(s)
 * that are backed by an {@link UiObjectWkt}.
 */
public abstract class ObjectComponentFactoryAbstract extends ComponentFactoryAbstract {

    public ObjectComponentFactoryAbstract(
            final UiComponentType uiComponentType,
            final Class<?> componentClass) {
        super(uiComponentType, componentClass);
    }

    public ObjectComponentFactoryAbstract(
            final UiComponentType uiComponentType,
            final String name,
            final Class<?> componentClass) {
        super(uiComponentType, name, componentClass);
    }

    @Override
    protected ApplicationAdvice appliesTo(final IModel<?> model) {
        if (!(model instanceof UiObjectWkt)) {
            return ApplicationAdvice.DOES_NOT_APPLY;
        }
        final UiObjectWkt objectModel = (UiObjectWkt) model;
        // hit a scenario on a redirect-and-post strategy where the component is rendered not on an
        // DomainObjectPage but instead using a custom home page.
        // The hacky override in entity page (in DomainObjectPage#onBeforeRender)
        // is therefore not called. resulting in a concurrency exception.
        //
        // Therefore, we do the same processing here instead.
        var adapter = objectModel.getManagedObject();
        if (adapter == null) {
            // is ok;
        }
        var spec = objectModel.getTypeOfSpecification();
        if (spec.isSingular()
                && !spec.isValue()) {
            return doAppliesTo(objectModel);
        }
        return ApplicationAdvice.DOES_NOT_APPLY;
    }

    /**
     * optional hook.
     */
    protected ApplicationAdvice doAppliesTo(final UiObjectWkt objectModel) {
        return ApplicationAdvice.APPLIES;
    }

}
