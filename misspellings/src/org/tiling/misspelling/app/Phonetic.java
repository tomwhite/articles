package org.tiling.misspelling.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.tiling.misspelling.phonetic.PhoneticEncoder;

public class Phonetic {

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("Usage: Phonetic <PhoneticEncoder classname>");
			System.exit(1);
		}
		PhoneticEncoder phoneticEncoder = (PhoneticEncoder) Class.forName(args[0]).newInstance();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.print("Enter word (return to exit): ");
			String line = in.readLine();

			if (line.length() == 0) {
				break;
			}
			System.out.println(phoneticEncoder.calculateCode(line));
		}
	}
}
