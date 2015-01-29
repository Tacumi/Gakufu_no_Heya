package GakufunoHeya;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import GakufunoHeya.*;

public class ItemButton extends JButton implements ActionListener{
	private music_play music;
	private Status status;
	editMIDI eM;
	public ItemButton(editMIDI eM, Status status){
		this.eM = eM;
		this.status = status;
		this.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		eM.randomAppend();
		status.setFull(status.getFull() - 30);
	}
}
