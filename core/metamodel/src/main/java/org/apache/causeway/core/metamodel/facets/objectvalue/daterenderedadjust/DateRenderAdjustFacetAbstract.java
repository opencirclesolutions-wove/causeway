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
package org.apache.causeway.core.metamodel.facets.objectvalue.daterenderedadjust;

import java.util.function.BiConsumer;

import org.apache.causeway.core.metamodel.facetapi.Facet;
import org.apache.causeway.core.metamodel.facetapi.FacetAbstract;
import org.apache.causeway.core.metamodel.facetapi.FacetHolder;

import org.jspecify.annotations.NonNull;

public abstract class DateRenderAdjustFacetAbstract
extends FacetAbstract
implements DateRenderAdjustFacet {

    public static final Class<DateRenderAdjustFacet> type() {
        return DateRenderAdjustFacet.class;
    }

    private final int dateRenderAdjustDays;

    protected DateRenderAdjustFacetAbstract(final int adjustByDays, final FacetHolder holder, final Precedence precedence) {
        super(type(), holder, precedence);
        this.dateRenderAdjustDays = adjustByDays;
    }

    protected DateRenderAdjustFacetAbstract(final int adjustByDays, final FacetHolder holder) {
        super(type(), holder);
        this.dateRenderAdjustDays = adjustByDays;
    }

    @Override
    public int getDateRenderAdjustDays() {
        return dateRenderAdjustDays;
    }

    @Override
    public void visitAttributes(final BiConsumer<String, Object> visitor) {
        super.visitAttributes(visitor);
        visitor.accept("dateRenderAdjustDays", String.valueOf(dateRenderAdjustDays));
    }

    @Override
    public boolean semanticEquals(final @NonNull Facet other) {
        return other instanceof DateRenderAdjustFacet
                ? this.getDateRenderAdjustDays() == ((DateRenderAdjustFacet)other).getDateRenderAdjustDays()
                : false;
    }

}
