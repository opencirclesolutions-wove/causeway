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
package org.apache.causeway.testdomain.jpa.conf;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.causeway.core.config.presets.CausewayPresets;
import org.apache.causeway.core.runtimeservices.CausewayModuleCoreRuntimeServices;
import org.apache.causeway.persistence.jpa.eclipselink.CausewayModulePersistenceJpaEclipselink;
import org.apache.causeway.security.bypass.CausewayModuleSecurityBypass;
import org.apache.causeway.testdomain.jpa.springdata.SpringDataJpaTestModule;
import org.apache.causeway.testdomain.model.stereotypes.MyService;
import org.apache.causeway.testdomain.util.kv.KVStoreForTesting;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import({

    SpringDataJpaTestModule.class,

    MyService.class, // testing injection into entities

    CausewayModuleCoreRuntimeServices.class
    ,CausewayModuleSecurityBypass.class
    ,CausewayModulePersistenceJpaEclipselink.class
    //,CausewayModuleTestingFixturesApplib.class
    ,KVStoreForTesting.class, // Helper for JUnit Tests
})
@PropertySources({
    @PropertySource(CausewayPresets.NoTranslations),
    @PropertySource(CausewayPresets.H2InMemory_withUniqueSchema),
})
public class Configuration_usingSpringDataJpa {

}
