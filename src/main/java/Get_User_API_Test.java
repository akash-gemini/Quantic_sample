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

public class Get_User_API_Test extends QuanticAPIBase {
    // Instantiating ApiClientConnectImp class
    ApiClientConnect api = new ApiClientConnect();
    ParameterizedUrl parameterizedUrl = new ParameterizedUrl();

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class, priority = 0)
    public void validateValidUser(JsonObject inputData) {

        GemTestReporter.addTestStep("<b>Test Case Description<b>","Test case to validate a valid username",STATUS.INFO);
        Boolean isExcecutionException = false;
        JsonObject response = new JsonObject();

        String url = ProjectApiUrl.getUrl("validate_User");
        HashMap<String,String> param = new HashMap<String,String>();
        param.put("username","user123");

        String pUrl = parameterizedUrl.getParameterizedUrl(url,param);
        GemTestReporter.addTestStep("<b>Endpoint to validate user<b>",pUrl,STATUS.INFO);

        // Executing the request
        try{
            //response = api.httpGetRequest(pUrl);
            response = ApiClientConnect.GetRequest(pUrl);
            System.out.println("The response is ------> "+response);
            GemTestReporter.addTestStep("<b>Execute the GET request<b>","GET request to validate user executed successfully",STATUS.PASS);
        }catch (Exception e){
            GemTestReporter.addTestStep("<b>Execute the GET request<b>","GET request to validate user unsuccessful",STATUS.FAIL);
            isExcecutionException = true;
        }

        if(!isExcecutionException){
            String status = response.get("status").getAsString();
            JsonObject responseBody = response.get("responseBody").getAsJsonObject();
            JsonObject data = responseBody.get("data").getAsJsonObject();
            String isPresent = data.get("isPresent").getAsString();
            String message = responseBody.get("message").getAsString();
            String operation = responseBody.get("operation").getAsString();

            // Verifying the status code
            if(status.equalsIgnoreCase("200")){
                GemTestReporter.addTestStep("<b>Verify the status code<b>","Expected Status Code = 200 <br> Actual Status code " + status,
                        STATUS.PASS);
            }else{
                GemTestReporter.addTestStep("<b>Verify the status code<b>","Expected Status Code = 200 <br> Actual Status code " + status,
                        STATUS.FAIL);
            }

            // Verify if user is present
            if(isPresent.equalsIgnoreCase("true")){
                GemTestReporter.addTestStep("<b>Verify that Username exists<b>","Username existence verified successfully. <br>isPresent = "+isPresent,STATUS.PASS);
            }else {
                GemTestReporter.addTestStep("<b>Verify that Username exists<b>","Username does not exist. <br>isPresent = "+isPresent,STATUS.FAIL);
            }

        }

    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class, priority = 0)
    public void validateInvalidUser(JsonObject inputData) {

        GemTestReporter.addTestStep("<b>Test Case Description<b>","Test case to validate a Invalid username",STATUS.INFO);
        Boolean isExcecutionException = false;
        JsonObject response = new JsonObject();

        String url = ProjectApiUrl.getUrl("validate_User");
        HashMap<String,String> param = new HashMap<String,String>();
        param.put("username","Invalid_User");

        String pUrl = parameterizedUrl.getParameterizedUrl(url,param);
        GemTestReporter.addTestStep("<b>Endpoint to validate user<b>",pUrl,STATUS.INFO);

        // Executing the request
        try{
            // response = api.httpGetRequest(pUrl);
            response = ApiClientConnect.GetRequest(pUrl);
            System.out.println("The response is ------> "+response);
            GemTestReporter.addTestStep("<b>Execute the GET request<b>","GET request to validate user executed successfully",STATUS.PASS);
        }catch (Exception e){
            GemTestReporter.addTestStep("<b>Execute the GET request<b>","GET request to validate user unsuccessful",STATUS.FAIL);
            isExcecutionException = true;
        }

        if(!isExcecutionException){
            String status = response.get("status").getAsString();
            JsonObject responseBody = response.get("responseBody").getAsJsonObject();
            JsonObject data = responseBody.get("data").getAsJsonObject();
            String isPresent = data.get("isPresent").getAsString();
            String message = responseBody.get("message").getAsString();
            String operation = responseBody.get("operation").getAsString();

            // Verifying the status code
            if(status.equalsIgnoreCase("200")){
                GemTestReporter.addTestStep("<b>Verify the status code<b>","Expected Status Code = 200 <br> Actual Status code " + status,
                        STATUS.PASS);
            }else{
                GemTestReporter.addTestStep("<b>Verify the status code<b>","Expected Status Code = 200 <br> Actual Status code " + status,
                        STATUS.FAIL);
            }

            // Verify if user is present
            if(isPresent.equalsIgnoreCase("false")){
                GemTestReporter.addTestStep("<b>Verify that Invalid Username exists<b>","Invalid Username does not exist. <br>isPresent = "+isPresent,STATUS.PASS);
            }else {
                GemTestReporter.addTestStep("<b>Verify that Invalid Username exists<b>","Invalid Username exists <br>isPresent = "+isPresent,STATUS.FAIL);
            }

        }

    }
}