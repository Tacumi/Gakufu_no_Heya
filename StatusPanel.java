package GakufunoHeya;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import GakufunoHeya.*;

public class StatusPanel extends JPanel{
	JPanel namePanel,expProgressPanel,levelPanel,moneyPanel;
	JLabel nameLabel,expProgressLabel,levelLabel,moneyLabel;
	JProgressBar expProgressBar;
	Status status;
	public StatusPanel(Status status){
		this.setLayout(null);
		this.status = status;
		namePanel = new JPanel();
		namePanel.setBounds(0,0,400,50);
		namePanel.setBackground(Color.lightGray);
		nameLabel = new JLabel();
		nameLabel.setBounds(5,5,100,40);
		namePanel.add(nameLabel);
		this.add(namePanel);

		expProgressPanel = new JPanel();
		expProgressPanel.setBounds(0,50,400,50);
		expProgressPanel.setBackground(Color.lightGray);
		expProgressLabel = new JLabel();
		expProgressBar = new JProgressBar(0,status.getExpLimit());
		expProgressPanel.add(expProgressBar);
		expProgressPanel.add(expProgressLabel);
		this.add(expProgressPanel);

		levelPanel = new JPanel();
		levelPanel.setBackground(Color.lightGray);
		levelPanel.setBounds(400,0,200,50);
		levelLabel = new JLabel();
		levelLabel.setBounds(0,0,200,50);
		levelPanel.add(levelLabel);
		this.add(levelPanel);

		moneyPanel = new JPanel();
		moneyPanel.setBounds(400,50,200,50);
		moneyPanel.setBackground(Color.lightGray);
		moneyLabel = new JLabel();
		moneyLabel.setBounds(0,0,200,50);
		moneyPanel.add(moneyLabel);
		this.add(moneyPanel);

		this.setVisible(true);
		updateStatus();
	}
	public void updateStatus(){
		nameLabel.setText(status.getName());
		expProgressLabel.setText("Exp :" + status.getExp());
		expProgressBar.setMaximum(status.getExpLimit());
		expProgressBar.setValue(status.getExp());
		levelLabel.setText("Lv :" + status.getLevel());
		moneyLabel.setText("PM :" + status.getMoney());
		repaint();
	}
}
