package GakufunoHeya;

import javax.sound.midi.*;
import java.io.*;

public class music_play{
	Sequencer seq = null;
	String midiFilename = null;
	MusicThread musicThread;
	public music_play(String midiFilename){
		try{
			this.seq = MidiSystem.getSequencer();
		}catch(Exception e){
			e.printStackTrace();
		}
		this.midiFilename = midiFilename;
		musicThread = MusicThread.getMusicThread(seq,midiFilename);
		return;
	}
	public void toggle(){
		if(seq.isRunning()){
			musicThread.setEnd();
			return;
		}
		musicThread = MusicThread.getMusicThread(seq,midiFilename);
		musicThread.start();
	}
}
final class MusicThread extends Thread {
	private static MusicThread instance;
	private static Sequencer seq = null;
	private static String midiFilename = null;
	private static boolean isNotEnd = true;
	private MusicThread(Sequencer seq , String midiFilename){
		this.seq = seq;
		this.midiFilename = midiFilename;
	};
	public static synchronized MusicThread getMusicThread(Sequencer seq, String midiFilename){
		if(instance == null)
			instance = new MusicThread(seq , midiFilename);
		return instance;
	}
	public void setEnd(){
		isNotEnd = false;
		instance = null;
	}
	public void run(){
		isNotEnd = true;
		if( midiFilename == null || seq == null )
			return;
		try {
			seq.open();
			seq.setSequence(MidiSystem.getSequence(
						new File(this.midiFilename)));
			seq.start();
			while(isNotEnd && seq.isRunning())this.milisleep(100);
			if( midiFilename == null || seq == null )
				return;
			seq.stop();
			seq.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	private void milisleep(int miliseconds){
		try {
			Thread.currentThread().sleep(100);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
