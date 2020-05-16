package com.framework.driverconfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.currentThread;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class DriverManager {

    static Map<Long, WebDriver> driverMap = new ConcurrentHashMap<>();
    static ThreadLocal<DriverService> driverService = new ThreadLocal<>();

    protected abstract void startService();

    private void stopService() {
        if (nonNull(driverService.get()) && driverService.get().isRunning())
            driverService.get().stop();
    }

    protected abstract WebDriver createDriver();

    void addDriverToMap(WebDriver webDriverInstance) {
        driverMap.put(getCurrentThreadId(), webDriverInstance);
    }

    WebDriver getDriverFromMap() {
        return driverMap.get(getCurrentThreadId());
    }

    public WebDriver getDriver() {
        if (isNull(driverMap.get(getCurrentThreadId()))) {
            startService();
            createDriver();
        }
        return driverMap.get(getCurrentThreadId());

    }

    void quitDriver() {
        if (nonNull(driverMap.get(getCurrentThreadId()))) {
            driverMap.get(getCurrentThreadId()).quit();
            try {
                stopService();
            } finally {
                driverMap.remove(getCurrentThreadId());
            }
        }
    }

    long getCurrentThreadId() {
        return currentThread().getId();
    }
}