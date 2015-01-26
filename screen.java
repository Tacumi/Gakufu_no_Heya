package GakufunoHeya;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GakufunoHeya.*;

public class screen implements MouseListener{
	music_play mplay;
	JFrame mainframe;
	JPanel musicPlayButton;
	JButton musicPlayButton2;
	public static void main(String args[]){
		new screen();
	}
	public screen(){
		int winWidth=600,winHeight=800;
		mplay = new music_play("./status.mid");
		mainframe = new JFrame();
		mainframe.setLayout(null);
		mainframe.setBounds(100,100,winWidth,winHeight);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		musicPlayButton = new pushButton();
		musicPlayButton2 = new musicButton(mplay);
		musicPlayButton.setBounds(winWidth/4*3,winHeight-winHeight/5,
								  winWidth/4,winHeight/5);
		musicPlayButton.setBackground(Color.blue);
		musicPlayButton.addMouseListener(this);
		musicPlayButton2.setText("Play");
		mainframe.add(musicPlayButton);

		mainframe.setVisible(true);
	}
	

	public void mouseClicked(MouseEvent me){
		if(me.getComponent() == musicPlayButton)
			mplay.toggle();
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
}

class pushButton extends JPanel{
	public pushButton(){
		super();
	}
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x,y,width,height);
	}
}
class musicButton extends JButton implements ActionListener {
	private static music_play mplay;

	public musicButton (music_play mplay){
		super();
		this.mplay = mplay;
		this.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		this.setText(this.getText().equals("Play")?"Stop":"Play");
		mplay.toggle();
	}
}
