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
package org.apache.causeway.core.metamodel.services.exceprecog;

import jakarta.annotation.Priority;
import jakarta.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.exceptions.RecoverableException;
import org.apache.causeway.applib.services.exceprecog.Category;
import org.apache.causeway.applib.services.exceprecog.ExceptionRecognizer;
import org.apache.causeway.applib.services.exceprecog.ExceptionRecognizerForType;
import org.apache.causeway.core.metamodel.CausewayModuleCoreMetamodel;

/**
 * Default implementation of {@link ExceptionRecognizer},
 * which will automatically recognize any
 * {@link org.apache.causeway.applib.exceptions.RecoverableException}s.
 *
 * @since 1.x revised for 2.0 {@index}
 */
@Service
@Named(CausewayModuleCoreMetamodel.NAMESPACE + ".ExceptionRecognizerForRecoverableException")
@Priority(PriorityPrecedence.MIDPOINT)
@Qualifier("Default")
public class ExceptionRecognizerForRecoverableException extends ExceptionRecognizerForType {

    public ExceptionRecognizerForRecoverableException() {
        super(Category.RECOVERABLE, RecoverableException.class);
    }

}
