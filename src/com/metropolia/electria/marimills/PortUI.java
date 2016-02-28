package com.metropolia.electria.marimills;
/**
 * UI for port settings and handling
 * pre: default setting has been configured and port number will be automatically selected.
 * post: connection can be made by pressing only connect button and real time data can be seen 
 * in scrollable textarea.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import gnu.io.*;

public class PortUI extends JFrame{
	/** Initialising the components */
	private JLabel Portnumber = new JLabel("PortNumber");
	private JLabel Baudrate = new JLabel("BaudRate");
	private JLabel Paritybit = new JLabel("ParityBit");
	private JLabel Databit = new JLabel("DataBit");
	private JLabel Stopbit = new JLabel("StopBit");
	private JLabel Haridwarefc = new JLabel("Hardware Flow Control");
	public JComboBox JSerialPort = new JComboBox();
	public JComboBox JBaudRate = new JComboBox();
	public JComboBox JParity = new JComboBox();
	public JComboBox JDataBits = new JComboBox();
	public JComboBox JStopBits = new JComboBox();
	public JComboBox JHardwareFC = new JComboBox();
	public JTextArea ShowReadingdata = new JTextArea();
	private JScrollPane Scrollinputdata = new JScrollPane();
	private JButton Disconnect = new JButton("Disconnect");
	private JButton Connect = new JButton("Connect");
	private JPanel Left = new JPanel();
	private JPanel Right = new JPanel();
	private JPanel Top = new JPanel();
	private JPanel Buttom = new JPanel();
	//End of components declaration
	
	//Communicator object
    PortUI_Setting communicator = null; 
	
	/** Constructor for the PortUI class */
	public PortUI(){
		/** Adding panels to the frame and setting panel size */
		add(Top, BorderLayout.NORTH);
		Top.setPreferredSize(new Dimension(550, 10));
		add(Left, BorderLayout.WEST);
		Left.setPreferredSize(new Dimension(250, 200));
		Left.setBorder(BorderFactory.createTitledBorder(null, "Serial Port Settings", TitledBorder.CENTER, 
				TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 13))); // NOI18N
		add(Right, BorderLayout.EAST);
		Right.setPreferredSize(new Dimension(300, 200));
		add(Buttom, BorderLayout.SOUTH);
		Buttom.setPreferredSize(new Dimension(550, 50));
		
		/** Setting the size of the componenet*/
		Portnumber.setPreferredSize(new Dimension(100, 20));
		JSerialPort.setPreferredSize(new Dimension(120, 20));
		Baudrate.setPreferredSize(new Dimension(100, 20));
		JBaudRate.setPreferredSize(new Dimension(120, 20));
		Paritybit.setPreferredSize(new Dimension(100, 20));
		JParity.setPreferredSize(new Dimension(120, 20));
		Databit.setPreferredSize(new Dimension(100, 20));
		JDataBits.setPreferredSize(new Dimension(120, 20));
		Stopbit.setPreferredSize(new Dimension(100, 20));
		JStopBits.setPreferredSize(new Dimension(120, 20));
		Haridwarefc.setPreferredSize(new Dimension(180, 20));
		JHardwareFC.setPreferredSize(new Dimension(200, 20));
		
		/** Adding components  to the leftPanel*/
		Left.add(Portnumber);
		Left.add(JSerialPort);
        Left.add(Baudrate);
		Left.add(JBaudRate);
        Left.add(Paritybit);  
		Left.add(JParity);
        Left.add(Databit);
		Left.add(JDataBits);
        Left.add(Stopbit);
		Left.add(JStopBits);
        Left.add(Haridwarefc);
		Left.add(JHardwareFC);
		
		
		/** Adding components  to the rightPanel*/
		Right.add(Scrollinputdata);
				
		/** Adding components  to the buttomPanel*/
		Buttom.add(Disconnect);
		Buttom.add(Connect);
		
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new Font("Tahoma", 1, 14)); // NOI18N//setting fonts and defaultclose operation
        
        JSerialPort.setEditable(true); //Automatically selects the port
        JSerialPort.setModel(new DefaultComboBoxModel(new String[] { }));
         
        JBaudRate.setModel(new DefaultComboBoxModel(new String[] { "115200", "57600", "38400", "19200", "9600", "1200", "300" }));

        JParity.setModel(new DefaultComboBoxModel(new String[] { "Null", "Odd", "Even", "Mark", "Space"}));

        JDataBits.setModel(new DefaultComboBoxModel(new String[] { "8 bits", "7 bits", "6 bits", "5 bits"}));

        JStopBits.setModel(new DefaultComboBoxModel(new String[] { "1 bit", "2 bits" }));
        
        JHardwareFC.setModel(new DefaultComboBoxModel(new String[] { "None", "RTS/CTS", "DTR/DSR", "RS485" }));
                
        Scrollinputdata.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scrollinputdata.setAutoscrolls(true);
        
        ShowReadingdata.setColumns(24);
        ShowReadingdata.setRows(11);
        ShowReadingdata.setBorder(BorderFactory.createTitledBorder(null, "Location Data", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP));
        Scrollinputdata.setViewportView(ShowReadingdata); //Adding TextArea inside the Scrollpane
        
        /** Adding listener to the buttons*/
        Disconnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                getSerialportDisconnection(evt);
            }
        });
        Connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                getSerialportConnection(evt);
            }
        });
        createObjects();
        communicator.searchForPorts();
        setSize(550, 260);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
	}
	private void createObjects()
    {
        communicator = new PortUI_Setting(this);
        
    }
	 /**
	  * Bauddrate selection for the Baudrate jcomboBox.
	  * Default selection would be 115200.
	  */
	 public void getSelectedBoudRate(){
	    int Selected_Baud_Rate = JBaudRate.getSelectedIndex();
		switch(Selected_Baud_Rate)
	       {
	           case 0: 
	               communicator.BAUDRATE = 115200;	    
	               break;
	           case 1:
	               communicator.BAUDRATE = 57600;	               
	                break;
	           case 2: 
	               communicator.BAUDRATE = 38400;	               
	                break;
	           case 3:
	               communicator.BAUDRATE = 19200;	               
	                break;
	           case 4:
	               communicator.BAUDRATE = 9600;	               
	                break;
	           case 5:
	               communicator.BAUDRATE = 1200;	               
	                break;
	           case 6:
	               communicator.BAUDRATE = 300;               
	                break;
	           default: 	               
	               break;
	        }     
	    } // End of getSelectedPortBoudRate
	 /**
	  * Paritybit selection for the Parity jcomboBox.
	  * Default selection would be NULL
	  */
	 public void getSelectedParitybit(){
	    int Selected_Paritybit = JParity.getSelectedIndex();
		switch(Selected_Paritybit)
	       {
	           case 0: 
	        	   communicator.SerialPort_PARITY = SerialPort.PARITY_NONE ;   
	               
	               break;
	           case 1:
	        	   communicator.SerialPort_PARITY = SerialPort.PARITY_ODD ;
	               
	                break;
	           case 2: 
	        	   communicator.SerialPort_PARITY = SerialPort.PARITY_EVEN ;
	               
	                break;
	           case 3:
	        	   communicator.SerialPort_PARITY = SerialPort.PARITY_SPACE ;
	        	   
	                break;
	           case 4:
	        	   communicator.SerialPort_PARITY = SerialPort.PARITY_MARK;
	               
	                break;
	           default: 
	               
	               break;

	        }
	        
	    } // End of getSelectedParitybit
	
	 /**
	  * Databit selection for the PDatabit jcomboBox.
	  * Default selection would be 8 bit
	  */
	 public void getSelectedDatabit(){
	     int Selected_Databit = JDataBits.getSelectedIndex();
	     switch(Selected_Databit)
	       		{
	           case 0: 
	        	   communicator.SerialPort_DATABITS = SerialPort.DATABITS_8 ;
	               
	               break;
	           case 1:
	        	   communicator.SerialPort_DATABITS = SerialPort.DATABITS_7 ;
	               
	                break;
	           case 2: 
	        	   communicator.SerialPort_DATABITS = SerialPort.DATABITS_6 ;
	               
	                break;
	           case 3:
	        	   communicator.SerialPort_DATABITS = SerialPort.DATABITS_5 ; 
	               
	                break;
	           default: 
	               
	               break;

	        }
	        
	    } // End of getSelectedDatabit
	 
	 /**
	  * Stopbit selection for the StopBit jcomboBox.
	  * Default selection would be 1 bit
	  */
	 public void getSelectedStopbit(){
	    int Selected_Stopbit = JStopBits.getSelectedIndex();
		switch(Selected_Stopbit)
	       {
	           case 0: 
	        	   communicator.SerialPort_STOPBITS = SerialPort.STOPBITS_1 ;
	               
	               break;
	           case 1:
	        	   communicator.SerialPort_STOPBITS = SerialPort.STOPBITS_2 ;
	               
	                break;
	           default: 
	               
	               break;

	        }
	        
	    } // End of getSelectedStopbit
	 
	 /**
	  * Hardware Flow Control selection for the HardwareFC jcomboBox.
	  * Default selection would be None, i.e .FLOWCONTROL_NONE
	  */
	 public void getSelectedHardwareFC(){
	    int Selected_Hardwarefc = JHardwareFC.getSelectedIndex();
		switch(Selected_Hardwarefc)
	       {
	           case 0: 
	        	   communicator.SerialPort_HardwareFC = SerialPort.FLOWCONTROL_NONE ;  

	               break;
	           case 1:
	        	   communicator.SerialPort_HardwareFC = SerialPort.FLOWCONTROL_RTSCTS_IN ;
	               
	                break;
	           case 3:
	        	   communicator.SerialPort_HardwareFC = SerialPort.FLOWCONTROL_RTSCTS_OUT ;
	               
	                break;
	           case 4:
	        	   communicator.SerialPort_HardwareFC = SerialPort.FLOWCONTROL_NONE;  //maybe sth else
	               
	                break;
	           default: 
	               
	               break;

	        }
	        
	    } // End of getSelectedHardwareFC
	 
	 /**
	  * Function for connecting the serial port.
	  * Connect buttom has to be pressed to the start the connection
	  */
	 public void getSerialportConnection(ActionEvent evt) {
	        communicator.connect();
	        if (communicator.getbConnected() == true)
	        {
	            if (communicator.initIOStream() == true)
	            {
	                communicator.initListener();          
	            }
	        }
	    }
	 
	 /**
	  * Function for dconnecting the serial port.
	  * Disconnect buttom has to be pressed to the stop the connection
	  */
	    private void getSerialportDisconnection(ActionEvent evt) {
	         communicator.disconnect();
	    }
	
	
//	public static void main(String[] args){ 
//	public void mainn(){
//		/**Create and display the form */
//	    EventQueue.invokeLater(new Runnable() {
//	    	public void run() {
//	    		new PortUI();
//		/**
//		 *  System will automatically starts with the selected port and default parameters,
//		 *  In other case, setting has to be done manually and has to press the connect button.
//		 *  In windows OS, the programs works fine since each device has its own port
//		 *  In linux OS, port can be changed for same device, if multiple device connected
//		 */
//		PortUI autostart = new PortUI();
//		autostart.getSerialportConnection(null);
//	    	}
//	    });
//	}
}

