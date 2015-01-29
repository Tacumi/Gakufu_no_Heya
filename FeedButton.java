package GakufunoHeya;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GakufunoHeya.*;

public class FeedButton extends JButton implements ActionListener{
	private Status status;
	StatusPanel sp;
	public FeedButton(Status status,StatusPanel sp){
		this.status = status;
		this.sp = sp;
		this.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		status.increaseExp(10);
		status.setFull(status.getFull() + 30);
		sp.updateStatus();
		sp.repaint();
	}
}
