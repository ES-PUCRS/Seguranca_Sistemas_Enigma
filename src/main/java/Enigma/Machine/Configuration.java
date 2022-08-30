package Enigma.Machine;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import Enigma.Enums.Reflector;
import Enigma.Enums.Action;

@Getter
@Setter
@NoArgsConstructor
public class Configuration {

	private Reflector reflector;
	private Action action;
	
	private int[]  rotors;
	private char[] positions;
	private char[] rings;
	private String plugboard;

	private String message;
	private String knownMessage;
	private String ciphertext;
	private double acceptance;

	public Configuration(Action action, Reflector reflector, int[] rotors, char[] positions, char[] rings, String plugboard, String ciphertext) {
		this.reflector = reflector;
		this.action = action;
		
		this.ciphertext = ciphertext;
		this.plugboard = plugboard;
		this.positions = positions;
		this.rotors = rotors;
		this.rings = rings;
	}

	public void calculateAcceptance() {
		if(knownMessage == null) return;
		// TODO
	}

	@Override
	public String toString() {
		return
			"Action: " 	 + action 		+ "\n" +
			"Reflector: "+ reflector 	+ "\n" +
			"Rotors: \t" + rotors[0] 	+ "\t" + rotors[1] 		+ "\t" + rotors[2] 		+ "\n" +
			"Positn: \t" + positions[0] + "\t" + positions[1] 	+ "\t" + positions[2] 	+ "\n" +
			"Rings : \t" + rings[0] 	+ "\t" + rings[1] 		+ "\t" + rings[2] 		+ "\n" +
			"Message: "  + message 		+ " | Ciphertext: " 	+ ciphertext + "\n---\n";
	}

}