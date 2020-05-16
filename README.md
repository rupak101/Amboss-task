# Web Automation Test Suite

<br/>This automated web test suite covers all the test cases mentioned in code challenge file.

# Libraries Used

* Selenium:
    * To incorporate web tests.
* TestNG:
    * To perform parallel execution of test.
    * To perform cross browser/platform test.
    
# Features:

* Generation human readable allure report
    - HTML Reports are available in the "/target/allure-report" directory having details of each test case execution 
    and also take screenshot of failed test cases.
* WebDriver factory
    - WebDriverFactory class added as part of base package to enable WebDriver initialization for cross browser and platform test.
* Encapsulation layers like test data, logic of tests, actions on web pages and so on
    - Page object model design pattern used to have a clean separation of layers consisting of test data, logic
    and actions on web pages.
* Configurator(via custom-session.xml file):
  * run tests in parallel mode;
    - Test cases executed in parallel with multiple threads.
  * ability to run tests for different browsers by configuring;
    - Test can run for chrome/firefox browsers using parameters in custom-session.xml file.
* Allure report: 
  *Integrate to defect tracking system by using @link
  *Test order by severity by using @Severity annotation.
  *Tests groups with @Epic, @Feature, and @Stories annotations.

# Steps to execute the project:

* Method to run in Terminal:
    * Go to project folder(i.e Amboss-task) in the terminal or command line
    * Execute via terminal or command line by entering following command.
    
    Run the below command by replacing the all the [values] with actual values:
    ```
    mvn clean test allure:report -D username=[username]  -D password=[password]
    ``` 
    Run the command to generate allure report and open it in a browser: 
    ```bash
    mvn allure:serve
    ```
    