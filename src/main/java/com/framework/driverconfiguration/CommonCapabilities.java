package com.framework.driverconfiguration;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
class CommonCapabilities {

    private static final String HEADLESS = "headless";

    static Capabilities getDefaultChromeLocalCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setHeadless(BooleanUtils.toBoolean(System.getProperty(HEADLESS, Boolean.FALSE.toString())));
        return options;
    }

    static Capabilities getDefaultFireFoxLocalCapabilities() {
        FirefoxOptions options = new FirefoxOptions().setLogLevel(FirefoxDriverLogLevel.FATAL)
                .setHeadless(BooleanUtils.toBoolean(System.getProperty(HEADLESS, Boolean.FALSE.toString())));
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return options;
    }
}
