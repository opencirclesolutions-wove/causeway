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
package org.apache.causeway.viewer.wicket.ui.components.layout.bs.clearfix;

import org.apache.wicket.markup.html.WebMarkupContainer;

import org.apache.causeway.applib.layout.grid.bootstrap.BSClearFix;
import org.apache.causeway.core.metamodel.object.ManagedObject;
import org.apache.causeway.viewer.wicket.model.models.UiObjectWkt;
import org.apache.causeway.viewer.wicket.ui.panels.PanelAbstract;
import org.apache.causeway.viewer.wicket.ui.util.Wkt;

public class ClearFix
extends PanelAbstract<ManagedObject, UiObjectWkt> {

    private static final long serialVersionUID = 1L;

    private static final String ID_COL = "clearfix";

    private final BSClearFix bsClearFix;

    public ClearFix(
            final String id,
            final UiObjectWkt objectModel,
            final BSClearFix bsClearFix) {

        super(id, objectModel);
        this.bsClearFix = bsClearFix;

        buildGui();
    }

    private void buildGui() {

        setRenderBodyOnly(true);
        Wkt.cssAppend(this, bsClearFix.getCssClass());

        final WebMarkupContainer div = new WebMarkupContainer(ID_COL);
        Wkt.cssAppend(div, bsClearFix.toCssClass());

        this.addOrReplace(div);
    }

}
