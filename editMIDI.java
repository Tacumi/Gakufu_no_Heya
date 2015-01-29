package GakufunoHeya;

import java.io.*;

import javax.sound.midi.*;

public class editMIDI {
	public File saveMIDI;
	public static final String saveMIDIName = "status.mid";

	public editMIDI() {
		checkMIDI();
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
			for (int i = 0; i < addTrack.length; i++) {
				int tsize = addTrack[i].size();
				tickplus = mainTrack[i].ticks();
				for (int j = 0; j < tsize; j++) {
					mie = addTrack[i].get(j);
					mie.setTick(mie.getTick() + tickplus);
					mainTrack[i].add(mie);
				}
			}
			MidiSystem.write(mainSequence, 0, saveMIDI);
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
				message.setMessage(ShortMessage.NOTE_ON, 0, pitch, 100);
				mainTrack[0].add(new MidiEvent(message, tickcount + tickplus));

				message = new ShortMessage();
				message.setMessage(ShortMessage.NOTE_OFF, 0, pitch, 100);
				tickcount += pitchlength;
				if(tickcount <= 192){
					mainTrack[0].add(new MidiEvent(message,tickcount + tickplus));
				}else{
					mainTrack[0].add(new MidiEvent(message,192 + tickplus));
					tickcheck = false;
				}
			}

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
			saveMIDI.createNewFile();

			Sequence sequence = new Sequence(Sequence.PPQ, 24);
			Track track = sequence.createTrack();

			int channel = 0;
			int pitch = 48;
			int velocity = 100;
			int instrument = 6;

			MetaMessage mmessage = new MetaMessage();
			int tempo = 105;
			int l = 60 * 1000000 / tempo;
			mmessage.setMessage(0x51, new byte[] { (byte) (l / 65536),
					(byte) (l % 65536 / 256), (byte) (l % 256) }, 3);
			track.add(new MidiEvent(mmessage, 0));

			ShortMessage message = new ShortMessage();
			message.setMessage(ShortMessage.PROGRAM_CHANGE, channel,
					instrument, 0);
			track.add(new MidiEvent(message, 0));

			MidiSystem.write(sequence, 0, saveMIDI);
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
