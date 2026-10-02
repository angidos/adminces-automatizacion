package com.tatf.core.driver.manager;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriver extends DriverManager {

    /**
     * Crea el driver de Chrome con las opciones por defecto.
     */
    public ChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-features=PasswordLeakDetection");

        Map<String, Object> preferences = new HashMap<>();
        preferences.put("credentials_enable_service", false);
        preferences.put("profile.password_manager_enabled", false);
        preferences.put("profile.password_manager_leak_detection", false);

        chromeOptions.setExperimentalOption("prefs", preferences);

        this.driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
        setDefaultConfig();
    }
}
