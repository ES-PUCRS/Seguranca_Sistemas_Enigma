package Enigma.Utils;

import java.io.File;

public class Variables {

	// Generator
	public static final boolean GENERATOR = true;
	public static final int GENERATE = 5000 /*iteration*/;

	// WebDriver
	public static final String LIBS = rootPath() + "\\libs";
	public static final String FIREFOXDRIVER = LIBS + "\\geckodriver\\geckodriver.exe";

	// Thread command timeout
	public static final int THREADTIMEOUT = 200 /*ms*/;

	// System paths
	public static final String RESOURCES 	 = rootPath() + "/src/main/resources"; 
	public static final String CONFIGURATION = RESOURCES + "\\Configuration.yaml";
	public static final String OUTPUT 		 = RESOURCES + "/output"; 
	public static final String RESULT 		 = OUTPUT + "/result.yaml"; 
	public static final String FOUNDS 		 = OUTPUT + "/founds.txt"; 
	public static final String GENERATEDCONFIGURATION 	= RESOURCES + "\\Configuration_Generated.yaml";
	
	// Project root path
	public static String rootPath() {
		return new File("").getAbsolutePath();
	}
	
}

// ptaepo
// avelino
// -------
// 