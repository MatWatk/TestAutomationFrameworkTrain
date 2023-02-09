# TestAutomationFrameworkTrain
This is my framework which I'm trying to update as often as possible. It has been made to train, consolidate and to try out my learned skills.

Description of the framework:
The framework has been created in Java language using Selenium and TestNG.
Inside framework we have following packages: Resources (framework properties), AbstractComponent (reusable java methods), PageObjects (methods intended to specific pages) which are our java core to use for the tests.
There are also packages where are placed classes written in TestNG: TestComponents (our core to all tests with reusable methods) abd Tests.

Description of tests (xml files):
1. DevTools - performing all tests which uses DevTools.
2. ErrorValidation - performing test cases: loginErrorValidation, emptyCheckoutCheck.
3. GeneralTest - performing test cases: orderTest, cartCheck, emptyCartCheckoutCheck, loginErrorValidation, checkAllBackbuttons, checkoutTextboxesBasicCheck, checkoutTexboxesFullCheck, websiteOnDifferentDeviceSize, logOnAllUsers, 
4. LoggingIn - performing test cases: manuaLoggingInTest, LogOnSelectedUserFromDatabase, logOnAllUsers
5. Orders - performing test cases: orderTest, cartCheck, emptyCartCheckoutCheck, checkAllBackbuttons, checkoutTextboxesBasicCheck
6. Textboxes - performing test cases: checkoutTextboxesBasicCheck, checkoutTexboxesFullCheck, emptyCheckoutCheck.
All test cases are described below:

Description of classes:
1.StandAloneTest it was my core class from which I've started writing my framework. After creating it I've reorganised everything inside - created PageObject, AbstractComponents, BaseTest and other packages.
2. StandAloneTest2 - after the reorganisation I've rewritten the test using all prepared packages and classes.
3. BaseTest - it's a core test component where are methods like launchApplication (where driver is beeing initialized) and closingBrowser.
4. OrderTest - this is a class where I'm placing methods to test ordering on the website. For now there are few methods (test cases):

  a) orderTest - testing the ordering process from the beggining to the end with normal way (filling everything as it should be filled).
  Test steps:
    * Open website https://www.saucedemo.com/
    * Grab login informations from the bottom of the website (standard_user), copy the login and password and paste it in appropriate places, than clicking on login button.
    * Adding to the card products which prices aren't higher than 10 $ (the price is stored in variable "maxPrice" - in the future, after connecting the framework with Jenkins I'll add a possibility to change max price by user)
    * In this step program is checking if all products in card are cheaper than 10$ (the price is stored in variable "maxPrice")
    * If everything is fine program will click checkout button
    * On the checkout page program will fill all textboxes (I've found one bug here - there is a possibility to write numbers in first name and last name boxes and letters in post code and no error will appear after clicking continue button. Soon I'll add one test case where I will get this bug and add it to generated report)
    * On the checkout overview page program is checking if the total price is being calculated well. If yest program clicks finish button.
    * On the confirmation page program grabs the message and checks if there's word "COMPLETE". If yes everything ends successfully.
  b) cartCheck - testing all buttons "add to cart", clicking on cart and testing all "remove from cart" buttons.
  c) emptyCartCheckoutCheck - this test case checks if there's a possibility to continue ordering process with empty cart (it's second bug because the page shouldn't let the user to get to checkout page with empty cart)
  d) checkAllBackbuttons - test case which check if all back buttons (on every page) works fine
  e) checkoutTextboxesBasicCheck - providing wrong data in checkout page texboxes: in name and last name textboxes providing numbers and in post code textbox providing letters.
  f) checkoutTexboxesFullCheck - testing all variants of provided data in textboxes (eg. numbers in name, letters in last name and numbers in post code in one check).
    
 4. LoginValidation - here are the methods checking logging process:
  a) manualLoggingInTest - for now it grabs the properties from GlobalData.properties file, but in future after connecting framework with Jenkins there will be a possibility to write password and login manually by user.
  b) logOnSelectedUserFromDatabase - after connecting it to Jenkins the user will provide the login and program will get it's password from database (created now on localhost in MySQL). Now it's getting the login from GlobalData.properties file.
  c) logOnAllUsers - logging on all users and checking if there's no problem during this process.
  
 5. ErrorValidation - here are the methods checking the error messages:
   a) loginErrorValidation - providing wrong password and login and checking if the error appears.
   b) emptyCheckoutCheck - checking if error message appears while user will try to continue with empty checkout textboxes.
 
 6. DevTools - here are all methods which will be using DevTools options
   a) websiteOnDifferentDeviceSize - opening the landing page on mobile phone size and taking screenshot of it. Screenshot is being added to the folder screenshot_mobile inside the project.

7. Listeners - this class is used for report generation. The report is being saved in reports folder inside the project.
