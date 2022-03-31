import com.gemini.automation.dataProvider.QuanticDataProvider;
import com.gemini.automation.generic.*;
import com.google.gson.JsonObject;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


/*
 @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Test1(JsonObject inputData) {

    }
    */
public class UI_test_5 extends QuanticUIBase {

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Linkedin_Testcase(JsonObject inputData) throws InterruptedException {

        DriverAction.launchUrl("https://www.linkedin.com/");
        DriverAction.click(By.xpath("//a[@class='nav__button-tertiary']"), "join now");
        DriverAction.getElement(By.xpath("//input[@name='email-or-phone']")).sendKeys("abc@gmail.com");
        GemTestReporter.addTestStep("Typing", "user-name", STATUS.PASS);
        DriverAction.getElement(By.xpath("//input[@name='password']")).sendKeys("Abc@12345");
        GemTestReporter.addTestStep("Typing", "password", STATUS.PASS);
        DriverAction.click(By.xpath("//button[@type='submit']"), "Agree & Join");
        DriverAction.getElement(By.xpath("//input[@id='first-name']")).sendKeys("ABCD");
        GemTestReporter.addTestStep("Typing", "first-name", STATUS.PASS);
        DriverAction.getElement(By.xpath("//input[@id='last-name']")).sendKeys("EFGH");
        GemTestReporter.addTestStep("Typing", "last-name", STATUS.PASS);
        DriverAction.click(By.xpath("//button[@type='submit']"), "Continue");
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Amazon_Testcase(JsonObject inputData) {
        DriverAction.navigateToUrl("https://www.amazon.in/");
        DriverAction.getElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("iphone");
        GemTestReporter.addTestStep("Typing", "iphone", STATUS.PASS);
        DriverAction.click(By.xpath("//input[@id='nav-search-submit-button']"), "search button");
        DriverAction.navigateBack();
        DriverAction.getElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("centre table");
        GemTestReporter.addTestStep("Typing", "centre table", STATUS.PASS);
        DriverAction.click(By.xpath("//input[@id='nav-search-submit-button']"), "search button");
        DriverAction.navigateBack();
        DriverAction.getElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("smart watch");
        GemTestReporter.addTestStep("Typing", "smart watch", STATUS.PASS);
        DriverAction.click(By.xpath("//input[@id='nav-search-submit-button']"), "search button");
        DriverAction.navigateBack();
        DriverAction.getElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("wireless-earphones");
        GemTestReporter.addTestStep("Typing", "wireless earphones", STATUS.PASS);
        DriverAction.click(By.xpath("//input[@id='nav-search-submit-button']"), "search button");
        DriverAction.navigateBack();
        DriverAction.navigateForward();
        DriverAction.navigateRefresh();
        DriverAction.navigateBack();
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Events_Testcase(JsonObject inputData) throws InterruptedException {
        DriverAction.navigateToUrl("https://chercher.tech/practice/practice-pop-ups-selenium-webdriver");

        DriverAction.click(By.xpath("//*[@name='alert']"), "Alert");
        Thread.sleep(1000);
        DriverManager.getWebDriver().switchTo().alert().accept();
        GemTestReporter.addTestStep("Alert Accepted", "Alert-Box", STATUS.PASS);

        DriverAction.click(By.xpath("//*[@name='confirmation']"), "Confirmation box");
        Thread.sleep(1000);
        DriverManager.getWebDriver().switchTo().alert().accept();
        GemTestReporter.addTestStep("Alert Accepted", "Confirmation-Box", STATUS.PASS);

        DriverAction.click(By.xpath("//input[@name='prompt']"), "prompt");
        Thread.sleep(1000);
        GemTestReporter.addTestStep("Typing", "Pawan Deep", STATUS.PASS);
        DriverManager.getWebDriver().switchTo().alert().sendKeys("Pawan Deep");
        GemTestReporter.addTestStep("Typed successfully", "click on OK", STATUS.PASS);
        DriverManager.getWebDriver().switchTo().alert().accept();

        WebElement link = DriverAction.getElement(By.xpath("//input[@id='double-click']"));
        Actions builder = new Actions(DriverManager.getWebDriver());
        Action double_click = builder.moveToElement(link).doubleClick().build();
        double_click.perform();
        GemTestReporter.addTestStep("Doubled Clicked successfully", "double click", STATUS.PASS);
        System.out.println(DriverManager.getWebDriver().switchTo().alert().getText());
        Thread.sleep(1000);

        GemTestReporter.addTestStep("Alert Accepted", "Alert", STATUS.PASS);
        DriverManager.getWebDriver().switchTo().alert().accept();
//        WebElement submenu = DriverAction.getElement(By.xpath("//button[@id='sub-menu']"));
        WebElement submenu = DriverAction.getElement(By.xpath(inputData.get("submenu").getAsString()));

        Actions actions = new Actions(DriverManager.getWebDriver());
        actions.moveToElement(submenu).click();
        actions.perform();
        GemTestReporter.addTestStep("click successfully", "Sub-menu", STATUS.PASS);
        Thread.sleep(1000);

        String a = DriverAction.getElementText(By.xpath("//*[@id='link2']"));
        actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER);
        actions.perform();
        GemTestReporter.addTestStep("clicked successfully", "GOOGLE", STATUS.PASS);
        if (a.equals(DriverAction.getTitleWithoutReporting(DriverAction.getCurrentURL()))) {
            GemTestReporter.addTestStep("Validation Successful", "Expected:" + a + "<br> Current:" + DriverAction.getTitleWithoutReporting(DriverAction.getCurrentURL()), STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Validation failed", "Expected:" + a + "<br> Current:" + DriverAction.getTitleWithoutReporting(DriverAction.getCurrentURL()), STATUS.FAIL);
        }
        Thread.sleep(1000);
    }
}