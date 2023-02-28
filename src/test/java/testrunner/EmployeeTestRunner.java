package testrunner;
import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PDetailPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;


import java.io.IOException;
import java.util.List;


public class EmployeeTestRunner extends Setup {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    PIMPage pimPage;
    Utils utils;

    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONObject userObject = Utils.loadJSONFile("./src/test/resources/AdminCred.json");
        String username = (String) userObject.get("username");
        String password = (String) userObject.get("password");
        loginPage.doLogin(username, password);
    }

    @Test(priority = 1, description = "Creating Employee without Username")
    public void createEmployeeWithoutUsername() throws InterruptedException, IOException, ParseException {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.menus.get(1).click(); //Click on PIM menu

        PDetailPage pDetailPage = new PDetailPage(driver);
        pDetailPage.topBarItem.get(2).click(); // click on add employee
        Thread.sleep(3000);

        pimPage = new PIMPage(driver);

        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        int empId = Utils.generateNumber(10000, 99999);
        String employeeId = String.valueOf(empId);
        String password = "P@ssword123";
        String confirmPassword = password;
        Thread.sleep(1500);
        pimPage.createEmployeeWithoutUsername(firstname, lastname, employeeId,password,confirmPassword);

        String header_actual = driver.findElements(By.className("oxd-text")).get(15).getText();
        String header_expected = "Required";
        org.junit.Assert.assertTrue(header_actual.contains(header_expected));
        driver.navigate().refresh();

    }

    @Test(priority = 2, description = "Creating 1st Employee")
    public void createEmployee1() throws InterruptedException, IOException, ParseException {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.menus.get(1).click(); //Click on PIM menu

        PDetailPage pDetailPage = new PDetailPage(driver);
        pDetailPage.topBarItem.get(2).click(); // click on add employee
        Thread.sleep(3000);

        pimPage = new PIMPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();//taking firstname from faker
        String lastName=faker.name().lastName();//taking lastname from faker

        utils = new Utils();
        int empIdInt = Utils.generateNumber(10000, 99999);
        String empIdStr = String.valueOf(empIdInt);//taking generate number

        WebElement empID = pimPage.txtInput.get(4);

        empID.clear();
        empID.sendKeys(Keys.CONTROL + "a");
        empID.sendKeys(empIdStr);

        String username = "test" + Utils.generateNumber(1000, 9999);//taking generate username
        String password = "P@ssword123";//taking password
        String confirmPassword = password;

        Thread.sleep(5000);
        pimPage.createEmployee(firstName, lastName, username, empIdStr, password,confirmPassword);
        Thread.sleep(7000);

        String header_actual= driver.findElement(By.className("orangehrm-main-title")).getText();
        String header_expected="Personal Details";
        Assert.assertTrue(header_actual.contains(header_expected));

        Utils.addJsonList(firstName,lastName,empIdStr, username, password,confirmPassword);

        pDetailPage.topBarItem.get(2).click();
        Thread.sleep(3000);

    }

    @Test(priority = 3, description = "Creating 2nd Employee")
    public void createEmployee2() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();//taking firstname from faker
        String lastName=faker.name().lastName();//taking lastname from faker

        utils = new Utils();
        int empIdInt = Utils.generateNumber(10000, 99999);
        String empIdStr = String.valueOf(empIdInt);//taking generate number

        WebElement empID = pimPage.txtInput.get(4);

        empID.clear();
        empID.sendKeys(Keys.CONTROL + "a");
        empID.sendKeys(empIdStr);

        String username = "test" + Utils.generateNumber(1000, 9999);//taking generate username
        String password = "P@ssword123";//taking password
        String confirmPassword = password;

        Thread.sleep(5000);
        pimPage.createEmployee(firstName, lastName, username, empIdStr, password,confirmPassword);
        Thread.sleep(7000);

        String header_actual= driver.findElement(By.className("orangehrm-main-title")).getText();
        String header_expected="Personal Details";
        Assert.assertTrue(header_actual.contains(header_expected));

        Utils.addJsonList(firstName,lastName,empIdStr, username, password,confirmPassword);

    }

    @Test(priority = 4, description = "Search Employee By Id")
    public void searchEmployee() throws InterruptedException, IOException, ParseException {

        pimPage = new PIMPage(driver);
        PDetailPage pDetailPage = new PDetailPage(driver);
        pimPage.clickOnPIM();

        String fileName = "./src/test/resources/EmployeeList.json";
        JSONArray jsonArray = (JSONArray) Utils.readJSONArray(fileName);
        int indexOfFirstEmp = jsonArray.size() - 2;

        JSONObject firstEmp = new JSONObject();
        firstEmp = (JSONObject) jsonArray.get(indexOfFirstEmp);
        String firstEmpId = (String) firstEmp.get("empIdStr");

        Thread.sleep(2500);
        pimPage.txtInputEmpId.get(1).sendKeys(firstEmpId);
        pimPage.btnSubmit.click();

        Utils.doScroll(driver);
        Thread.sleep(500);
        pimPage.empList.click();
        Thread.sleep(3500);

        String actualRecordEmp1 = pimPage.txtInput.get(5).getAttribute("value");
        System.out.println(actualRecordEmp1);


        String expectedRecordEmp1 = firstEmpId;
        Assert.assertTrue(actualRecordEmp1.equals(expectedRecordEmp1));

        }

        @Test(priority = 5, description = "Update Employee Id")
        public void updateEmployee() throws InterruptedException, IOException, ParseException {

            pimPage = new PIMPage(driver);
            int empId = Utils.generateNumber(10000, 99999);
            String randomEmployeeId = String.valueOf(empId);
            Utils.updateEmp("./src/test/resources/EmployeeList.json", "employeeId", randomEmployeeId,0 );
            Utils.doScroll(driver);
            pimPage.updateEmployee(randomEmployeeId);
            Thread.sleep(1500);

            String header_actual = driver.findElements(By.className("orangehrm-main-title")).get(0).getText();
            String header_expected = "Personal Details";
            org.junit.Assert.assertTrue(header_actual.contains(header_expected));
        }

        @Test(priority = 6, description = "Search Updated Employee Id")
        public void searchEmployeeId() throws IOException, ParseException, InterruptedException {

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        dashboardPage.menus.get(1).click();
        JSONObject userObject = Utils.loadJSONFiles("./src/test/resources/EmployeeList.json", 0);
        String employeeId = userObject.get("employeeId").toString();
        pimPage.SearchEmployee(employeeId);
        Thread.sleep(1500);
        Utils.doScroll(driver);

        String message_actual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String message_expected = "Record Found";
        org.junit.Assert.assertTrue(message_actual.contains(message_expected));
        Thread.sleep(1000);

        }

    @Test(priority = 7,description = "Admin Logout Successfully")
    public void logOut() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doLogout();
        driver.close();
    }
}
