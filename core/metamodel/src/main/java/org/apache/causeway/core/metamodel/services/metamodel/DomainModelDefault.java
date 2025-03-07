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
package org.apache.causeway.core.metamodel.services.metamodel;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.apache.causeway.applib.services.metamodel.DomainMember;
import org.apache.causeway.applib.services.metamodel.DomainModel;
import org.apache.causeway.commons.internal.exceptions._Exceptions;

@XmlRootElement(name="domain")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class DomainModelDefault implements DomainModel {

    // to support JAX-B marshaling
    DomainModelDefault(){
        throw _Exceptions.unexpectedCodeReach();
    }

    public DomainModelDefault(List<DomainMember> memberList) {
        this.memberList = memberList;
    }

    // --

    private List<DomainMember> memberList;

    @XmlElement(name="domain-member", type=DomainMemberDefault.class)
    @Override
    public List<DomainMember> getDomainMembers() {
        return memberList;
    }

    // --

}
