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
package org.apache.causeway.viewer.graphql.model.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.FieldCoordinates;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import org.apache.causeway.applib.services.bookmark.BookmarkService;
import org.apache.causeway.commons.collections.Can;
import org.apache.causeway.core.metamodel.object.ManagedObject;
import org.apache.causeway.core.metamodel.spec.ObjectSpecification;
import org.apache.causeway.core.metamodel.spec.feature.ObjectAction;
import org.apache.causeway.core.metamodel.spec.feature.ObjectActionParameter;
import org.apache.causeway.viewer.graphql.applib.types.TypeMapper;
import org.apache.causeway.viewer.graphql.model.context.Context;
import org.apache.causeway.viewer.graphql.model.fetcher.BookmarkedPojoFetcher;
import org.apache.causeway.viewer.graphql.model.mmproviders.ObjectActionProvider;
import org.apache.causeway.viewer.graphql.model.mmproviders.ObjectSpecificationProvider;

import lombok.Getter;
import lombok.val;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GqlvActionParams implements GqlvActionParam.Holder {

    @Getter private final Holder holder;
    private final Context context;

    private final GraphQLObjectType.Builder gqlObjectTypeBuilder;
    private final GraphQLObjectType gqlObjectType;

    /**
     * Populated iff {@link #hasParams()}
     */
    private final GraphQLFieldDefinition field;

    private final Map<String, GqlvActionParam> params = new LinkedHashMap<>();

    public GqlvActionParams(
            final Holder holder,
            final Context context) {
        this.holder = holder;
        this.context = context;
        this.gqlObjectTypeBuilder = newObject().name(TypeNames.actionParamsTypeNameFor(holder.getObjectSpecification(), holder.getObjectAction()));

        val idx = new AtomicInteger(0);
        holder.getObjectAction().getParameters().forEach(objectActionParameter -> {
            addParam(objectActionParameter, idx.getAndIncrement());
        });

        this.gqlObjectType = gqlObjectTypeBuilder.build();

        this.field = hasParams() ?
                holder.addField(newFieldDefinition()
                    .name("params")
                    .type(gqlObjectTypeBuilder)
                    .build())
                : null;
    }

    @Override
    public ObjectSpecification getObjectSpecification() {
        return holder.getObjectSpecification();
    }

    @Override
    public ObjectAction getObjectMember() {
        return getObjectAction();
    }

    @Override
    public ObjectAction getObjectAction() {
        return holder.getObjectAction();
    }

    public boolean hasParams() {
        return !params.isEmpty();
    }

    void addParam(ObjectActionParameter objectActionParameter, int paramNum) {
        params.put(objectActionParameter.getId(), new GqlvActionParam(this, objectActionParameter, context, paramNum));
    }


     @Override
    public GraphQLFieldDefinition addField(GraphQLFieldDefinition field) {
        gqlObjectTypeBuilder.field(field);
        return field;
    }

    void addDataFetcher() {
        context.codeRegistryBuilder.dataFetcher(
                holder.coordinatesFor(field),
                new BookmarkedPojoFetcher(context.bookmarkService));

        params.forEach((id, param) -> param.addDataFetcher());
    }


    @Override
    public FieldCoordinates coordinatesFor(GraphQLFieldDefinition fieldDefinition) {
        return FieldCoordinates.coordinates(gqlObjectType, fieldDefinition);
    }

    @Override
    public void addGqlArguments(
            ObjectAction objectAction, GraphQLFieldDefinition.Builder fieldBuilder, TypeMapper.InputContext inputContext, int paramNum) {
        holder.addGqlArguments(objectAction, fieldBuilder, inputContext, paramNum);
    }

    @Override
    public Can<ManagedObject> argumentManagedObjectsFor(
            DataFetchingEnvironment dataFetchingEnvironment,
            ObjectAction objectAction,
            BookmarkService bookmarkService) {
        return holder.argumentManagedObjectsFor(dataFetchingEnvironment, objectAction, bookmarkService);
    }

    public interface Holder
            extends GqlvHolder,
                    ObjectSpecificationProvider,
                    ObjectActionProvider {

        void addGqlArguments(
                ObjectAction objectAction,
                GraphQLFieldDefinition.Builder fieldBuilder,
                TypeMapper.InputContext inputContext,
                int paramNum);

        Can<ManagedObject> argumentManagedObjectsFor(
                DataFetchingEnvironment dataFetchingEnvironment,
                ObjectAction objectAction,
                BookmarkService bookmarkService);
    }
}