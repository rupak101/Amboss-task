package com.framework.driverconfiguration;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

import static io.github.bonigarcia.wdm.WebDriverManager.getInstance;
import static java.util.Objects.isNull;

@Slf4j
public class ChromeDriverImplementation extends DriverManager {

    @Override
    protected final void startService() {
        if (isNull(driverService.get())) {
            WebDriverManager manager = getInstance(DriverManagerType.CHROME);
            manager.setup();
            driverService.set(new ChromeDriverService.Builder().usingDriverExecutable(new File(manager.getBinaryPath()))
                    .usingAnyFreePort().build());
            try {
                driverService.get().start();
            } catch (IOException e) {
                log.error(e.getMessage(), e.getStackTrace());
            }
        }
    }

    @Override
    protected WebDriver createDriver() {
        ChromeOptions capabilities = (ChromeOptions) CommonCapabilities.getDefaultChromeLocalCapabilities();
        addDriverToMap(new ChromeDriver((ChromeDriverService) driverService.get(), capabilities));
        getDriverFromMap().manage().window().maximize();
        return getDriverFromMap();
    }

}