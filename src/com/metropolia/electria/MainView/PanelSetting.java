package com.metropolia.electria.MainView;

/**Setting button panel class
 * Input : new updated value
 * Output: do  port selection, beacons setting, Mobiletag setting
 * Note  : For changing the inner hard coded datas
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.metropolia.electria.marimills.PortUI;
import com.metropolia.electria.ButtonClickClass.Beaconinfo;
import com.metropolia.electria.ButtonClickClass.Informationdatainfo;

public class PanelSetting extends JPanel {
	/* Initializing and Declaring the variables,  Images for the Buttons */
	final public ImageIcon Port_Setting = new ImageIcon(getClass().getResource("/port.png"));
	final public ImageIcon Beacon_Setting = new ImageIcon(getClass().getResource("/beacon.png"));
	final public ImageIcon Mobiletag_Setting = new ImageIcon(getClass().getResource("/mobiletag.png"));
	
	/* Buttons in the setting panels */
	protected JButton Pbutton = new JButton(Port_Setting);
	protected JButton Bbutton = new JButton(Beacon_Setting);
	protected JButton Mbutton = new JButton(Mobiletag_Setting);
	Color bg = new Color(250, 206, 142);
	

	/* Constructor */
	public PanelSetting() {
		/* Adding the buttons to the Panel*/
		add(Mbutton);
		add(Bbutton);
		add(Pbutton);
		
		/*Decorating the buttons*/
		Mbutton.setBorder(null);
		Mbutton.setBorderPainted(false);
		Mbutton.setContentAreaFilled(false);
		Mbutton.setPreferredSize(new Dimension(263, 160));

		Bbutton.setBorder(null);
		Bbutton.setBorderPainted(false);
		Bbutton.setContentAreaFilled(false);
		Bbutton.setPreferredSize(new Dimension(263, 160));

		Pbutton.setBorder(null);
		Pbutton.setBorderPainted(false);
		Pbutton.setContentAreaFilled(false);
		Pbutton.setPreferredSize(new Dimension(263, 160));
		
		
		/* Listeners to the buttons*/
		Pbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EventQueue.invokeLater(new Runnable() {
				    	public void run() {
				    		new PortUI();
				    	}
				    });
				} catch (Exception ex) {
				}
			}
		});
		Bbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Beaconinfo();
				}catch (Exception ex) {
				}
			}
		});
		Mbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Informationdatainfo();
				} catch (Exception ex) {
				}
			}
		});
				
		setPreferredSize(new Dimension(891, 230)); // Width has changed from 1184 to 293 due to removing the exit button
		setBackground(bg);
		setVisible(true);
		
	}
		/*Testing using Main class */
		/*public static void main(String[] args){
			JFrame hello = new JFrame();
			hello.add(new PanelSetting());
			System.out.println("Show sth...");
			hello.setVisible(true);
			hello.setSize(1184, 230);
		}*/
}