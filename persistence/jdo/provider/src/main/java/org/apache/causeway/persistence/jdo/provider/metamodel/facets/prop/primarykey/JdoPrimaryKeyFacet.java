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
package org.apache.causeway.persistence.jdo.provider.metamodel.facets.prop.primarykey;

import javax.jdo.annotations.PrimaryKey;

import org.apache.causeway.applib.services.bookmark.Bookmark;
import org.apache.causeway.core.metamodel.facetapi.Facet;

/**
 * Corresponds to the property with the {@link PrimaryKey} annotation.
 * <p>
 * The JDO {@link PrimaryKey} annotation is used internally the JDO object store to
 * create the {@link Bookmark}.
 */
public interface JdoPrimaryKeyFacet extends Facet {

}
