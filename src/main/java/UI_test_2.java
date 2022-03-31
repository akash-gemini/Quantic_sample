import com.gemini.automation.dataProvider.QuanticDataProvider;
import com.gemini.automation.generic.DriverAction;
import com.gemini.automation.generic.ProjectProperties;
import com.gemini.automation.generic.QuanticUIBase;
import com.gemini.automation.generic.TestCaseData;
import com.google.gson.JsonObject;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class UI_test_2 extends QuanticUIBase {

//    @BeforeMethod(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
//    public class beforeMethod(JsonObject inputData){
//
//    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Test1(JsonObject inputData) {
        GemTestReporter.addTestStep("IN TEST 2", "in test 2", STATUS.PASS);

        System.out.println("In Test");
        System.out.println(inputData);
        System.out.println(TestCaseData.getCurrentTestCaseData());
        System.out.println(TestCaseData.getTestCaseID());
        System.out.println(TestCaseData.getTestCaseCategory());
        System.out.println(TestCaseData.getTestCaseRunFlag());
        System.out.println(TestCaseData.getTestCaseInputData());
        JsonObject data = TestCaseData.getTestCaseInputData();
        System.out.println("ID = " + data.get("id").getAsString());
        System.out.println(ProjectProperties.getSize());
        System.out.println(ProjectProperties.isEmpty());
        System.out.println(ProjectProperties.getStringPropertyNames());
        System.out.println(ProjectProperties.getKeySet());
        DriverAction driverAction = new DriverAction();
        System.out.println(driverAction.getAccessibleName(By.xpath("(//input)[1]")));
        driverAction.click(By.xpath("//a[@aria-label='Google apps']"), "Google Apps");
        driverAction.navigateToUrl("https://stackoverflow.com/questions/55201446/how-to-run-test-cases-in-parallel");
    }
}
