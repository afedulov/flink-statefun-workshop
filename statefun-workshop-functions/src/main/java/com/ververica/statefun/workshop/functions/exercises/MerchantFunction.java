/*
 * Copyright 2020 Ververica GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ververica.statefun.workshop.functions.exercises;

import com.ververica.statefun.workshop.messages.MerchantScore;
import com.ververica.statefun.workshop.messages.QueryMerchantScore;
import com.ververica.statefun.workshop.utils.MerchantScoreService;
import org.apache.flink.statefun.sdk.Context;
import org.apache.flink.statefun.sdk.StatefulFunction;

/**
 * Our application relies on a 3rd party service that returns a trustworthiness score for each
 * merchant.
 *
 * <p>This function, when it receives a {@link QueryMerchantScore} message, will make up to <b>3
 * attempts</b> to query the service and return a score. If the service does not successfully return
 * a result within 3 tries it will return back an error.
 *
 * <p>All cases will result in a {@link MerchantScore} message be sent back to the caller function.
 */
public class MerchantFunction implements StatefulFunction {

    private final MerchantScoreService client;

    public MerchantFunction(MerchantScoreService client) {
        this.client = client;
    }

    @Override
    public void invoke(Context context, Object input) {}
}
