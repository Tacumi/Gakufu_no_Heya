package GakufunoHeya;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import GakufunoHeya.*;

public class StatusDialog extends JPanel implements MouseListener
{
	private final int defaultWidth=400,defaultHeight=300;
	// even index ... Element Name
	// odd  index ... Element Value
	private JLabel[] statusItem;
	private JProgressBar expBar;
	private JPanel rP;
	private Status stat;

	public StatusDialog(Status stat,JPanel rp){
		super();
		rP=rp;
		this.setBackground(new Color(0xff,0xfa,0xf0));
		this.stat = stat;
		this.addMouseListener(this);
		this.setLayout(null);
		statusItem = new JLabel[8];
		for(int i = 0; i < statusItem.length; i++){
			statusItem[i] = new JLabel();
			statusItem[i].setBounds(30+(150*(i%2)),70+(40*(i/2)),150,40);
			this.add(statusItem[i]);
		}
		expBar = new JProgressBar(0,stat.getExpLimit());
		expBar.setBounds(0,defaultHeight-40,400,40);
		this.add(expBar);
		updateDialog();
		this.setVisible(true);
	}
	public void updateDialog(){
		statusItem[0].setText("Your Level ");
		statusItem[1].setText(stat.getLevel()+"");
		statusItem[2].setText("Your Money ");
		statusItem[3].setText(stat.getMoney()+"");
		statusItem[4].setText("Your Exp ");
		statusItem[5].setText(stat.getExp()+"");
		statusItem[6].setText("Your Full");
		statusItem[7].setText(stat.getFull()+"");
		expBar.setMaximum(stat.getExpLimit());
		expBar.setBounds(0,defaultHeight-40,400,40);
		expBar.setValue(stat.getExp());
		repaint();
	}
	public void mouseClicked(MouseEvent me){
		this.setVisible(false);
		rP.setVisible(true);
		repaint();
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
}
