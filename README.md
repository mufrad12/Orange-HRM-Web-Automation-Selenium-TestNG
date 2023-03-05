# Orange HRM Web Automation Selenium TestNG
### This is a complete project where a Demo site is automated by writing test suites using selenium-webdriver and TestNg as testing framework.

## Scenerio
1. Login to orange hrm demo site
https://opensource-demo.orangehrmlive.com/
2. Create 2 new employees and save it to a JSON list
3. Now go to PIM dashboard and search by 1st user name. Assert that the user is found.
4. Now click on the user from the search table and update id by random userid
5. Now again search the user by new user id from the PIM dashboard menu and assert that the user is found
6. Now logout from admin and login with the 2nd user from your JSON list
7. Now click on My Info menu
8. Select Gender and Blood Type and save it
9. Click on contact details and input address and email
10. Logout the user

## Technology and Tool Used
- Selenium Webdriver
- TestNG
- Java
- Gradle
- Intellij Idea 
- Allure

## How to run this project
- Clone this project
- Hit the following command into the terminal:
 ```gradle clean test```
 
- For generating Allure Report use these commands:
```allure generate allure-results --clean -o allure-report``` and
```allure serve allure-results```    
 
## Prerequisite
- TestNG,Selenium Webdriver,Java-8 or higher dependencies must be installed

## Allure Report:
![Screenshot_20230228_082316](https://user-images.githubusercontent.com/58912515/221887116-d9ce2fb8-1d8a-4285-962e-043d73ed31b1.png)
![Screenshot_20230228_082527](https://user-images.githubusercontent.com/58912515/221887129-9badf7d4-42fb-4f10-9e44-0ed445180719.png)

## Automation Video Output:
https://user-images.githubusercontent.com/58912515/221886880-56d55fbb-5697-40f0-bdb5-465c3d01a0f2.mp4

