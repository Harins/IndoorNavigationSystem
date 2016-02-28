package com.metropolia.electria.MainView;

/* Main user interface class */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;


import com.metropolia.electria.marimills.PortUI;
import com.metropolia.electria.Floorview.FirstfloorView;
import com.metropolia.electria.Floorview.SecondfloorView;
import com.metropolia.electria.Floorview.ThirdfloorView;
import com.metropolia.electria.Floorview.GraphicsThread;
import com.metropolia.electria.MainView.PanelSetting;
import com.metropolia.electria.MainView.PanelHistory;
import com.metropolia.electria.MainView.PanelPeople;

import java.io.*;

public class NavigationUI extends JFrame {
	/* ImageIcons for the Buttons background */
	final public ImageIcon people = new ImageIcon(getClass().getResource("/peoples.png"));
	final public ImageIcon history = new ImageIcon(getClass().getResource("/history.png"));
	final public ImageIcon setting = new ImageIcon(getClass().getResource("/setting.png"));
	final public ImageIcon next = new ImageIcon(getClass().getResource("/NEXT.png"));
	final public ImageIcon previous = new ImageIcon(getClass().getResource("/PREVIOUS.png"));
	
	/* JPanels in the main frame */
	public JPanel PanelTop = new JPanel();
	public JPanel PanelNext = new JPanel();
	public JPanel PanelPrevious = new JPanel();
	public JPanel PanelFloor = new JPanel();
	public JPanel PanelButton = new JPanel();


	/* JButton to the main frame */
	protected JButton peopleButton = new JButton(people);
	protected JButton portsettingButton = new JButton(setting);
	protected JButton historyButton = new JButton(history);
	protected JButton nextButton = new JButton(next);
	protected JButton previousButton = new JButton(previous);
	
	/* Buttons for Exitting the panel to mainframe panel*/
	protected JButton EXITPEOPLE = new JButton(people);
	protected JButton EXITHISTORY = new JButton(history);
	protected JButton EXITPORT = new JButton(setting);
	
	protected int Floormapnumber; // for floor changing purpose

	/* Importing other Panels that changes on button click */
	public PanelSetting panelsetting = new PanelSetting();
	public PanelHistory panelhistory = new PanelHistory();
	public PanelPeople panelpeople = new PanelPeople();
	
	
	/* Main Frame Constructor */
	public NavigationUI() {
		super("Electria Oy, Metropolia University of Applied Sciences");
		
		/* Default floor map presented on the GUI*/
		add(SecondfloorView.getInstance());
		SecondfloorView.getInstance();
		Floormapnumber = 2; 
		
		/* Add the Panels to the Main Frame*/
		add(PanelTop, BorderLayout.NORTH);
		add(PanelPrevious, BorderLayout.WEST);
		add(PanelNext, BorderLayout.EAST);
		//add(PanelFloor, BorderLayout.CENTER);
		add(PanelButton, BorderLayout.SOUTH);
		
		/* Add the buttons to the panels*/
		PanelNext.add(nextButton);
		PanelPrevious.add(previousButton);
		PanelButton.add(portsettingButton);
		PanelButton.add(peopleButton);
		PanelButton.add(historyButton);
		
		/* setting the size of the Panels and background color */
		PanelTop.setPreferredSize(new Dimension(1184, 50));
		PanelTop.setBackground(Color.gray);
		PanelPrevious.setPreferredSize(new Dimension(80, 80));
		PanelPrevious.setBackground(Color.gray);
		PanelNext.setPreferredSize(new Dimension(80, 80));
		PanelNext.setBackground(Color.gray);
		PanelFloor.setPreferredSize(new Dimension(1024, 656));
		PanelFloor.setBackground(Color.gray);
		PanelButton.setPreferredSize(new Dimension(1184, 230));
		PanelButton.setBackground(Color.gray);
		
		/* Decorating the Buttons with size, color and background*/
		previousButton.setBackground(Color.gray);
		previousButton.setPreferredSize(new Dimension(80, 700));
		previousButton.setBorder(null);
		previousButton.setBorderPainted(false);
		previousButton.setContentAreaFilled(false);
		
		nextButton.setBackground(Color.gray);
		nextButton.setPreferredSize(new Dimension(80, 700));
		nextButton.setBorder(null);
		nextButton.setBorderPainted(false);
		nextButton.setContentAreaFilled(false);
			
		portsettingButton.setPreferredSize(new Dimension(200, 180));
		portsettingButton.setBorder(null);
		portsettingButton.setBorderPainted(false);
		portsettingButton.setContentAreaFilled(false);
		
		peopleButton.setPreferredSize(new Dimension(200, 180));
		peopleButton.setBorder(null);
		peopleButton.setBorderPainted(false);
		peopleButton.setContentAreaFilled(false);

		historyButton.setPreferredSize(new Dimension(200, 180));
		historyButton.setBorder(null);
		historyButton.setBorderPainted(false);
		historyButton.setContentAreaFilled(false);
		
		EXITPEOPLE.setPreferredSize(new Dimension(200, 230));
		EXITPEOPLE.setBorder(null);
		EXITPEOPLE.setBorderPainted(false);
		EXITPEOPLE.setContentAreaFilled(false);  
		
		EXITHISTORY.setPreferredSize(new Dimension(200, 230));
		EXITHISTORY.setBorder(null);
		EXITHISTORY.setBorderPainted(false);
		EXITHISTORY.setContentAreaFilled(false);  
		
		EXITPORT.setPreferredSize(new Dimension(200, 230));
		EXITPORT.setBorder(null);
		EXITPORT.setBorderPainted(false);
		EXITPORT.setContentAreaFilled(false);  
      


		/* Listener for changing to next floor Button */
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Floormapnumber == 1) {
						remove(FirstfloorView.getInstance());
						FirstfloorView.getInstance().removeAll();
						invalidate();
						add(SecondfloorView.getInstance(), BorderLayout.CENTER);
						SecondfloorView.getInstance().setBackground(Color.gray);
						Floormapnumber = 2;
						validate();
					} else if (Floormapnumber == 2) {
						remove(SecondfloorView.getInstance());
						SecondfloorView.getInstance().removeAll();
						invalidate();
						add(ThirdfloorView.getInstance(), BorderLayout.CENTER);
						ThirdfloorView.getInstance().setBackground(Color.gray);
						Floormapnumber = 3;	
						validate();
					} else{
						Floormapnumber = 3;	
						//System.out.println("Can't change the floor");
					}
				} catch (Exception ee) {
					//System.out.println("Problem occured while changing panels");
				}
			}
		});
		
		/* Listener for changing to previous floor Button*/
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// // changing the floor map ////
				try {
					if (Floormapnumber == 3) {
						remove(ThirdfloorView.getInstance());
						ThirdfloorView.getInstance().removeAll();
						invalidate();
						add(SecondfloorView.getInstance(),BorderLayout.CENTER);
						SecondfloorView.getInstance().setBackground(Color.gray);
						Floormapnumber = 2;
						validate();
					} else if (Floormapnumber == 2) {
						remove(SecondfloorView.getInstance());
						SecondfloorView.getInstance().removeAll();
						invalidate();
						add(FirstfloorView.getInstance(),BorderLayout.CENTER);
						FirstfloorView.getInstance().setBackground(Color.gray);
						Floormapnumber = 1;
						validate();						
					} else{
						Floormapnumber = 1;
						//System.out.println("Can't change the floor");
					}
				} catch (Exception ee) {
					//System.out.println("Problem occured while changing panels");
				}
			}
		});

		/* Listener to Port setting Button */
		portsettingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					remove(PanelButton);
					invalidate();
					add(panelsetting, BorderLayout.SOUTH);
					panelsetting.add(EXITPORT);
					validate();
					panelsetting.updateUI();
				} catch (Exception ex) {
				}
			}
		});
		
		/* Listener to Mobile Tag information of the people */
		peopleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(PanelButton);
				invalidate();
				add(panelpeople, BorderLayout.SOUTH);
				panelpeople.add(EXITPEOPLE);
				validate();
				panelpeople.updateUI();
			}
		});
		
		/* Listener to History button */
		historyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(PanelButton);
				invalidate();
				add(panelhistory, BorderLayout.SOUTH);
				panelhistory.add(EXITHISTORY);
				validate();
				panelhistory.updateUI();
			}
		});
		
	/* Changing Panels to the mainframe button panel */
     	
	  /* Listener to the exit history button */
		EXITPORT.addActionListener(new ActionListener() {  //adding listener for exit button for changing panels
	      public void actionPerformed(ActionEvent e) {
			remove(panelsetting);
			invalidate();
			add(PanelButton, BorderLayout.SOUTH);
			validate();
			PanelButton.updateUI();
	        }
	    });
		
		 /* Listener to the exit people button */
		EXITPEOPLE.addActionListener(new ActionListener() {  //adding listener for exit button for changing panels
          public void actionPerformed(ActionEvent e) {
			remove(panelpeople);
			invalidate();
			add(PanelButton, BorderLayout.SOUTH);
			validate();
			PanelButton.updateUI();
          }
        });
		
		 /* Listener to the exit people button */
		EXITHISTORY.addActionListener(new ActionListener() {  //adding listener for exit button for changing panels
          public void actionPerformed(ActionEvent e) {
			remove(panelhistory);
			invalidate();
			add(PanelButton, BorderLayout.SOUTH);
			validate();
			PanelButton.updateUI();
          }
        });
		
		pack();
		setBackground(Color.gray);
		setSize(1184, 785);
		setVisible(true);
		setResizable(false);
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) throws IOException {
		NavigationUI Harisframe = new NavigationUI();
		Harisframe.setSize(1184, 785);
		Harisframe.pack();
		//Harisframe.setLocationRelativeTo(null);
		Harisframe.setVisible(true);
		Harisframe.setResizable(false); 
		GraphicsThread gt = new GraphicsThread();
		gt.start();
		
		//new PortUI();
		/**
		 *  System will automatically starts with the selected port and default parameters,
		 *  In other case, setting has to be done manually and has to press the connect button.
		 *  In windows OS, the programs works fine since each device has its own port
		 *  In linux OS, port can be changed for same device, if multiple device connected
		 */
		PortUI autostart = new PortUI();
		autostart.getSerialportConnection(null);
	}
}

