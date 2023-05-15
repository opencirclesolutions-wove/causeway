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
package demoapp.dom.domain.actions.progmodel.depargs;

import javax.inject.Inject;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.MemberSupport;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.ParameterLayout;
import org.apache.causeway.applib.annotation.PromptStyle;
import org.apache.causeway.applib.services.message.MessageService;

import demoapp.dom.domain.actions.progmodel.TvShow;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.val;
import lombok.experimental.Accessors;

@ActionLayout(named="Default", promptStyle = PromptStyle.DIALOG_MODAL)
@Action
@RequiredArgsConstructor
public class ActionDependentArgsPage_useDefault {

    @Inject MessageService messageService;

    private final ActionDependentArgsPage mixee;

    @Value @Accessors(fluent = true) // fluent so we can replace this with Java(14+) records later
    static class Parameters {
        TvShow tvShow;
        String message;
    }

    public ActionDependentArgsPage act(

            // PARAM 0
            @Parameter(optionality = Optionality.MANDATORY) final
            TvShow tvShow,

            // PARAM 1
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Message") final
            String message

            ) {

        messageService.informUser(message);
        return mixee;
    }

    // -- PARAM 0 (Parity)

    @MemberSupport public TvShow defaultTvShow(final Parameters params) {

        return mixee.getFirstParamDefault();
    }

    // -- PARAM 1 (String message)

    @MemberSupport public String defaultMessage(final Parameters params) {

        val parityFromDialog = params.tvShow(); // <-- the refining parameter from the dialog above

        if(parityFromDialog == null) {
            return "no parity selected";
        }
        return parityFromDialog.name();
    }

}
