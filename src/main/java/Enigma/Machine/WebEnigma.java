package Enigma.Machine;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;

import Enigma.Utils.Variables;
import Enigma.Enums.Reflector;
import Enigma.Enums.Action;

public class WebEnigma {

	private static WebElement message;
	private static WebElement ciphertext;

	private static WebElement plugboard;

	private static WebElement encrypt;
	private static WebElement decrypt;
	
	private static WebElement rotor1;
	private static WebElement rotor2;
	private static WebElement rotor3;

	private static WebElement position1;
	private static WebElement position2;
	private static WebElement position3;

	private static WebElement ring1;
	private static WebElement ring2;
	private static WebElement ring3;

	private static WebElement reflector;

	private static Action action;

	public WebEnigma(WebDriver driver) {
		getElements(driver);
	}

	private void getElements(WebDriver driver) {
		message = driver.findElement(By.id("plaintext"));
		ciphertext = driver.findElement(By.id("ciphertext"));

		plugboard = driver.findElement(By.id("plugboard"));

		encrypt = driver.findElement(By.id("encrypt"));
		decrypt = driver.findElement(By.id("decrypt"));

		rotor1 = driver.findElement(By.id("rotor1"));
		rotor2 = driver.findElement(By.id("rotor2"));
		rotor3 = driver.findElement(By.id("rotor3"));

		position1 = driver.findElement(By.id("position1"));
		position2 = driver.findElement(By.id("position2"));
		position3 = driver.findElement(By.id("position3"));

		ring1 = driver.findElement(By.id("ring1"));
		ring2 = driver.findElement(By.id("ring2"));
		ring3 = driver.findElement(By.id("ring3"));

		reflector = driver.findElement(By.id("reflector"));
	}

	public void run() {
		if(this.action == Action.ENCRYPT)
			encrypt();
		else
			decrypt();
	}

	public void config(Configuration configuration) {
		this.action = configuration.getAction();
		message(configuration.getMessage());
		ciphertext(configuration.getCiphertext());
		reflector(configuration.getReflector());
		
		if(!configuration.getPlugboard().isEmpty())
			plugboard(configuration.getPlugboard());
	
		rotor(1, configuration.getRotors()[0]);
		rotor(2, configuration.getRotors()[1]);
		rotor(3, configuration.getRotors()[2]);

		position(1, configuration.getPositions()[0]);
		position(2, configuration.getPositions()[1]);
		position(3, configuration.getPositions()[2]);

		ring(1, configuration.getRings()[0]);
		ring(2, configuration.getRings()[1]);
		ring(3, configuration.getRings()[2]);
	}

	public void encrypt() { encrypt.sendKeys(Keys.ENTER); timeout(); }
	public void decrypt() { decrypt.sendKeys(Keys.ENTER); timeout(); }

	public String getMessage() { return message.getAttribute("value"); }
	public String getCiphertext() { return ciphertext.getAttribute("value"); }

	public void plugboard(String pairs) {
		timeout();
		if(!plugboard.getAttribute("value").isEmpty())
			plugboard.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		plugboard.sendKeys(pairs);
	}

	public void message(String plaintext) {
		if(!isCiphertextEmpty())
			ciphertext.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		message.sendKeys(Keys.chord(Keys.CONTROL, "a"), plaintext);
	}

	public boolean isCiphertextEmpty() {
		return getCiphertext().isEmpty();
	}

	public void ciphertext(String cyphermessage) {
		if(!isCiphertextEmpty())
			ciphertext.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		ciphertext.sendKeys(cyphermessage);
	}

	public void reflector(Reflector ref) {
		reflector.sendKeys(ref.getKey());
	}

	public void rotor(int rotor, int pos) {
		if(pos < 1 || pos > 3) pos = 99;

		switch (rotor) {
			case 1: setRotor(rotor1, pos); break;
			case 2: setRotor(rotor2, pos); break;
			case 3: setRotor(rotor3, pos); break;
			default:
				throw new IllegalArgumentException
				("Rotor configuration \"" + pos + "\" is not valid." +
				 " Only enabled configuration is I, II and III.");
		}
	}
	private void setRotor(WebElement element, int pos) {
		element.sendKeys(Keys.HOME);
		switch (pos) {
			case 1: break;
			case 2: element.sendKeys("I"); break;
			case 3: element.sendKeys("II"); break;
		}
	}

	public void position(int position, char pos) {
		if(!validation(pos)) position = 99;

		String selection = "" + pos;
		switch (position) {
			case 1: position1.sendKeys(selection); break;
			case 2: position2.sendKeys(selection); break;
			case 3: position3.sendKeys(selection); break;
			default:
				throw new IllegalArgumentException
				("Position configuration \"" + pos + "\" is not valid." +
				 " Only enabled configuration is from A to Z char.");
		}
	}

	public void ring(int ring, char pos) {
		if(!validation(pos)) ring = 99;

		String selection = "" + pos;
		switch (ring) {
			case 1: ring1.sendKeys("" + selection); break;
			case 2: ring2.sendKeys("" + selection); break;
			case 3: ring3.sendKeys("" + selection); break;
			default:
				throw new IllegalArgumentException
				("Ring configuration \"" + pos + "\" is not valid." +
				 " Only enabled configuration is from A to Z char.");
		}
	}


	/*
	 * 	Validate if the input @validate is
	 * between A(ASCII 65) to Z(ASCII 90) and
	 * between a(ASCII 97) to z(ASCII 122)
	 */
	private boolean validation(char validate) {
		return
			(((int) validate) >= 65 && ((int)validate) <= 90) ||
			(((int) validate) >= 97 && ((int)validate) <= 122);
	}

	/*
	 * 	Validate if the input @validate is I, II or III
	 */
	private boolean validation(String validate) {
		return validate.equals("I") || validate.equals("II") || validate.equals("III");
	}


	private void timeout() {
		try {
			Thread.sleep(Variables.THREADTIMEOUT);
		} catch (Exception e) { e.printStackTrace(); }
	}

}