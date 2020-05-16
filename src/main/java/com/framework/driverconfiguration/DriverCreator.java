package com.framework.driverconfiguration;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@NoArgsConstructor(access = PRIVATE)
public class DriverCreator {

    private static ThreadLocal<DriverManager> driverManager = new ThreadLocal<>();

    public static void setDriver(String browserName) {
        driverManager.set(getManager(Browser.getBrowserByName(browserName)));
        log.debug("Creating local driver for browser = {}...", browserName);
        driverManager.get().getDriver();
    }

    public static WebDriver getDriver() {
        return driverManager.get().getDriver();
    }

    public static synchronized void quitDriver() {
        if (Objects.isNull(driverManager.get())) {
            log.debug("Driver was not initialized no need to quit");
            return;
        }
        driverManager.get().quitDriver();
        clearDriverManager();
    }

    private static synchronized DriverManager getManager(Browser type) {
        if (Objects.isNull(driverManager.get())) {
            switch (type) {
                case FIREFOX:
                    driverManager.set(new FirefoxDriverImplementation());
                    return driverManager.get();
                case CHROME:
                    driverManager.set(new ChromeDriverImplementation());
                    break;
                default:
                    throw new IllegalArgumentException(String.format("No implementation found for [%s] type of driver", type));
            }
        }
        return driverManager.get();
    }

    private static synchronized void clearDriverManager() {
        if (Objects.nonNull(driverManager.get())) {
            driverManager.remove();
        }
    }
}
