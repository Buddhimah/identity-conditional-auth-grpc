# identity-conditional-auth-grpc
This implementation of gRPC Based Function for WSO2 Identity Server Adaptive Authentication enables users to implement platform independent custom function for adaptive authentication.

- [Getting Started](#getting-started)
- [Build from the source](#build-from-the-source)


## Getting Started
You can get a clear knowledge on configuring of the gRPC Based function for adaptive authentication by following this small guide which contains main sections listed below.

- [Implementing gRPC Server](#implementing-grpc-server)
- [Configuring Identity Server](#configuring-identity-server)
- [Running the sample](#running-the-sample)

Throughout the instructions `{wso2is-home}` is referred as the root directory of the WSO2 Identity Server.

### Implementing gRPC Server
Use [service.proto](https://github.com/NuwangaHerath/identity-conditional-auth-grpc/blob/main/src/main/resources/Service.proto) to implement the gRPC server.

Follow [this](https://grpc.io/docs/) documentation to implement a gRPC server in any preferred gRPC supported language.

You can find sample gRPC servers from the below table.

| Language | Link |
| ------ | ------ |
| Java | [gRPC Adaptive Auth Server](https://github.com/NuwangaHerath/grpc-adaptive-auth-server) |

- For this guide, we used Java gRPC server.
- Note down the `host` and `port` of the server for [Identity Server Configurations](#configuring-identity-server).

### Configuring Identity Server
1. Build the `org.wso2.carbon.identity.conditional.auth.functions.grpc-1.1.7-SNAPSHOT.jar` by following [build from the source](#build-from-the-source).


2. Copy the `org.wso2.carbon.identity.conditional.auth.functions.grpc-1.1.7-SNAPSHOT.jar` file into `{wso2is-home}/repository/component/dropins` directory.


3. Follow this [WSO2 Identity Server Official Documentatio](https://is.docs.wso2.com/en/latest/learn/configuring-a-service-provider-for-adaptive-authentication/) to configure a service provider in WSO2 Identity Server.


4. From the Identity Server Management Console, navigate to the Service Providers section listed under the Main tab.


<img src="https://github.com/NuwangaHerath/images/blob/master/adaptive-1.png" align="center" width="400">


5. Edit the Service Provider configured in the previous step.


<img src="https://github.com/NuwangaHerath/images/blob/master/adaptive-2.png" align="center" width="700">


6. Expand the Local and Outbound Authentication Configuration section and click on Advanced Configuration.


<img src="https://github.com/NuwangaHerath/images/blob/master/adaptive-3.png" align="center" width="700">


7. Add an another authentication step as step 2. In this demo we add `totp` as Authentication step 2.


<img src="https://github.com/NuwangaHerath/images/blob/master/adaptive-4.png" align="center" width="700">


8. Add the below script under `Script Based Adaptive Authentication` and update. Here we used a test script by giving sample JSON Object to the GrpcInvoke function.

- Replace the values of `host` and `port` from the respective values you copied from the [previous step](#implementing-grpc-server).


```jsp
// GrpcInvoke Test Template...

var onLoginRequest = function(context) {
    executeStep(1, {
        onSuccess: function (context) {
            // Extracting authenticated subject from the first step
            var user = context.currentKnownSubject;
            
            // Defines the Json object
            var jsonObject = {"country" : "Sri Lanka",
                             "isRegistered" : true,
                             "age" : 21};
            
            // Invoking gRPC function
            var grpcResponse = grpcInvoke('<host>', '<port>', jsonObject, {
                onSuccess : function(context, data) {
                    Log.info('Successfully recieved the response from gRPC service.');
                    executeStep(2);
                   
                }, onFail : function(context, data) {
                    Log.info('Failed to invoke grpc service.');
                    executeStep(2);
                }
            });
            

        }
    });
};

// End of Grpvinvoke Test...

```


<img src="https://github.com/NuwangaHerath/images/blob/master/adaptive-5.png" align="center" width="700">

### Running the sample
1. Start the gRPC Server.
2. Start the Identity Server by executing the following commands from `{wso2is-hom}/bin` directory.

```sh
For Windows
$ wso2server.bat --run

For Linux
$ sh wso2server.sh
```
3. You should be able to see the following log when the event handler is activated.
```
INFO {org.wso2.carbon.identity.conditional.auth.functions.grpc.internal.GrpcInvokeFunctionServiceComponent} - GrpcInvokeFunction is activated successfully.
```
4. Open any web browser and navigate the URL of the application you configured in [Configuring Identity Server](configuring-identity-server). In this demo it is http://wso2is.local:8080/saml2-web-app-pickup-dispatch.com/.
5. Try to log in to the application.
6. You should be able to see the following logs, 
  - In case of success,
```
INFO {org.wso2.carbon.identity.application.authentication.framework.config.model.graph.js.JsLogger} - Successfully recieved the response from gRPC service.

```
  - In case of failure,
```
INFO {org.wso2.carbon.identity.application.authentication.framework.config.model.graph.js.JsLogger} - Failed to invoke grpc service.

```

## Build from the source

1. Download/Clone the project into your local machine.
2. Open a terminal from the project directory of your machine.
2. Build the project using maven by executing the following command in the terminal.
```sh
$ mvn clean install
```
3. Copy the `org.wso2.carbon.identity.conditional.auth.functions.grpc-1.1.7-SNAPSHOT.jar` file from `target` directory.
