package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashboardPage;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    @Test(priority = 1,description = "User cannot do login successfully")
    public void doLoginWithInvalidCreds(){
        loginPage = new LoginPage(driver);
        String message_actual = loginPage.doLoginWithInvalidCreds("admin","wrongpass");
        String message_expected = "Invalid credentials";
        Assert.assertTrue(message_actual.contains(message_expected));
    }

    @Test(priority = 2,description = "User can do login successfully")
    public void doLogin() throws IOException, ParseException {
        loginPage=new LoginPage(driver);
        JSONObject userObject = Utils.loadJSONFiles("./src/test/resources/Cred.json", 0);
        String username = userObject.get("username").toString();
        String password = userObject.get("password").toString();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.doLogin(username, password);

        WebElement headerText=driver.findElement(By.tagName("h6"));
        String header_actual= headerText.getText();
        String header_expected="Dashboard";
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(header_actual,header_expected);
        Assert.assertEquals(header_actual,header_expected);
        WebElement profileImageElement= driver.findElement(By.className("oxd-userdropdown-img"));
        softAssert.assertTrue(profileImageElement.isDisplayed());
        softAssert.assertAll();
    }
}
