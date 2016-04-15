package CaptainMarkov;

import CaptainMarkov.utils.Macros;
import CaptainMarkov.utils.MarkovChain;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
	public static final boolean deepLogging = false;

	//TODO: Convert to GUI (long term goal) (And fix this shitty menu while you're at it)
	public static void main(String[] args) {
		HashMap<String, HashMap<String, MarkovChain>> chains = Macros.createMarkovChains();
		HashMap<String, MarkovChain> seriesChain;
		String series = "";
		String choice;
		int n;
		MarkovChain curr;
		while (!series.equals("exit")) {
			System.out.println("Enter Series Name");
			System.out.println("TOS, TNG, DS9, VOY, ENT, ALL");
			series = readString().toUpperCase();
			seriesChain = chains.get(series);
			System.out.println("Enter type of line to generate");
			System.out.println("Log, Command, or character name");
			choice = readString();
			curr = seriesChain.get(choice);
			System.out.println("How many do you want to generate at a time");
			n = readInt();
			do {
				String[] logs = curr.generateSentences(n);
				for(String s : logs)
					System.out.println(s);
				System.out.println("Type back to go back");
			} while (!readString().equalsIgnoreCase("back"));
			System.out.println("Type exit to exit, or enter to continue");
			series = readString();
		}
	}

	/**
	 * Read a string from the user
	 *
	 * @return String
	 */
	private static String readString() {
		Scanner kb = new Scanner(System.in);
		while (!kb.hasNextLine()) {
			kb.next();
		}
		return kb.nextLine();
	}

	/**
	 * Read a positive int from the user
	 *
	 * @return int
	 */
	private static int readInt() {
		Scanner kb = new Scanner(System.in);
		int out = 0;
		while (out <= 0) {
			while (!kb.hasNextInt())
				kb.next();
			out = kb.nextInt();
			if (out <= 0) System.out.println("Must be positive");
		}
		return out;
	}
}
