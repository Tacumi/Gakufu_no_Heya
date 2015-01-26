package GakufunoHeya;

public class measureList {

	final byte amountPiano = 0;
	final byte amountBase = 3;
	final byte amountDrum = 8;
	final static short bpm = 105;

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
		case "2":
			return "./midi/base2.mid";
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
		case "2":
			return "./midi/drum2.mid";
		case "3":
			return "./midi/drum3.mid";
		case "4":
			return "./midi/drum4.mid";
		case "5":
			return "./midi/drum5.mid";
		case "6":
			return "./midi/drum6.mid";
		case "7":
			return "./midi/drum7.mid";
		default:
			System.err.println("Can't read this measure name!!");
			System.err.println("imput drum name : " + str);
			return "";
		}
	}
}