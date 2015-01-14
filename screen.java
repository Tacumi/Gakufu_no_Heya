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
		mainframe = new JFrame();
		mainframe.setBounds(100,100,winWidth,winHeight);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
	}
}
