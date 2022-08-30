package Enigma;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;
import java.lang.StringBuilder;
import java.util.LinkedList;
import java.util.Random;
import java.util.List;

import Enigma.WebDriverConfiguration.FireFoxWebDriver;
import Enigma.Machine.Configuration;
import Enigma.Utils.FileManager;
import Enigma.Machine.WebEnigma;
import Enigma.Machine.Marshal;
import Enigma.Utils.Generator;
import Enigma.Utils.Variables;

public class App {


    public static void main(String[] args) {
        if(Variables.GENERATOR) Generator.marshal();

        WebDriver driver = configurateWebDriver();
        WebEnigma machine = new WebEnigma(driver);

        run(driver, machine);
    }


    private static WebDriver configurateWebDriver() {
        WebDriver driver = FireFoxWebDriver.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.cachesleuth.com/enigma.html");
        return driver;
    }


    private static void run(WebDriver driver, WebEnigma machine) {
		StringBuilder builder = new StringBuilder();
    	Marshal marshal = FileManager.read();

		builder.append("Found: \n\t");		    	
    	for (Configuration configuration : marshal.getConfigurations()) {
			machine.config(configuration);
			machine.run();

			configuration.setMessage(machine.getMessage());
			configuration.calculateAcceptance();
			builder.append(configuration.getMessage());
			builder.append(" ");
    	}
   	
    	FileManager.write(marshal, false);
    	FileManager.write(builder);

        FileManager.clear();
		System.out.println(builder.toString());
	}

}