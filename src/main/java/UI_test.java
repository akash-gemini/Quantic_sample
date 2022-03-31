import com.gemini.automation.dataProvider.QuanticDataProvider;
import com.gemini.automation.generic.*;
import com.google.gson.JsonObject;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class UI_test extends QuanticUIBase {

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void demo_testcase(JsonObject inputData) {
        GemTestReporter.addTestStep("", "", STATUS.PASS);

        GemTestReporter.addTestStep("Step title", "stepDescription1", STATUS.PASS);

        System.out.println("input data = " + inputData.toString());
        GemTestReporter.addTestStep("input Data", String.valueOf(inputData.toString()), STATUS.INFO);

        System.out.println(TestCaseData.getCurrentTestCaseData());
        GemTestReporter.addTestStep("Current test Case Data", String.valueOf(TestCaseData.getCurrentTestCaseData()), STATUS.INFO);

        System.out.println(TestCaseData.getTestCaseID());
        GemTestReporter.addTestStep("Test Case ID", TestCaseData.getTestCaseID(), STATUS.INFO);

        System.out.println(TestCaseData.getTestCaseCategory());
        GemTestReporter.addTestStep("Test Case category", TestCaseData.getTestCaseCategory(), STATUS.INFO);

        System.out.println(TestCaseData.getTestCaseRunFlag());
        GemTestReporter.addTestStep("Test Case run Flag", TestCaseData.getTestCaseRunFlag(), STATUS.INFO);

        System.out.println(TestCaseData.getTestCaseInputData());
        GemTestReporter.addTestStep("Test Case input Data", String.valueOf(TestCaseData.getTestCaseInputData().toString()), STATUS.INFO);

        JsonObject data = TestCaseData.getTestCaseInputData();
        System.out.println("ID = " + data.get("id").getAsString());
        System.out.println(ProjectProperties.getSize());
        System.out.println(ProjectProperties.isEmpty());
        System.out.println(ProjectProperties.getStringPropertyNames());
        GemTestReporter.addTestStep("Project properties names", ProjectProperties.getStringPropertyNames().toString(), STATUS.INFO);

        System.out.println(ProjectProperties.getKeySet());
//        System.out.println(DriverAction.getAccessibleName(By.xpath("(//input)[1]")));
        WebElement bar = DriverAction.getElement(By.xpath("(//input)[1]"));

        DriverAction.typeText(bar, "Gemini solutions", "search bar");
//        GemTestReporter.addTestStep("Type on Search Bar", "text type = Gemini Solutions", STATUS.PASS);

        bar.sendKeys(Keys.ENTER);
        GemTestReporter.addTestStep("Send Keys", "Send Keys = ENTER", STATUS.INFO);

        DriverAction.navigateBack();
//        GemTestReporter.addTestStep("Navigate Back", "Navigate back to google home page ", STATUS.PASS);

        DriverAction.click(By.xpath("//a[@aria-label='Google apps']"), "Google Apps");
//        GemTestReporter.addTestStep("Click on Google app button", "Click Successful", STATUS.PASS);

        DriverAction.navigateToUrl("https://www.linkedin.com/company/gemini-solutions-india");
//        GemTestReporter.addTestStep("Use navigate to functions", "Navigate to https://www.linkedin.com/company/gemini-solutions-india ", STATUS.PASS);

        DriverAction.launchUrl("https://www.geminisolutions.com/");
//        GemTestReporter.addTestStep("Use LaunchUrl to function", "launch to https://www.geminisolutions.com/", STATUS.PASS);

        String page_title = DriverAction.getTitle("https://www.geminisolutions.com/");
//        GemTestReporter.addTestStep("Get Title name of page https://www.geminisolutions.com/ ", "title name = "+page_title, STATUS.PASS);

        System.out.println("title name = " + page_title);
        DriverAction.minimizeBrowser();
//        GemTestReporter.addTestStep("minimize browser", "minimize browser successful", STATUS.PASS);

        DriverAction.maximizeBrowser();
//        GemTestReporter.addTestStep("maximize browser", "maximize browser successful ", STATUS.PASS);

        Dimension x = null;
        x = DriverAction.getBrowserSize();
        GemTestReporter.addTestStep("Get browser size", "dimension = " + x.getHeight() + "<>" + x.getWidth(), STATUS.PASS);

        System.out.println("dimension = " + x.getHeight() + "<>" + x.getWidth());
        DriverAction.setBrowserSize(x.getWidth(), x.getHeight());
        Point p = DriverAction.getBrowserLocation();
        System.out.println("position = " + p.getX() + "<>" + p.getY());
        GemTestReporter.addTestStep("Get browser positions", "position = " + p.getX() + "<>" + p.getY(), STATUS.PASS);

        DriverAction.setBrowserPosition(p.getX(), p.getY());
        String current_url = DriverAction.getCurrentURL();
        GemTestReporter.addTestStep("get Current URL", "current url = " + current_url, STATUS.PASS);

        System.out.println("current url = " + current_url);
        String window_handle = DriverAction.getWindowHandle();
        System.out.println("Window handle = " + window_handle);
        GemTestReporter.addTestStep("get Window handle", "Window handle = " + window_handle, STATUS.PASS);

        String page_source = DriverAction.getPageSource();
//        System.out.println("Page Source = " + page_source);
        DriverAction.click(By.xpath("//*[@id=\"header\"]/div/div/div[2]/nav[1]/ul/li/a"), "Search button");
//        GemTestReporter.addTestStep("Click on Search button of geminisolutions.com", "Click Successful", STATUS.PASS);

//        DriverAction.setImplicitTimeOut(1);
//        DriverAction.setScriptTimeOut(1);
//        DriverAction.setPageLoadTimeOut(1);
        DriverAction.navigateBack();
//        GemTestReporter.addTestStep("Navigate Back", " Successful", STATUS.PASS);

        DriverAction.navigateForward();
//        GemTestReporter.addTestStep("Navigate Forward", " Successful", STATUS.PASS);

        DriverAction.navigateRefresh();
//        GemTestReporter.addTestStep("Navigate Refresh", " Successful", STATUS.PASS);
        try {
            String loc = DriverAction.takeSnapShot();
            System.out.println("ss loc = " + loc);
//            GemTestReporter.addTestStep("Take screenshot", "ScreenShot loc : " + loc, STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Error occur while taking screen shot", "ERROR", STATUS.FAIL);
            e.printStackTrace();
        }
//        GemTestReporter.addTestStep("Close Driver", " Successful", STATUS.PASS);

        GemTestReporter.addTestStep("Demo Fail", "Fail", STATUS.FAIL);
        GemTestReporter.addTestStep("Demo EXE", "EXE", STATUS.EXE);
        GemTestReporter.addTestStep("Demo INCOMPLETE", "INCOMPLETE", STATUS.INCOMPLETE);
        GemTestReporter.addTestStep("Demo WARN", "WARN", STATUS.WARN);

    }

}
