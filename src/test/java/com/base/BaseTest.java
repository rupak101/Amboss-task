package com.base;

import com.TestFailureListener;
import com.framework.driverconfiguration.DriverCreator;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.*;

/**
 * @Author: Rupak Mansingh
 * @Desc: Base class provides all the members & functions to be made visible for uiTest classes and page objects
 */
@Listeners(TestFailureListener.class)
public class BaseTest {

    private static final Logger log = Logger.getLogger(BaseTest.class.getName());

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        log.info("Initializing web driver");
        DriverCreator.setDriver(browser);
    }

    /**
     * Method to be used for getting the status of the uiTest execution and close the driver
     */
    @AfterMethod
    public void quiteDriverAfterTest() {
        DriverCreator.quitDriver();
        log("Quit driver and browser closed");
    }

    /**
     * Logging method so that the same log is added in logger as well as in TestNG Report
     *
     * @param data
     */
    public void log(String data) {
        log.info(data);
        Reporter.log(data + "\n");
    }
}
