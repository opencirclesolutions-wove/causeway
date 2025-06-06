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
package org.apache.causeway.valuetypes.markdown.ui.wkt.components;

import org.springframework.stereotype.Component;

import org.apache.causeway.valuetypes.markdown.applib.value.Markdown;
import org.apache.causeway.viewer.wicket.model.models.UiAttributeWkt;
import org.apache.causeway.viewer.wicket.model.models.ValueModel;
import org.apache.causeway.viewer.wicket.ui.components.attributes.markup.MarkupAttributePanelFactories;
import org.apache.causeway.viewer.wicket.ui.components.attributes.markup.MarkupComponent;

/**
 * @implNote Almost a copy of {@code Parented} and {@code Standalone} in
 * {@link MarkupAttributePanelFactories}, but specific to
 * the {@link Markdown} value-type which requires client-side java-script to be
 * executed to enable syntax highlighting
 */
public class MarkdownPanelFactoriesWkt {

    // -- PARENTED

    @Component
    public static class Parented extends MarkupAttributePanelFactories.ParentedAbstract<Markdown> {

        public Parented() {
            super(Markdown.class);
        }

        @Override
        protected MarkupComponent newMarkupComponent(final String id, final UiAttributeWkt model) {
            var markupComponent = new MarkdownComponentWkt(id, model);
            markupComponent.setEnabled(false);
            return markupComponent;
        }

    }

    // -- STANDALONE

    @Component
    public static class Standalone extends MarkupAttributePanelFactories.StandaloneAbstract<Markdown> {

        public Standalone() {
            super(Markdown.class);
        }

        @Override
        protected MarkupComponent newMarkupComponent(final String id, final ValueModel model) {
            return new MarkdownComponentWkt(id, model);
        }

    }

}
