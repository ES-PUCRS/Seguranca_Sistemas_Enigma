package Enigma.Enums;

import org.openqa.selenium.Keys;

public enum Reflector {
	UKWB(Keys.UP), UKWC(Keys.DOWN);
	
	public Keys key;
	Reflector(Keys key) {
		this.key = key;
	}
	
	public Keys getKey() {
		return key;
	}
}