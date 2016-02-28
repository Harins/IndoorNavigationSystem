package com.metropolia.electria.marimills;

import gnu.io.*;

import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.metropolia.electria.MainAlgorithm.Coridoor_BEACON_INFO;
//import com.metropolia.electria.MainAlgorithm.SerialPortSending;


public class PortUI_Setting implements SerialPortEventListener {
	public static HashMap<Integer, Coridoor_BEACON_INFO> multiple_mobile = new HashMap<Integer, Coridoor_BEACON_INFO>(); //Data for own GUI
	public static HashMap<Integer, senddata_structure> Datatoserver = new HashMap<Integer, senddata_structure>(); //Data for Marimils Server
	private HashMap<String, CommPortIdentifier> portMap = new HashMap<String, CommPortIdentifier>();
    static int MobileTagID, BeaconId, AlarmNotification, RSSIValue;
	private Enumeration<?> ports = null;
	
   /**this is the object that contains the opened port*/
    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort serialPort = null;
    
   /**inputstream and output streams for sending and receiving data*/
    private InputStream inputstream = null;
    private OutputStream outputstream = null;
    private CommPortIdentifier curPort;
    
	PortUI PortUIFrame = null;	
	
	 /** Default selection  to use. */
    public int BAUDRATE = 115200;
    public int SerialPort_DATABITS = SerialPort.DATABITS_8 ;
    public int SerialPort_STOPBITS = SerialPort.STOPBITS_1 ; 
    public int SerialPort_PARITY = SerialPort.PARITY_NONE ;
    public int SerialPort_HardwareFC = SerialPort.FLOWCONTROL_NONE ;
  
   /** Boolean flag for enabling and disabling buttons based of the serial port connection*/
    private boolean bConnected = false;

  /** A string for result and shows on GUI frame   */
    String FeetbackTest = "";
    
    public PortUI_Setting(PortUI portUIFrame){
        this.PortUIFrame = portUIFrame;
    }
	
	public boolean getbConnected() {
		return bConnected;
	}

	public void setbConnected(boolean bConnected) {
		this.bConnected = bConnected;
	}

	/*
	 * search for all the serial ports and filters the default ports
	 * pre: none
	 * post: adds only the used ports to a combo box on the GUI and removes default ports.
	 */
	public void searchForPorts(){
        ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements())
        {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();

            //get only serial ports
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
            	if(curPort.getName().equals("/dev/ttyS1") ||curPort.getName().equals("/dev/ttyS0")){
					System.out.println("Default serial port did not add in the system");
				}else{
					PortUIFrame.JSerialPort.addItem(curPort.getName());
	                portMap.put(curPort.getName(), curPort);
				}
                
                
            }
        }
    }// End of searchForPorts
	
   /** Open the input and output streams
     * pre: an open port
     * ost: initialized intput and output streams for use to communicate data
     */   
	public boolean initIOStream()
    {
        //return value for whather opening the streams is successful or not
        boolean successful = false;

        try {
            inputstream = serialPort.getInputStream();
            outputstream = serialPort.getOutputStream();
            successful = true;
            return successful;
        }
        catch (IOException e) {
        	FeetbackTest = "I/O Streams failed to open. (" + e.toString() + ")";
        	PortUIFrame.ShowReadingdata.setForeground(Color.red);
        	PortUIFrame.ShowReadingdata.append(FeetbackTest + "\n");
            return successful;
        }
    }

   /** Starts the event listener that knows whenever data is available to be read
     * pre: an open serial port
     * post: an event listener for the serial port that knows when data is recieved
     */
    public void initListener()
    {
        try
        {
//            serialPort.addEventListener((SerialPortEventListener) this);
        	serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (TooManyListenersException e)
        {
            FeetbackTest = "Too many listeners. (" + e.toString() + ")";
            PortUIFrame.ShowReadingdata.setForeground(Color.red);
            PortUIFrame.ShowReadingdata.append(FeetbackTest + "\n");
        }
    }
  /**
    * Connect to the selected port in the combo box.
    * pre: ports are already found by using the searchForPorts method.
    * post: the connected comm port is stored in commPort, otherwise,
    * an exception is generated
    */
	public void connect(){
        String selectedPort = (String)PortUIFrame.JSerialPort.getSelectedItem();
        selectedPortIdentifier = (CommPortIdentifier)portMap.get(selectedPort);

        CommPort commPort = null;
        PortUIFrame.getSelectedBoudRate();
        PortUIFrame.getSelectedParitybit();
        PortUIFrame.getSelectedDatabit();
        PortUIFrame.getSelectedStopbit();
        PortUIFrame.getSelectedHardwareFC();

        try
        {
            //the method below returns an object of type CommPort
            commPort = selectedPortIdentifier.open("ILS", 2000);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort)commPort;
             
            // set up the serial port parameters
            serialPort.setSerialPortParams(BAUDRATE, SerialPort_DATABITS, SerialPort_STOPBITS, SerialPort_PARITY);
            serialPort.setFlowControlMode(SerialPort_HardwareFC); // Harware Flow Control

            //for controlling GUI elements
            setbConnected(true);

            //logging
            FeetbackTest = selectedPort + " opened successfully.";
            PortUIFrame.ShowReadingdata.setForeground(Color.black);
            PortUIFrame.ShowReadingdata.append(FeetbackTest + "\n");

          }
        catch (PortInUseException e)
        {
            FeetbackTest = selectedPort + " is in use. (" + e.toString() + ")";   
            PortUIFrame.ShowReadingdata.setForeground(Color.RED);
            PortUIFrame.ShowReadingdata.append(FeetbackTest + "\n");
        }
        catch (Exception e)
        {
            FeetbackTest = "Failed to open " + selectedPort + "(" + e.toString() + ")";
            PortUIFrame.ShowReadingdata.append(FeetbackTest + "\n");
            PortUIFrame.ShowReadingdata.setForeground(Color.RED);
        }
    }
  /**
    * Disconnects the selected port in the combo box
    * pre: ports are already found by using the searchForPorts method and connects
    * post: close the serial port and allow to connect again.
    */
	 public void disconnect(){
	       /**close the serial port*/
	        try
	        {
	            serialPort.removeEventListener();
	            serialPort.close();
	            inputstream.close();
	            outputstream.close();
	            setbConnected(false);
	            //PortUIFrame.keybindingController.toggleControls();

	            FeetbackTest = "\nDisconnected.";
	            PortUIFrame.ShowReadingdata.setForeground(Color.red);
	            PortUIFrame.ShowReadingdata.append(FeetbackTest + "\n");
	        }
	        catch (Exception e)
	        {
	            FeetbackTest = "Failed to close " + serialPort.getName() + "(" + e.toString() + ")";
	            PortUIFrame.ShowReadingdata.setForeground(Color.red);
	            PortUIFrame.ShowReadingdata.append(FeetbackTest + "\n");
	        }
	    }
	 
	/**
	  * Reading the data from serial port with event based
	  * pre: inputstream data in byte by byte form
	  * post: Receiving, filtering, processing and send data for further processing
	  * for eg; data to server and graphics
	  */
	 public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] readBuffer = new byte[40];
			final int d = 1;
			byte[] PayLoad = { -35,0,2,2,4,1,127,(byte) MobileTagID, 0,0,-65,125 };// UART payload
			byte[] RayLoad = { -35, 0, 2, 2, 4, 1, 16, 16, 0, 0, -65, 125 };

			try {
				while (inputstream.available() > 0) {
					int numBytes = inputstream.read(readBuffer);// received Byte
					int[][] ID_RSSI_INT = new int[8][2]; // length
								
			/**
			 * Removing noise and unwanted data, filtering based on Packet received
			 * note: check the packet structure and array elements 
			 */
					//if(readBuffer == null || readBuffer[6]!=-86 || readBuffer[8]<1 ||readBuffer[9]==0 || readBuffer[10]==0){  
					if(readBuffer == null || readBuffer[6]!=-86 ||readBuffer[9]==0 || readBuffer[10]==0){   
						break;					
					}else{					
						/* Original Data reading form the serial port in dbm byte by byte */
					for (int i = 7; i < numBytes - 7; i++) {
						//System.out.print(String.format("%x ", readBuffer[i]));
    					PortUIFrame.ShowReadingdata.append(Integer.toString(readBuffer[i])+"");
					}
					System.out.println();
					PortUIFrame.ShowReadingdata.append("\n");
										
					/* PUTTING THE DATAS INTO DIMENTIONAL FORM FOR EASY SORTING*/					
					for (int i = 0, k = 9; i <= 4; i++) {
						for (int j = 0; j <= 1; j++) {
							ID_RSSI_INT[i][j] =  (int) readBuffer[k];  //Look multiplication with -1
							 //System.out.print(" " + ID_RSSI_INT[i][j]);
							k++;
						}
						//System.out.println(); //ifyouwantoprintintwodimentionalform
					}//System.out.println();    //ifyouwantoprintintwodimentionalform
					MobileTagID = readBuffer[7]; //System.out.println(MobileTagID);
					AlarmNotification = readBuffer[8];
											
					/**
					 * SORTING ALGORITHM BASED ON RSSI
					 * Sorting tricks: changing position of returns or -1 multiplication to get rid of nevative datas value.
					 * Note: Check the packet structure and array elements
					 */
					Arrays.sort(ID_RSSI_INT, new Comparator<int[]>() {
						@Override
						public int compare(final int[] entry1, final int[] entry2) {

							if (entry1[d] < entry2[d])
								return 0;
							else
								return 1;
						}
					});
						
					BeaconId =ID_RSSI_INT[0][0]; //Select the nearest Beacon based on RSSI value
					RSSIValue = ID_RSSI_INT[0][1]; //System.out.println("BeaconId::"+ BeaconId+ " RSSIValue::"+ RSSIValue);
					System.out.println("MobileTagID:BeaconId:AlarmNotification:: "+""+MobileTagID+","+ BeaconId+","+ AlarmNotification);	
				
		/**
		 * Resetting the MobileTag
		 */
					if(AlarmNotification == 126){
						outputstream.write(PayLoad);
						System.out.println("Alarm Signal has received");
					}else if(AlarmNotification ==15){
						outputstream.write(RayLoad);
						System.out.println("Alarm Red signal has reset");
					}	
		/**
		 *  Batchupdate Data sending to the server in event based. Received datas are filtered, processed and sent to the Server 
		 *  in every time receiving data in the form of JSON Array that contains MobiletagID, BeaconId and RssiValue or AlarmID.
		 */
					//updateJSON_Server(MobileTagID, BeaconId, AlarmNotification);
						
		/**
		 * Mapping multiple mobiletag for corridor Beacon selection 
		 * Create a hash map and Put elements to the map
		 * Checks if mobiletag is already registered, if registered then values are just updated
		 * Otherwise, new mobiletag is created.
		 */
				if (multiple_mobile.containsKey(MobileTagID)) { 
					multiple_mobile.get(MobileTagID).update(MobileTagID, BeaconId, AlarmNotification, RSSIValue);
					System.out.println("ReadingDatafor update: " + "" + MobileTagID+ ":::" + multiple_mobile.get(MobileTagID).getBeaconid());
				} else {
					multiple_mobile.put(MobileTagID, new Coridoor_BEACON_INFO(MobileTagID, BeaconId, AlarmNotification, RSSIValue));
					// System.out.println("ReadingDatafor adding: "+""+MobileTagID+":::"+ multiple_mobile.get(MobileTagID).getBeaconid());
				}
		/**
		 * Resetting the MobileTag
		 */

//			try{
//				if(AlarmNotification == 126){
//					outputstream.write(PayLoad);
//					outputstream.write(PayLoad);
//					System.out.println("Alarm Signal has received");
//					
//				}else if(AlarmNotification ==15){
//					outputstream.write(RayLoad);
//					outputstream.write(RayLoad);
//					System.out.println("Alarm Red signal has reset");
//				}	
//			}catch(Exception e){
//				e.printStackTrace();
//			}
						
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			} catch (Exception e) {
				e.printStackTrace();				 
			}
			break;
		}
	  }
	/* public void updateJSON_Server(int mid, int bid, int alarm){
	        try {
	            
	            JSONObject foo = new JSONObject();
	            foo.put("mid", mid);
	            foo.put("bid", bid);
	            foo.put("alarm", alarm);
	            
	            String request = URLEncoder.encode("json", "UTF-8") + "=" + foo.toString();
	            URL url = new URL("http://indoor-location.herokuapp.com/bup");
				
	            URLConnection connection = url.openConnection();
	            connection.setDoOutput(true);
	            
	            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
	            osw.write(request);
	            osw.flush();
	            
	            StringBuilder response = new StringBuilder();
	            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String line;
	            while ((line = br.readLine()) != null) {
	            	response.append(line);
	            }
	            
	            System.out.println(response);
	            
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}*/
}
