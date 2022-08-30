package Enigma.Utils;

import java.util.LinkedList;
import java.util.Random;
import java.util.List;

import Enigma.Machine.Configuration;
import Enigma.Machine.Marshal;
import Enigma.Enums.Reflector;
import Enigma.Enums.Action;

public class Generator {

	private static Generator instance;
	private static Random rnd;

	private Generator() {
        rnd = new Random();
	}

	public static void marshal() {
		if(instance == null) instance = new Generator();
        LinkedList<Configuration> list = new LinkedList<Configuration>();

        for (int i = 0; i < Variables.GENERATE; i ++) {
            list.add(
                new Configuration(
                    Action.DECRYPT,
                    Reflector.UKWB,
                    randomRotor(),     // Rotors,
                    randomPositions(), // Positions,
                    randomizeRings(),  // Rings,
                    randomPlugboard(), // Plugboard
                    "HGNCAZM"          // Ciphertext
                )
            );
        }

        FileManager.write(new Marshal(list), true);
    }
    

    private static int[] randomRotor() {
        int[] rotors = new int[3];

        do {
            rotors[0] = 1 + rnd.nextInt(3);
            rotors[1] = 1 + rnd.nextInt(3);
            rotors[2] = 1 + rnd.nextInt(3);
        } while(rotors[0] == rotors[1] || rotors[0] == rotors[2] || rotors[1] == rotors[2]);

        return rotors;
    }

    private static char[] randomPositions() {
    	return new char[] { generateLetter(), generateLetter(), generateLetter() };
    }

    private static char[] randomizeRings() {
    	char[] letters = new char[] {'C', 'E', 'A'};
    	char[] rings = new char[3];


    	for(int i = 0, p = 0; i < 3;) {
    		p = rnd.nextInt(3);
    		if(rings[p] < 65) {
	    		rings[p]=letters[i];
	    		i++;
    		}
    	}

        return rings;
    }

    private static String randomPlugboard() {
    	String plugboard = "" + generateLetter();
    	String tmp = "" + generateLetter();

    	while ( plugboard.equals(tmp) ) {
    		tmp = "" + generateLetter();
    	}

    	return plugboard + tmp;
    }

    //A(ASCII 65) to Z(ASCII 90)
    private static char generateLetter() {
    	return (char) (65 + rnd.nextInt(26));
    }

}