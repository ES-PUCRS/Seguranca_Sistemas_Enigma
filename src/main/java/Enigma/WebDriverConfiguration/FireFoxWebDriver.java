package Enigma.WebDriverConfiguration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;

import Enigma.Utils.Variables;

public class FireFoxWebDriver {

	/*
	 * Singleton instance
	 */
	private static FireFoxWebDriver instance;
	private static WebDriver driver;

	private FireFoxWebDriver() {
		System.setProperty("webdriver.gecko.driver", Variables.FIREFOXDRIVER);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new FirefoxDriver(capabilities);
	}

	public static WebDriver getDriver() {
		if (instance == null)
			instance = new FireFoxWebDriver();

		return driver;
	}

}