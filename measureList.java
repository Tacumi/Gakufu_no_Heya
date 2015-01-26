package GakufunoHeya;

public class measureList {

	final byte amountPiano = 2;
	final byte amountBase = 2;
	final byte amountDrum = 2;
	final static short bpm = 100;

	public static String pianoList(String str) {
		switch (str) {
		case "0":
			return "./midi/piano0.mid";
		case "1":
			return "./midi/piano1.mid";
		default:
			System.err.println("Can't read this measure name!!");
			System.err.println("imput piano name : " + str);
			return "";
		}
	}

	public static String baseList(String str) {
		switch (str) {
		case "0":
			return "./midi/base0.mid";
		case "1":
			return "./midi/base1.mid";
		default:
			System.err.println("Can't read this measure name!!");
			System.err.println("imput base name : " + str);
			return "";
		}
	}

	public static String drumList(String str) {
		switch (str) {
		case "0":
			return "./midi/drum0.mid";
		case "1":
			return "./midi/drum1.mid";
		default:
			System.err.println("Can't read this measure name!!");
			System.err.println("imput drum name : " + str);
			return "";
		}
	}
}