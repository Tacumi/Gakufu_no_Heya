package GakufunoHeya;

import javax.swing.*;
import javax.sound.midi.*;
import java.io.*;

public class music_play{
	Sequencer seq = null;
	String midiFilename = null;
	MusicThread musicThread;
	public music_play(String midiFilename){
		try{
			this.seq = MidiSystem.getSequencer();
			seq.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.midiFilename = midiFilename;
		return;
	}
	public boolean toggle(JButton musicPlayButton){
		if(seq.isRunning()){
			musicThread.setEnd();
			return true;
		}
		musicThread = MusicThread.getMusicThread(seq,midiFilename,musicPlayButton);
		musicThread.start();
		return false;
	}
}
final class MusicThread extends Thread {
	private static MusicThread instance;
	private static Sequencer seq = null;
	private static String midiFilename = null;
	private static boolean isNotEnd = true;
	private static JButton musicPlayButton;
	private MusicThread(Sequencer seq , String midiFilename, JButton musicPlayButton){
		this.seq = seq;
		this.midiFilename = midiFilename;
		this.musicPlayButton = musicPlayButton;
	};
	public static synchronized MusicThread getMusicThread(Sequencer seq, String midiFilename, JButton musicPlayButton){
		if(instance == null)
			instance = new MusicThread(seq , midiFilename, musicPlayButton);
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
			musicPlayButton.setText("Play");
			setEnd();
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
