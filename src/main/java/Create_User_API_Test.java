import com.gemini.automation.ApiTest.ApiClientConnect;
import com.gemini.automation.ApiTest.ProjectApiUrl;
import com.gemini.automation.ApiTest.ProjectSampleJson;
import com.gemini.automation.dataProvider.QuanticDataProvider;
import com.gemini.automation.generic.QuanticAPIBase;
import com.google.gson.JsonObject;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Create_User_API_Test extends QuanticAPIBase {
    //[akash,garg,rGHAV]
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class,priority = 0)
    public void createUser(JsonObject inputData) {

        Boolean isExcecutionException = false;
        JsonObject response = new JsonObject();

        GemTestReporter.addTestStep("<b>Test case Description</b>", "Test to create a new user", STATUS.INFO);

        // Reading Url of the api
        String url = ProjectApiUrl.getUrl("Create_User");
        GemTestReporter.addTestStep("<b>Endpoint to create user<b>", url, STATUS.INFO);

        // Building Input JSON for the request
        JsonObject req = new JsonObject();
        String username = "User_" + ((int) (Math.random() * 99) + 1);
        req.addProperty("username", username);
        req.addProperty("firstName", username);
        req.addProperty("lastName", "User_Last");
        req.addProperty("email", username + "@gmail.com");
        req.addProperty("password", "Password");
        req.addProperty("company", "Dummy");

        GemTestReporter.addTestStep("<b>JSON Body to create user<b>", req.toString(), STATUS.INFO);

        HashMap<String,String> header = new HashMap<String, String>();
        header.put("Content-Type","application/json");
//        header.put("AuthToken","hjvsdyawvdw");

        // Executing the request
        try {
             //response = api.httpPostRequest(url, req.toString(), "json");
            response = ApiClientConnect.PostRequest(url,req.toString(),"json");
             System.out.println("The response is ------> "+ response);
            GemTestReporter.addTestStep("<b>Execute the request<b>","POST request to create user executed successfully",STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("<b>Execute the request<b>","POST request to create user unsuccessful",STATUS.FAIL);
            isExcecutionException = true;
        }

        if(!isExcecutionException){
            String status = response.get("status").getAsString();
            JsonObject responseBody = response.get("responseBody").getAsJsonObject();
            JsonObject data = responseBody.get("data").getAsJsonObject();
            String bridgeToken = data.get("bridgeToken").getAsString();
            String message = responseBody.get("message").getAsString();
            String operation = responseBody.get("operation").getAsString();

//            System.out.println("The status is --------> "+ status);
//            System.out.println("The bridgeToken is --------> "+ bridgeToken);
//            System.out.println("The message is --------> "+ message);
//            System.out.println("The operation is --------> "+ operation);

           // Verifying the status code
            if(status.equalsIgnoreCase("201")){
                GemTestReporter.addTestStep("<b>Verify the status code<b>","Expected Status Code = 201 <br> Actual Status code " + status,
                        STATUS.PASS);
            }else{
                GemTestReporter.addTestStep("<b>Verify the status code<b>","Expected Status Code = 201 <br> Actual Status code " + status,
                        STATUS.FAIL);
            }

            // Verify if Bridge token is generated
            if(bridgeToken.length()>0){
                GemTestReporter.addTestStep("<b>Verify bridge token created<b>","Bridge token is generated successfully. Generated bridge token is "+bridgeToken,
                        STATUS.PASS);
            }else {
                GemTestReporter.addTestStep("<b>Verify bridge token created<b>","Bridge token generation failed.", STATUS.FAIL);
            }

        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void createConflictUser(JsonObject inputData) {

        Boolean isExcecutionException = false;
        JsonObject response = new JsonObject();

        GemTestReporter.addTestStep("<b>Test Case Description</b>", "Test to create a conflict user", STATUS.INFO);

        // Reading Url of the api
        String url = ProjectApiUrl.getUrl("Create_User");
        GemTestReporter.addTestStep("<b>Endpoint to create user</b>", url, STATUS.INFO);

        // Reading JSON file to create user
        String req = ProjectSampleJson.getSampleDataString("createConflictUser_sampleJson");
        GemTestReporter.addTestStep("<b>JSON Body to create user<b>", req.toString(), STATUS.INFO);

        // Executing the request
        try {
            //response = api.httpPostRequest(url, req.toString(), "json");
            response = ApiClientConnect.PostRequest(url,req.toString(),"json");
            System.out.println("The response is ------> "+ response);
            GemTestReporter.addTestStep("<b>Execute the request<b>","POST request to create user executed successfully",STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("<b>Execute the request<b>","POST request to create user unsuccessful",STATUS.FAIL);
            isExcecutionException = true;
        }

        if(!isExcecutionException){
            String status = response.get("status").getAsString();
            JsonObject responseError = response.get("responseError").getAsJsonObject();
            String message = responseError.get("message").getAsString();

//            System.out.println("The status is --------> "+ status);
//            System.out.println("The bridgeToken is --------> "+ bridgeToken);
//            System.out.println("The message is --------> "+ message);
//            System.out.println("The operation is --------> "+ operation);

            // Verifying the status code
            if(status.equalsIgnoreCase("409")){
                GemTestReporter.addTestStep("<b>Verify the status code<b>","Expected Status Code = 409 <br> Actual Status code " + status,
                        STATUS.PASS);
            }else{
                GemTestReporter.addTestStep("<b>Verify the status code<b>","Expected Status Code = 409 <br> Actual Status code " + status,
                        STATUS.FAIL);
            }

            // Verify the response message
            if(message.length()>0 && message.contains("already")){
                GemTestReporter.addTestStep("<b>Verify the response message<b>","The response message is " + message,
                        STATUS.PASS);
            }else{
                GemTestReporter.addTestStep("<b>Verify the response message<b>","The response message is " + message,
                        STATUS.FAIL);
            }
        }
    }
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void createUserWithMissingProperty(JsonObject inputData) {

        Boolean isExcecutionException = false;
        JsonObject response = new JsonObject();

        GemTestReporter.addTestStep("<b>Test Case Description</b>", "Test to create a conflict user", STATUS.INFO);

        // Reading Url of the api
        String url = ProjectApiUrl.getUrl("Create_User");
        GemTestReporter.addTestStep("<b>Endpoint to create user</b>", url, STATUS.INFO);

        // Reading JSON file to create user
        String req = ProjectSampleJson.getSampleDataString("createUserMissingProperty_sampleJson");
        GemTestReporter.addTestStep("<b>JSON Body to create user<b>", req.toString(), STATUS.INFO);

        // Executing the request
        try {
            //response = api.httpPostRequest(url, req.toString(), "json");
            response = ApiClientConnect.PostRequest(url,req.toString(),"json");
            System.out.println("The response is ------> "+ response);
            GemTestReporter.addTestStep("<b>Execute the request<b>","POST request to create user executed successfully",STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("<b>Execute the request<b>","POST request to create user unsuccessful",STATUS.FAIL);
            isExcecutionException = true;
        }

        if(!isExcecutionException){
            String status = response.get("status").getAsString();
            JsonObject responseError = response.get("responseError").getAsJsonObject();
            String message = responseError.get("message").getAsString();

//            System.out.println("The status is --------> "+ status);
//            System.out.println("The bridgeToken is --------> "+ bridgeToken);
//            System.out.println("The message is --------> "+ message);
//            System.out.println("The operation is --------> "+ operation);

            // Verifying the status code
            if(status.equalsIgnoreCase("500")){
                GemTestReporter.addTestStep("<b>Verify the status code<b>","Expected Status Code = 500 <br> Actual Status code " + status,
                        STATUS.PASS);
            }else{
                GemTestReporter.addTestStep("<b>Verify the status code<b>","Expected Status Code = 500 <br> Actual Status code " + status,
                        STATUS.FAIL);
            }

            // Verify the response message
            if(message.length()>0 && message.contains("Exception")){
                GemTestReporter.addTestStep("<b>Verify the response message<b>","The response message is " + message,
                        STATUS.PASS);
            }else{
                GemTestReporter.addTestStep("<b>Verify the response message<b>","The response message is " + message,
                        STATUS.FAIL);
            }
        }
    }
}