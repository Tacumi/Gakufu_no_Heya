package GakufunoHeya;

import javax.swing.*;
import java.awt.*;
import GakufunoHeya.*;

public class screen{
	JFrame mainframe;
	public static void main(String args[]){
		new screen();
	}
	public screen(){
		int winWidth=600,winHeight=800;
		JPanel musicPlayButton;
		mainframe = new JFrame();
		mainframe.setLayout(null);
		musicPlayButton = new pushButton();
		mainframe.add(musicPlayButton);
		mainframe.setBounds(100,100,winWidth,winHeight);
		musicPlayButton.setBounds(winWidth/4*3,winHeight-winHeight/5,winWidth/4,winHeight/5);
		musicPlayButton.setBackground(Color.blue);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
	}
}

class pushButton extends JPanel{
	public pushButton(){
		super();
	}
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x,y,width,height);
	}
}
