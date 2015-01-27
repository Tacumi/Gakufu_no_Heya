package GakufunoHeya;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GakufunoHeya.*;

public class screen {
	static music_play mplay;
	static JFrame mainframe;
	static JPanel statusPanel;
	static JButton musicPlayButton;
	static JButton statusButton;
	static Status status;
	static final int winWidth=600,winHeight=800;
	public static void main(String args[]){
		status = new Status();
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
		mainframe.add(statusPanel);

		statusButton = new StatusButton(status);
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
class StatusButton extends JButton implements ActionListener{
	private Status status;
	public StatusButton(Status status){
		super();
		this.status = status;
	}
	public void actionPerformed(ActionEvent e){
		new statusDialog(status);
	}
}

class StatusPanel extends JPanel{
	JPanel namePanel,expProgressPanel,levelPanel,moneyPanel;
	JLabel nameLabel,expProgressLabel,levelLabel,moneyLabel;
	JProgressBar expProgressBar;
	public StatusPanel(Status status){
		this.setLayout(null);
		namePanel = new JPanel();
		namePanel.setBounds(0,0,400,50);
		nameLabel = new JLabel();
		nameLabel.setBounds(5,5,100,40);
		namePanel.add(nameLabel);
		this.add(namePanel);

		expProgressPanel = new JPanel();
		expProgressPanel.setBounds(0,50,400,50);
		expProgressLabel = new JLabel();
		expProgressBar = new JProgressBar(0,status.getExpLimit());
		expProgressPanel.add(expProgressBar);
		expProgressPanel.add(expProgressLabel);
		this.add(expProgressPanel);

		levelPanel = new JPanel();
		levelPanel.setBounds(400,0,200,50);
		levelLabel = new JLabel();
		levelLabel.setBounds(0,0,200,50);
		this.add(levelPanel);

		moneyPanel = new JPanel();
		moneyPanel.setBounds(400,50,200,50);
		moneyLabel = new JLabel();
		moneyPanel.setBounds(0,0,200,50);
		this.add(moneyLabel);

		this.setVisible(true);
		updateStatus(status);
	}
	public void updateStatus(Status status){
		nameLabel.setText(status.getName());
		expProgressLabel.setText("Exp :" + status.getExp());
		expProgressBar.setValue(status.getExp());
		levelLabel.setText("Lv :" + status.getLevel());
		moneyLabel.setText("PM :" + status.getMoney());
		repaint();
	}
}
class statusDialog extends JPanel implements MouseListener
{
	private final int defaultWidth=400,defaultHeight=300;
	// even index ... Element Name
	// odd  index ... Element Value
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
		repaint();
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
