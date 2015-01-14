package GakufunoHeya;

import javax.swing.*;
import GakufunoHeya.*;

public class screen{
	JFrame mainframe;
	public static void main(String args[]){
		new screen();
	}
	public screen(){
		mainframe = new JFrame();
		mainframe.setBounds(100,100,600,800);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
	}
}
