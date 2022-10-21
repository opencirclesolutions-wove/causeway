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
package org.apache.causeway.viewer.wicket.viewer.services;

import java.io.File;

import org.apache.causeway.viewer.wicket.viewer.services.TranslationsResolverWicket;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeThat;

public class TranslationsResolverWicketTest {

    public static class NewFile extends TranslationsResolverWicketTest {

        @Before
        public void setUp() throws Exception {
            assumeThat(System.getProperty("os.name").startsWith("Windows"), is(true));
        }

        @Test
        public void simple() throws Exception {
            final File file = TranslationsResolverWicket.newFile("c:/foo", "bar").toFile();
            final String absolutePath = file.getAbsolutePath();
            assertThat(absolutePath, is("c:\\foo\\bar"));
        }

        @Test
        public void nestedChild() throws Exception {
            final File file = TranslationsResolverWicket.newFile("c:/foo", "bar/baz").toFile();
            final String absolutePath = file.getAbsolutePath();
            assertThat(absolutePath, is("c:\\foo\\bar\\baz"));
        }

    }


}