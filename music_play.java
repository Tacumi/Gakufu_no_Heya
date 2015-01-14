package GakufunoHeya;

import javax.sound.midi.*;
import java.io.*;

public class music_play{
	public static void main(String[] args) throws Exception {
		Sequencer seq = MidiSystem.getSequencer();
		seq.open();
		seq.setSequence(MidiSystem.getSequence(new File("./status.mid")));
		seq.start();
		while(seq.isRunning())Thread.currentThread().sleep(100);
		seq.close();
		return;
	}
}
