package GakufunoHeya;

import java.io.*;

import javax.sound.midi.*;

public class editMIDI {
	public File saveMIDI;
	public static final String saveMIDIName = "status.mid";

	public editMIDI() {
		checkMIDI();
	}

	public void randomAppend() {
		byte RamdomB = (byte) (Math.random() * measureList.amountBase);
		byte RandomD = (byte) (Math.random() * measureList.amountDrum);

		appendMIDI(measureList.baseList(RamdomB));
		appendMIDI(measureList.drumList(RandomD));
		appendPiano();
	}

	void appendMIDI(String appendFileName) {
		Track[] addTrack, mainTrack;
		Sequence appendSequence, mainSequence;
		MidiEvent mie;
		long tickplus;
		try {
			mainSequence = MidiSystem.getSequence(saveMIDI);
			appendSequence = MidiSystem.getSequence(new File(appendFileName));
			mainTrack = mainSequence.getTracks();
			addTrack = appendSequence.getTracks();
			for (int i = 0; i < addTrack.length && i < mainTrack.length; i++) {
				int tsize = addTrack[i].size();
				tickplus = mainTrack[i].ticks();
				for (int j = 0; j < tsize; j++) {
					mie = addTrack[i].get(j);
					mie.setTick(mie.getTick() + tickplus);
					mainTrack[i].add(mie);
				}
			}
			MidiSystem.write(mainSequence, 1, saveMIDI);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void appendPiano() {
		Track[] mainTrack;
		Sequence mainSequence;
		ShortMessage message = new ShortMessage();
		long tickplus;
		short tickcount = 0;
		boolean tickcheck = true;
		try {
			mainSequence = MidiSystem.getSequence(saveMIDI);
			mainTrack = mainSequence.getTracks();
			tickplus = mainTrack[0].ticks();

			while (tickcheck) {
				int pitchlength = 12 * (int) (Math.random() * 3 + 1);
				int[] pitchList = { 45, 47, 48, 50, 51, 53, 55, 57, 59, 60, 62,
						64, 65, 67 };
				int pitchLot = (int) (Math.random() * 14);
				int pitch = pitchList[pitchLot];

				message = new ShortMessage();
				message.setMessage(ShortMessage.NOTE_ON, 1, pitch, 127);
				mainTrack[1].add(new MidiEvent(message, tickcount + tickplus));

				message = new ShortMessage();
				message.setMessage(ShortMessage.NOTE_OFF, 1, pitch, 127);
				tickcount += pitchlength;
				if (tickcount < 192) {
					mainTrack[1].add(new MidiEvent(message, tickcount
							+ tickplus));
				} else {
					mainTrack[1].add(new MidiEvent(message, 192 + tickplus));
					tickcheck = false;
				}
			}
			MidiSystem.write(mainSequence, 1, saveMIDI);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void checkMIDI() {
		try {
			saveMIDI = new File(saveMIDIName);
			if (isReadable(saveMIDI)) {
				// no operation
			} else {
				createMIDI();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	void createMIDI() {
		try {
			saveMIDI = new File(saveMIDIName);
			saveMIDI.createNewFile();
			Sequence sequence = new Sequence(Sequence.PPQ, 24, 10);

			Track[] track = sequence.getTracks();
			MetaMessage mmessage = new MetaMessage();
			int tempo = 105;
			int l = 60 * 1000000 / tempo;
			mmessage.setMessage(0x51, new byte[] { (byte) (l / 65536),
					(byte) (l % 65536 / 256), (byte) (l % 256) }, 3);
			track[0].add(new MidiEvent(mmessage, 0));

			// setting Melody(Piano) Track
			ShortMessage messageP = new ShortMessage();
			messageP.setMessage(ShortMessage.PROGRAM_CHANGE, 1, 01 - 1, 0);
			track[1].add(new MidiEvent(messageP, 0));

			// setting Base Track
			ShortMessage messageB = new ShortMessage();
			messageB.setMessage(ShortMessage.PROGRAM_CHANGE, 2, 33 - 1, 0);
			track[2].add(new MidiEvent(messageB, 0));

			// setting Drum Track
			ShortMessage messageD = new ShortMessage();
			messageD.setMessage(ShortMessage.PROGRAM_CHANGE, 9, 00, 0);
			track[9].add(new MidiEvent(messageD, 0));

			MidiSystem.write(sequence, 1, saveMIDI);
		} catch (Exception e) {
			e.printStackTrace();
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
