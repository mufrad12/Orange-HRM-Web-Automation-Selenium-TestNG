package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMPage {
    @FindBy(className = "oxd-button--secondary")
    List<WebElement> button;

    @FindBy(className = "oxd-input")
    public List<WebElement> txtInput;

    @FindBy(className = "oxd-switch-input")
    public WebElement btnToggle;

    @FindBy(css = "[type=submit]")
    public WebElement btnSubmit;

    @FindBy(className = "oxd-main-menu-item") // go to PIM
    public List<WebElement> menuItem;

    @FindBy(className = "oxd-input--active") // Search emp
    public List<WebElement> txtInputEmpId;

    @FindBy(css = "[name=firstName]")
    WebElement txtFirstName;
    @FindBy(css = "[name=lastName]")
    WebElement txtLastName;

    @FindBy(className = "orangehrm-employee-list")
    public WebElement empList;

    @FindBy(tagName = "button")
    public List<WebElement> btn;


    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void createEmployeeWithoutUsername(String firstname, String lastname, String employeeId, String password,String confirmPassword) throws InterruptedException {
        txtFirstName.sendKeys(firstname);
        txtLastName.sendKeys(lastname);

        WebElement empID = txtInput.get(4);
        Thread.sleep(1000);
        empID.clear();
        empID.sendKeys(Keys.CONTROL + "a");
        empID.sendKeys(employeeId);

        btnToggle.click();

        txtInput.get(6).sendKeys(password); //input password
        txtInput.get(7).sendKeys(confirmPassword);//confirm password
        Thread.sleep(1500);
        btnSubmit.click();
    }

    public void createEmployee(String firstName, String lastName, String username,String empIdStr, String password,String confirmPassword) throws InterruptedException {
        //button.get(1).click();
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);


        WebElement empID = txtInput.get(4);

        empID.clear();
        empID.sendKeys(Keys.CONTROL+"a"); // clear existing emp id
        empID.sendKeys(empIdStr);

        btnToggle.click();

        txtInput.get(5).sendKeys(username); //input username
        txtInput.get(6).sendKeys(password); //input password
        txtInput.get(7).sendKeys(confirmPassword);//confirm password
        Thread.sleep(1500);
        btnSubmit.click();
    }
    public void updateEmployee(String employeeId) throws InterruptedException {
        txtInput.get(5).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        Thread.sleep(1000);
        txtInput.get(5).sendKeys(employeeId);
        Thread.sleep(1500);
        btn.get(1).click();
    }

    public void SearchEmployee(String randomEmployeeId) throws InterruptedException {
        txtInput.get(1).sendKeys(randomEmployeeId);
        Thread.sleep(1500);
        btnSubmit.click();
    }

    public void clickOnPIM(){
        menuItem.get(1).click();
    }



}
