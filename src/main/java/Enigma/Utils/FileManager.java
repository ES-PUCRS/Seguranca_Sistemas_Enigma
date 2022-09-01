package Enigma.Utils;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.StringBuilder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import Enigma.Machine.Marshal;

public class FileManager {

	private static FileManager instance;
	private static ObjectMapper objectMapper;
	private static File generatedFile;
	private static File file;

	private FileManager () {
		objectMapper = new ObjectMapper(new YAMLFactory());
		generatedFile = new File(Variables.GENERATEDCONFIGURATION);
		file = new File(Variables.CONFIGURATION);
	}

	public static Marshal read() {
		instance();

		File read = file;
		if(generatedFile.exists())
			read = generatedFile;

		try {
			return objectMapper.readValue(read, Marshal.class);
		} catch (IOException ioe) { ioe.printStackTrace(); }
		return null;
	}

	public static void write(Marshal marshal, Boolean gen) {
		instance();
		File output = new File(Variables.OUTPUT);
		String outputPath = Variables.RESULT;
		if(gen) outputPath = Variables.GENERATEDCONFIGURATION;
		if(output.exists()) output.mkdir();
		try {
			objectMapper.writeValue(new File(outputPath), marshal);
		} catch (IOException ioe) { ioe.printStackTrace(); }
	}

	public static void write(StringBuilder builder) {
		instance();

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(Variables.FOUNDS));
		    writer.write(builder.toString());

		    writer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void clear() {
		
	}

	private static void instance() {
		if(instance == null)
			instance = new FileManager();
	}
}