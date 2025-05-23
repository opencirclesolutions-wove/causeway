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
package org.apache.causeway.viewer.wicket.viewer.integration;

import java.util.Locale;

import jakarta.inject.Inject;

import org.apache.wicket.util.convert.IConverter;

import org.apache.causeway.applib.services.bookmark.Bookmark;
import org.apache.causeway.core.metamodel.object.ManagedObject;
import org.apache.causeway.core.metamodel.object.ManagedObjects;
import org.apache.causeway.core.metamodel.objectmanager.ObjectManager;

/**
 * Implementation of a <i>Wicket</i> {@link IConverter} for {@link ManagedObject}s,
 * converting to-and-from their {@link Bookmark}'s string representation.
 */
public class ConverterForObjectAdapter implements IConverter<ManagedObject> {

    private static final long serialVersionUID = 1L;

    @Inject private transient ObjectManager objectManager;

    /**
     * Converts string representation of {@link Bookmark} to
     * {@link ManagedObject}.
     */
    @Override
    public ManagedObject convertToObject(final String value, final Locale locale) {

        var obj = Bookmark.parse(value)
            .flatMap(objectManager.getMetaModelContext().getObjectManager()::loadObject)
            .orElse(null);

        //XXX ever used ?
        System.err.printf("ConverterForObjectAdapter: convertTo ManagedObject %s->%s%n", value, obj);

        return obj;
    }

    /**
     * Converts {@link ManagedObject} to string representation of {@link Bookmark}.
     */
    @Override
    public String convertToString(final ManagedObject adapter, final Locale locale) {
        var string =  ManagedObjects.stringify(adapter)
                .orElse(null);

        //XXX ever used ?
        System.err.printf("ConverterForObjectAdapter: convertFrom ManagedObject %s->%s%n", adapter, string);

        return string;
    }

}
