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
package org.apache.causeway.core.metamodel.facets.members.iconfa;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FaFacetAbstractTest {

    public static class Sanitize extends FaFacetAbstractTest {

        @Test
        public void present() throws Exception {
            assertThat(FaStaticFacetAbstract.parse("fab foo").toString(), is("[fab, fa-fw, fa-foo]"));
        }

        @Test
        public void presentAtEnd() throws Exception {
            assertThat(FaStaticFacetAbstract.parse("foo far ").toString(), is("[far, fa-fw, fa-foo]"));
        }

        @Test
        public void missing() throws Exception {
            assertThat(FaStaticFacetAbstract.parse("foo").toString(), is("[fa, fa-fw, fa-foo]"));
        }
    }
}