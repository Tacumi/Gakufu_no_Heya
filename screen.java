package GakufunoHeya;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import GakufunoHeya.*;

public class screen extends JPanel implements ActionListener{
	static music_play mplay;
	static JFrame mainframe;
	static StatusPanel statusPanel;
	static JButton musicPlayButton;
	static JButton statusButton;
	static JPanel roomPanel;
	static JButton feedButton;
	static Status status;
	static StatusDialog statusDialog;
	static final int winWidth=600,winHeight=800;
	static JButton itemButton;

	public static void main(String args[]){
		status = new Status();
		status.increaseExp(20);
		new screen();
	}
	public screen(){
		mplay = new music_play("./status.mid");
		mainframe = new JFrame();
		mainframe.setLayout(null);
		mainframe.setBounds(100,100,winWidth,winHeight);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		statusPanel = new StatusPanel(status);
		statusPanel.setBounds(0,0,winWidth,100);
		statusPanel.setBorder(new LineBorder(Color.green,2,true));
		mainframe.add(statusPanel);

		itemButton = new ItemButton(new editMIDI(),status);
		itemButton.setBounds(0,winHeight-winHeight/5,
				winWidth/4,winHeight/5);
		itemButton.setText("Use Item");
		mainframe.add(itemButton);

		statusButton = new JButton();
		statusButton.setBounds(winWidth/4*2,winHeight-winHeight/5,
				winWidth/4,winHeight/5);
		statusButton.setText("Status");
		statusButton.addActionListener(this);
		statusButton.setActionCommand("status");
		mainframe.add(statusButton);
		
		musicPlayButton = new MusicButton(mplay);
		musicPlayButton.setBounds(winWidth/4*3,winHeight-winHeight/5,
				winWidth/4,winHeight/5);
		musicPlayButton.setText("Play");
		mainframe.add(musicPlayButton);

		roomPanel = new RoomPanel();
		roomPanel.setBounds(0,100,winWidth,540);
		mainframe.add(roomPanel);

		statusDialog = new StatusDialog(status,roomPanel);
		statusDialog.setBounds(100,150,400,300);
		mainframe.add(statusDialog);

		feedButton = new FeedButton(status,statusPanel);
		feedButton.setBounds(winWidth/4*1, winHeight-winHeight/5,
				winWidth/4,winHeight/5);
		feedButton.setText("Feed");
		mainframe.add(feedButton);

		mainframe.setVisible(true);
		statusDialog.setVisible(false);
	}
	public void actionPerformed(ActionEvent e){
		if(statusDialog.isVisible()){
			statusDialog.setVisible(false);
			roomPanel.setVisible(true);
		}else{
			statusDialog.setVisible(true);
			roomPanel.setVisible(false);
		}
		statusPanel.updateStatus();
		statusDialog.updateDialog();
		repaint();
	}
}
