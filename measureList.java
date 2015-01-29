package GakufunoHeya;

public class measureList {

	final static byte amountBase = 4;
	final static byte amountDrum = 8;

	public static String baseList(byte i) {
		switch (i) {
		case 0:
			return "./midi/base0.mid";
		case 1:
			return "./midi/base1.mid";
		case 2:
			return "./midi/base2.mid";
		case 3:
			return "./midi/base3.mid";
		default:
			System.err.println("Can't read this measure name!!");
			System.err.println("imput base number : " + i);
			return "";
		}
	}

	public static String drumList(byte i) {
		switch (i) {
		case 0:
			return "./midi/drum0.mid";
		case 1:
			return "./midi/drum1.mid";
		case 2:
			return "./midi/drum2.mid";
		case 3:
			return "./midi/drum3.mid";
		case 4:
			return "./midi/drum4.mid";
		case 5:
			return "./midi/drum5.mid";
		case 6:
			return "./midi/drum6.mid";
		case 7:
			return "./midi/drum7.mid";
		default:
			System.err.println("Can't read this measure name!!");
			System.err.println("imput drum number : " + i);
			return "";
		}
	}
}