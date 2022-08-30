package Enigma.Machine;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.StringBuilder;
import java.util.List;

import Enigma.Machine.Configuration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Marshal {

	private List<Configuration> configurations;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Configuration config : configurations) {
			builder.append(config);
		}
		return builder.toString();
	}
}