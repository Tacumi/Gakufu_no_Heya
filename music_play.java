package GakufunoHeya;

import javax.sound.midi.*;
import java.io.*;

public class music_play{
	Sequencer seq = null;
	String midiFilename = null;
	public music_play(String midiFilename){
		try{
			this.seq = MidiSystem.getSequencer();
		}catch(Exception e){
			e.printStackTrace();
		}
		this.midiFilename = midiFilename;
		return;
	}
	public void toggle(){
		if(seq.isRunning()){
			this.stop();
			return;
		}
		this.start();
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
