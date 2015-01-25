package GakufunoHeya;

public class measureList {

	/**
	public void strToFileName(String str) {
		switch (str.substring(0, 1)) {
		case "p":
			pianoList(str);
			break;
		case "d":
			drumList(str);
			break;
		}
	}**/

	final byte amountPiano = 1;

	public String pianoList(String str) {
		switch (str) {
		case "0":
			return ".\\midi\\piano0.mid";
		}
		return "";
	}

	final byte amountDrum = 1;

	public String drumList(String str) {
		switch(str){
		case "0":
			return ".\\midi\\drum0.mid";
		}
		return "";
	}
}