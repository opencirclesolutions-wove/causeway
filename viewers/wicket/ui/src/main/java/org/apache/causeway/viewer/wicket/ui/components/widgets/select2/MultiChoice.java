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
package org.apache.causeway.viewer.wicket.ui.components.widgets.select2;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.wicket.model.IModel;
import org.wicketstuff.select2.Select2MultiChoice;
import org.apache.causeway.applib.id.LogicalType;
import org.apache.causeway.core.metamodel.context.HasMetaModelContext;
import org.apache.causeway.core.metamodel.object.ManagedObject;
import org.apache.causeway.core.metamodel.objectmanager.memento.ObjectMemento;
import org.apache.causeway.viewer.wicket.model.models.UiAttributeWkt;

record MultiChoice(
    LogicalType logicalType,
    Select2MultiChoice<ObjectMemento> component)
implements Select2, HasMetaModelContext {

    MultiChoice(
            final String id,
            final IModel<Collection<ObjectMemento>> model,
            final UiAttributeWkt attributeModel,
            final ChoiceProvider choiceProvider) {
        this(
            attributeModel.getElementType().logicalType(),
            new Select2MultiChoice<>(id, model, choiceProvider.toSelect2ChoiceProvider()) {
                private static final long serialVersionUID = 1L;
                private boolean workaround;

                // -- bug in wicket-stuff
                @Override public void updateModel() {
                    workaround = true;
                    super.updateModel();
                    workaround = false;
                }
                @Override public Collection<ObjectMemento> getModelObject() {
                    var modelObj = super.getModelObject();
                    if(workaround) {
                        return modelObj==null
                                ? null
                                : new ArrayList<>(modelObj);
                    }
                    return modelObj;
                }
                // --

            });

        component().setRequired(attributeModel.isRequired());
    }

    @Override
    public ManagedObject convertedInputValue() {
        return getObjectManager().demementify(packedConvertedInput());
    }

    @Override
    public ObjectMemento objectMemento() {
        return packedModelObject();
    }

    // -- HELPER

    private ObjectMemento packedModelObject() {
        return ObjectMemento.packed(logicalType, component.getModelObject());
    }

    private ObjectMemento packedConvertedInput() {
        return ObjectMemento.packed(logicalType, component.getConvertedInput());
    }

}
