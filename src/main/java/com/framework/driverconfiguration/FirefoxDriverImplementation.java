package com.framework.driverconfiguration;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;

import static java.util.Objects.isNull;

@Slf4j
public class FirefoxDriverImplementation extends DriverManager {

    @Override
    protected final void startService() {
        if (isNull(driverService.get())) {
            WebDriverManager manager = WebDriverManager.getInstance(DriverManagerType.FIREFOX);
            manager.setup();
            driverService.set(new GeckoDriverService.Builder().usingDriverExecutable(new File(manager.getBinaryPath()))
                    .usingAnyFreePort().build());
            try {
                driverService.get().start();
            } catch (Exception e) {
                log.error(e.getMessage(), e.getStackTrace());
            }
        }
    }

    @Override
    protected WebDriver createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.merge(CommonCapabilities.getDefaultFireFoxLocalCapabilities());
        addDriverToMap(new FirefoxDriver(((GeckoDriverService) driverService.get()), options));
        getDriverFromMap().manage().window().maximize();
        return getDriverFromMap();
    }
}