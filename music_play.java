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
	public void start(){
		if( midiFilename == null || seq == null )
			return;
		try {
			seq.open();
			seq.setSequence(MidiSystem.getSequence(
						new File(this.midiFilename)));
			seq.start();
			while(seq.isRunning())milisleep(100);
			seq.stop();
			seq.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void stop(){
		if( midiFilename == null || seq == null )
			return;
		try {
			seq.stop();
			seq.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void milisleep(int miliseconds){
		try {
			Thread.currentThread().sleep(100);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
