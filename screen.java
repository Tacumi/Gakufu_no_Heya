package GakufunoHeya;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GakufunoHeya.*;

public class screen {
	music_play mplay;
	JFrame mainframe;
	JButton musicPlayButton;
	JButton statusButton;
	static final int winWidth=600,winHeight=800;
	public static void main(String args[]){
		new screen();
	}
	public screen(){
		mplay = new music_play("./status.mid");
		mainframe = new JFrame();
		mainframe.setLayout(null);
		mainframe.setBounds(100,100,winWidth,winHeight);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		statusButton = new JButton();
		statusButton.setBounds(winWidth/4*2,winHeight-winHeight/5,
								  winWidth/4,winHeight/5);
		statusButton.setText("Status");
		mainframe.add(statusButton);

		musicPlayButton = new musicButton(mplay);
		musicPlayButton.setBounds(winWidth/4*3,winHeight-winHeight/5,
								  winWidth/4,winHeight/5);
		musicPlayButton.setText("Play");
		mainframe.add(musicPlayButton);

		mainframe.setVisible(true);
	}
}

class statusDialog extends JPanel implements MouseListener
{
	private final int defaultWidth=400,defaultHeight=300;
	// even index ... Item Name
	// odd  index ... Item Value
	JLabel[] statusItem;
	JProgressBar expBar;
	public statusDialog(Status stat){
		statusItem = new JLabel[8];
		for(int i = 0; i < statusItem.length; i++){
			statusItem[i] = new JLabel();
			statusItem[i].setBounds(30+(150*(i%2)),70+(40*(i/2)),30,70);
			statusItem[i].add(this);
		}
		statusItem[0].setText("Your Level ");
		statusItem[1].setText(stat.getLevel()+"");
		statusItem[2].setText("Your Money ");
		statusItem[3].setText(stat.getMoney()+"");
		statusItem[4].setText("Your Exp ");
		statusItem[5].setText(stat.getExp()+"");
		statusItem[6].setText("Your Full");
		statusItem[7].setText(stat.getFull()+"");
		expBar = new JProgressBar(0,stat.getExpLimit());
		expBar.setValue(stat.getExp());
	}
	public void mouseClicked(MouseEvent me){
		this.setVisible(false);
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
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
