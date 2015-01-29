package GakufunoHeya;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import GakufunoHeya.*;

public class MusicButton extends JButton implements ActionListener {
	private static music_play mplay;

	public MusicButton (music_play mplay){
		super();
		this.mplay = mplay;
		this.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		if(mplay.toggle(this)){
			this.setText("Play");
		}else{
			this.setText("Stop");
		}
	}
}
