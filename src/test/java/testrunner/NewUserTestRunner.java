package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PDetailPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class NewUserTestRunner extends Setup {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    PDetailPage pDetailPage;

    @Test(priority = 1, description = "Login With Second User")
    public void doLoginWithSecondUsers() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        JSONObject userObject = Utils.loadJSONFiles("./src/test/resources/Cred.json", 2);
        String username = userObject.get("username").toString();
        String password = userObject.get("password").toString();
        loginPage.doLogin(username, password);
        Thread.sleep(1500);

        WebElement headerText = driver.findElement(By.tagName("h6"));
        String header_actual = headerText.getText();
        String header_expected = "Dashboard";
        Assert.assertEquals(header_actual, header_expected);
        Thread.sleep(1500);

    }

    @Test(priority = 2, description = "Insert 2nd Employee's Information ")
    public void updateUserInformation() throws IOException, ParseException, InterruptedException {
        pDetailPage=new PDetailPage(driver);

        pDetailPage.userMenu.get(2).click();
        Utils.doScroll(driver);

        pDetailPage.chooseGender();
        Thread.sleep(1000);
        Utils.doScroll(driver);

        pDetailPage.chooseBloodType();
        Thread.sleep(1000);
        driver.navigate().refresh();

        pDetailPage.chooseContact();
        Thread.sleep(2000);

        WebElement headerText = driver.findElement(By.tagName("h6"));
        String header_actual = headerText.getText();
        String header_expected = "PIM";
        Assert.assertEquals(header_actual, header_expected);
        Thread.sleep(1000);
    }

    @Test(priority = 3,description = "2nd User Logout Successfully")
    public void LogOut() {
       DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doLogout();
        driver.close();
   }
}
