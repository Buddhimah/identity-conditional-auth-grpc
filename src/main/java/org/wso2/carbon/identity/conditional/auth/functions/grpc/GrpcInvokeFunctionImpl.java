/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.conditional.auth.functions.grpc;

import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.wso2.carbon.identity.application.authentication.framework.AsyncProcess;
import org.wso2.carbon.identity.application.authentication.framework.config.model.graph.JsGraphBuilder;
import org.wso2.carbon.identity.conditional.auth.functions.grpc.service.GrpcServiceGrpc;
import org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.wso2.carbon.identity.conditional.auth.functions.common.utils.Constants.OUTCOME_FAIL;
import static org.wso2.carbon.identity.conditional.auth.functions.common.utils.Constants.OUTCOME_SUCCESS;

/**
 * Implementation of the {@link org.wso2.carbon.identity.conditional.auth.functions.grpc.GrpcInvokeFunction}.
 */
public class GrpcInvokeFunctionImpl implements GrpcInvokeFunction {

    private static final Log log = LogFactory.getLog(org.wso2.carbon.identity.conditional.auth.functions.grpc
            .GrpcInvokeFunctionImpl.class);

    @Override
    public void grpcInvoke(String host, String port, Object params, Map<String, Object> eventHandlers) {

        AsyncProcess asyncProcess = new AsyncProcess((context, asyncReturn) -> {

            JSONObject jsonObjectRequest = new JSONObject();
            Map<String, Object> properties;
            JSONObject jsonObjectResponse = null;
            String outcome;

            if (params instanceof Map) {

                properties = (Map<String, Object>) params;
                for (Map.Entry<String, Object> entry : properties.entrySet()) {

                    jsonObjectRequest.put(entry.getKey(), entry.getValue());
                }
                // Converts the Json object into a Json string.
                String jsonString = jsonObjectRequest.toJSONString();

                // Create the channel for gRPC server.
                ManagedChannel channel = NettyChannelBuilder.forAddress(host, Integer.parseInt(port)).usePlaintext()
                        .build();

                // Create the gRPC client stub.
                GrpcServiceGrpc.GrpcServiceBlockingStub clientStub = GrpcServiceGrpc.newBlockingStub(channel);

                // Define the request message.
                Service.JsonRequest jsonRequest = Service.JsonRequest.newBuilder().setJsonString(jsonString).build();

                // Obtain response message from gRPC server and sets a deadline.
                try {
                    String jsonStringResponse = clientStub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS)
                            .grpcInvoke(jsonRequest).getJsonString();
                    if (log.isDebugEnabled()) {
                        log.debug(jsonStringResponse);
                    }

                    //  Validate the gRPC server response object type.
                    try {
                        JSONParser jsonParser = new JSONParser();
                        jsonObjectResponse = (JSONObject) jsonParser.parse(jsonStringResponse);
                        outcome = OUTCOME_SUCCESS;
                    } catch (ParseException e) {
                        log.error("gRPC server returns non Json string.", e);
                        outcome = OUTCOME_FAIL;
                    }
                    // Handle the exceptions.
                } catch (StatusRuntimeException e) {
                    if (e.getStatus().getCode() == Status.Code.DEADLINE_EXCEEDED) {
                        log.error("gRPC connection deadline exceeded.", e);
                        outcome = OUTCOME_FAIL;
                    } else if (e.getStatus().getCode() == Status.Code.UNAVAILABLE) {
                        log.error("gRPC service is unavailable at " + host + ":" + port, e);
                        outcome = OUTCOME_FAIL;
                    } else if (e.getStatus().getCode() == Status.Code.UNIMPLEMENTED) {
                        log.error("Operation not implemented in the gRPC service at " + host + ":" + port, e);
                        outcome = OUTCOME_FAIL;
                    } else if (e.getStatus().getCode() == Status.Code.UNKNOWN) {
                        log.error("gRPC server threw unknown exception at " + host + ":" + port, e);
                        outcome = OUTCOME_FAIL;
                    } else {
                        log.error("gRPC service failure. " + e.getStatus().toString());
                        outcome = OUTCOME_FAIL;
                    }
                }
                channel.shutdown();

            } else {
                log.error("Incorrect definition of method parameters. Cannot find a Json Object.");
                outcome = OUTCOME_FAIL;
            }

            asyncReturn.accept(context, jsonObjectResponse != null ? jsonObjectResponse : Collections.emptyMap()
                    , outcome);
        });

        JsGraphBuilder.addLongWaitProcess(asyncProcess, eventHandlers);
    }

}

