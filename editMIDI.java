package GakufunoHeya;

import java.io.*;

import javax.sound.midi.*;
import javax.sound.midi.spi.*;

public class editMIDI {
	public File saveMIDI;
	public static final String saveMIDIName = "result.mid";

	public editMIDI() {
		checkMIDI();
	}

	void checkMIDI() {
		try {
			saveMIDI = new File(saveMIDIName);
			if (isReadable(saveMIDI)) {
				// no operation
			} else {
				saveMIDI.createNewFile();
				
				Sequence sequence = new Sequence(Sequence.PPQ,24);
				
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	boolean isReadable(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canRead()) {
				return true;
			}
		}
		return false;
	}
}
