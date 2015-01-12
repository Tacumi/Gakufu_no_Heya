package GakufunoHeya;

import javax.sound.midi.*;
import java.io.*;

public class musicplay{
	public static void main(String[] args) throws Exception {
		MusicGenerator mg = new MusicGenerator();
		Sequencer seq = MidiSystem.getSequencer();
		seq.open();
		seq.setSequence(mg.getSequence());
		seq.start();
		while(seq.isRunning())Thread.currentThread().sleep(100);
		seq.close();
		return;
	}
}

class MusicGenerator{
	Sequence sequence = null;
	Track track = null;
	public MusicGenerator() {
		try{
			sequence = new Sequence(Sequence.PPQ,96);
			track = sequence.createTrack();
			track.add(NoteOn  (  0, 60,120));
			track.add(NoteOff ( 90, 60,0));
			track.add(NoteOn  ( 96, 62,120));
			track.add(NoteOff (188, 62,0));
			track.add(NoteOn  (192, 64,120));
			track.add(NoteOff (282, 64,0));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Sequence getSequence(){
		if(sequence != null)
			return sequence;
		return null;
	}

	public static MidiEvent NoteOn(int time, int noteno, int velocity)
		throws Exception {
			ShortMessage sm = new ShortMessage();
			sm.setMessage(ShortMessage.NOTE_ON, noteno, velocity);
			return new MidiEvent(sm, time);
	}
	public static MidiEvent NoteOff(int time, int noteno, int velocity)
		throws Exception {
		ShortMessage sm = new ShortMessage();
		sm.setMessage(ShortMessage.NOTE_OFF, noteno, velocity);
		return new MidiEvent(sm, time);	
	}
}
