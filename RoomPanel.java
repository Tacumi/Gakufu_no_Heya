package GakufunoHeya;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import GakufunoHeya.*;

public class RoomPanel extends JPanel implements Runnable{
	private JLabel gakufu_chan;
	private JLabel backgrounds;
	private int waitCount = 0;
	private int piece = 0;
	private final int allpiece = 2;
	private final int waitLimit = 5; // waitLimit * 10 ms changing part
	private boolean isEnd = false;
	private int move = 20;
	private ImageIcon[] gakufu_chan_walking_anime;
	private ImageIcon[] gakufu_chan_eating_anime;
	private JPanel charaPanel;
	private Thread t;
	public RoomPanel(){
		this.setLayout(new OverlayLayout(this));
		charaPanel = new JPanel ();
		charaPanel.setBounds(0,0,600,540);
		charaPanel.setLayout(null);
		charaPanel.setBackground(new Color(0,0,0,0));
		this.add(charaPanel);
		backgrounds = new JLabel();
		backgrounds.setIcon(new ImageIcon("./img/backgrounds.png"));
		backgrounds.setBounds(0,0,600,540);
		this.add(backgrounds);
		gakufu_chan_walking_anime = new ImageIcon[2];
		gakufu_chan_eating_anime = new ImageIcon[2];
		for(int i = 0; i < allpiece; i++){
			gakufu_chan_walking_anime[i] = new ImageIcon("img/w"+i+".png");
		}
		gakufu_chan = new JLabel();
		gakufu_chan.setBounds(400,400,100,125);
		gakufu_chan.setIcon(gakufu_chan_walking_anime[0]);
		charaPanel.add(gakufu_chan);
		t = new Thread(this);
		t.start();
	}
	public void run(){
		while(!isEnd){
			waitCount++;
			if(waitCount > waitLimit){
				piece = (piece+1) % allpiece;
				gakufu_chan.setIcon(gakufu_chan_walking_anime[piece]);
				waitCount = 0;
			}
			gakufu_chan.setLocation(gakufu_chan.getX()+move,gakufu_chan.getY());
			if(gakufu_chan.getX()>400 || gakufu_chan.getX() < 40){
				move *= -1;
			}
			repaint();
			try{
				Thread.currentThread().sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
