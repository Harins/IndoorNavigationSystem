package com.metropolia.electria.MainAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import com.metropolia.electria.ButtonClickClass.Beaconimport;
import com.metropolia.electria.ButtonClickClass.Beaconimport_Structure;

/**
 * Main algorithm implementation. Selects all the required dates needed for the GUI processing
 * @Note: all the beacons are assumed as a corridor beacons, no gatebeacons and no entryflag...
 */
public class Coridoor_BEACON_INFO {
	private ArrayList<CORRIDOR_BID> Corridor_Beacon_Parameter = new ArrayList<CORRIDOR_BID>();
	private ArrayList<PSI_ForMobiletagHolder> Mainlyforpaintingshadow = new ArrayList<PSI_ForMobiletagHolder>();
//	private static HashMap<String, Beaconimport_Structure> AllBeaconVariable = new HashMap<String, Beaconimport_Structure>();


	private int MOBILE_TAG_ID, Beaconid, Alarm_Notification, RSSI;
//	private int Alarm_Notification, Alarmison, Alarmontime, Alarmontimediff;
	private int X = 0;
	private int Y = 0;
//	private int LostAlarmontime, startlost, AlreadyLostAlarmontime, lostdiff;
	int i = 0;
	int[] j = new int[3];
	int lost_id_count = 0;
	int lost_id_count_Room = 0;
	int[] lost_id_Buffer_Cori = new int[3];
	int[] lost_id_Buffer_Room = new int[10];
	boolean OriginGot = false;
	public String location = " ";
	int Selected_CorridorID;
	int Selected_RoomID=0;
	int FLoornumber;
	int Previous_BeaconID = 0;
	
	/* Corridor ID of all the Corridor beacons Beacons */
//	static int Corridor3_ID = 14;	static int Corridor4_ID = 04;
//	static int Corridor5_ID = 7;	static int Corridor6_ID = 9;
//	static int Corridor7_ID = 19;	static int Corridor8_ID = 17;	

	
	static int Corridor3_ID;	static int Corridor4_ID;
	static int Corridor5_ID;	static int Corridor6_ID;
	static int Corridor7_ID;	static int Corridor8_ID;
	/**
	  * Position of the All the beacons corridor and rooms (center point)
	  * Real Coordinates of the Beacons Position "No need to change" 
	  */

		int CX3 = 267;		int CY3 = 242;
		int CX4 = 323;		int CY4 = 346;
		int CX5 = 502;		int CY5 = 310;
		int CX6 = 654;		int CY6 = 343;
		int CX7 = 628;		int CY7 = 245;
		int CX8 = 448;		int CY8 = 242;
	
	/*  Images for repainting shadow background based on cordinates */
		
	final public  Image C3 = new ImageIcon(getClass().getResource("/A14567892021.png")).getImage();
	final public  Image C4 = new ImageIcon(getClass().getResource("/C4.png")).getImage();
	final public  Image C5 = new ImageIcon(getClass().getResource("/C5.png")).getImage();
	final public  Image C6 = new ImageIcon(getClass().getResource("/C66.png")).getImage();
	final public  Image C7 = new ImageIcon(getClass().getResource("/A14567892021.png")).getImage();
	final public  Image C8 = new ImageIcon(getClass().getResource("/A14567892021.png")).getImage();
//	private Image C3 = new ImageIcon("//home//electria//AllPicture//Parvoo_Pilot//A14567892021.png").getImage();
//	private Image C4 = new ImageIcon("//home//electria//AllPicture//Parvoo_Pilot//C4.png").getImage();
//	private Image C5 = new ImageIcon("//home//electria//AllPicture//Parvoo_Pilot//C5.png").getImage();
//	private Image C6 = new ImageIcon("//home//electria//AllPicture//Parvoo_Pilot//C66.png").getImage();
//	private Image C7 = new ImageIcon("//home//electria//AllPicture//Parvoo_Pilot//A14567892021.png").getImage();
//	private Image C8 = new ImageIcon("//home//electria//AllPicture//Parvoo_Pilot//A14567892021.png").getImage();
	


	/* Image position of Ordinates for shadow painting of corridor */
	
	int C3_Ipx = 236,		C3_Ipy = 200;
	int C4_Ipx = 268,		C4_Ipy = 293;
	int C5_Ipx = 443,		C5_Ipy = 295;
	int C6_Ipx = 568,		C6_Ipy = 292;
	int C7_Ipx = 598,		C7_Ipy = 200;
	int C8_Ipx = 419,		C8_Ipy = 200;
	
	
	private Image SelectedImage;
	private int PositionX;
	private int PositionY;
	
	/**
	 * Hardcoded variable should be changed, initially Hardcorded data are stored in the XML File  
	 * The data can be changed during the run time of the System  
	 * Once the data has changed,changed data will be saved on File 
	 * When next time start the System changed values will be used  
	 * Gate ID of all the Door Gates 
	 */  
	
	public static void BeaconVariable(HashMap<String, Beaconimport_Structure> AllBeaconVariable){
	
		/////// Corridor ID of all the Corridor beacons Beacons ///////
		
		Corridor3_ID = AllBeaconVariable.get("Corridor3_ID").getBeaid();
		Corridor4_ID = AllBeaconVariable.get("Corridor4_ID").getBeaid();
		Corridor5_ID = AllBeaconVariable.get("Corridor5_ID").getBeaid();
		Corridor6_ID = AllBeaconVariable.get("Corridor6_ID").getBeaid();
		Corridor7_ID = AllBeaconVariable.get("Corridor7_ID").getBeaid();
		Corridor8_ID = AllBeaconVariable.get("Corridor8_ID").getBeaid();
					
	}

	/**
	 * Constructor:: Get the filtered datas directly from serialport reader.
	 * @param mobiletagid
	 * @param bID_FIRST
	 * @param AlarmInfo
	 * @param RSSI
	 */
	public Coridoor_BEACON_INFO(int mobiletagid, int bID_FIRST, int AlarmInfo, int RSSI) {
		super();
		
		//Rssi value has renamed to alarminfor 
		Beaconimport.Beaconimporting();
		Coridoor_BEACON_INFO.BeaconVariable(Beaconimport.AllBeaconVariable);
		j[i] = bID_FIRST;		//System.out.println("COrdinates: " + j[i]);
		i++;	//System.out.println(" Constructor MTID_BID_RS " + "" + mobiletagid + "," + bID_FIRST+ "," + AlarmInfo);
		Corridorinfo();
		populateShadows();
		People_Shadow_Image(X, Y);
		
	}

	/* Getter and Setter for the important variables*/
	public int getMOBILE_TAG_ID() {
		return MOBILE_TAG_ID;
	}

	public void setMOBILE_TAG_ID(int mOBILE_TAG_ID) {
		MOBILE_TAG_ID = mOBILE_TAG_ID;
	}

	public int getBeaconid() {
		return Beaconid;
	}

	public void setBeaconid(int beaconid) {
		Beaconid = beaconid;
	}

	public int getAlarm_Notification() {
		return Alarm_Notification;
	}

	public void setAlarm_Notification(int Alarm_Notification) {
		Alarm_Notification = Alarm_Notification;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getFLoornumber() {
		return FLoornumber;
	}

	public void setFLoornumber(int fLoornumber) {
		FLoornumber = fLoornumber;
	}

	public Image getSelectedImage() {
		return SelectedImage;
	}

	public void setSelectedImage(Image selectedImage) {
		SelectedImage = selectedImage;
	}

	public int getPositionX() {
		return PositionX;
	}

	public void setPositionX(int positionX) {
		PositionX = positionX;
	}

	public int getPositionY() {
		return PositionY;
	}

	public void setPositionY(int positionY) {
		PositionY = positionY;
	}

	/**
	 * Adding the datas into the ArrayList for easy selection. 
	 * Holds the information of the Image, image cordinates and the Roomcordinates  
	 * Roomcordinates are for the oval paint which is in the center of the rooms.
	 */
	public void populateShadows() {
		if (Mainlyforpaintingshadow.isEmpty()) {
			
			Mainlyforpaintingshadow.add(new PSI_ForMobiletagHolder(CX3, CY3, C3, C3_Ipx, C3_Ipy));
			Mainlyforpaintingshadow.add(new PSI_ForMobiletagHolder(CX4, CY4, C4, C4_Ipx, C4_Ipy));
			Mainlyforpaintingshadow.add(new PSI_ForMobiletagHolder(CX5, CY5, C5, C5_Ipx, C5_Ipy));
			Mainlyforpaintingshadow.add(new PSI_ForMobiletagHolder(CX6, CY6, C6, C6_Ipx, C6_Ipy));
			Mainlyforpaintingshadow.add(new PSI_ForMobiletagHolder(CX7, CY7, C7, C7_Ipx, C7_Ipy));
			Mainlyforpaintingshadow.add(new PSI_ForMobiletagHolder(CX8, CY8, C8, C8_Ipx, C8_Ipy));		
		}
	}

	/**
	 * @param xgot and ygot
	 * Function for storing the datas into the array list and based on X, Y  cordinates selection it will //
	 * select the image and the xy cordinates of the images automatically &  draw the image in the screen //
	 */
	public void People_Shadow_Image(int xgot, int ygot) {
		/*** selects the matching beacon from the arraylists and assign the position */
		for (PSI_ForMobiletagHolder ShadowImage : Mainlyforpaintingshadow) {
			if (xgot == ShadowImage.getX_Position()	&& ygot == ShadowImage.getY_Position()) {
				SelectedImage = ShadowImage.getShadow_Image();
				PositionX = ShadowImage.getX1();
				PositionY = ShadowImage.getY1(); // drawing the image in the screen
				break;
			}
		}
	}
	/**
	 * Adding the Corridor Beacon datas into the Array list for easy selection.
	 * Holds the information of the Coridor name and its cordinates.
	 */
	public void Corridorinfo() {
		if (Corridor_Beacon_Parameter.isEmpty()) {
			Corridor_Beacon_Parameter.add(new CORRIDOR_BID(Corridor3_ID, CX3, CY3));
			Corridor_Beacon_Parameter.add(new CORRIDOR_BID(Corridor4_ID, CX4, CY4));
			Corridor_Beacon_Parameter.add(new CORRIDOR_BID(Corridor5_ID, CX5, CY5));
			Corridor_Beacon_Parameter.add(new CORRIDOR_BID(Corridor6_ID, CX6, CY6));
			Corridor_Beacon_Parameter.add(new CORRIDOR_BID(Corridor7_ID, CX7, CY7));
			Corridor_Beacon_Parameter.add(new CORRIDOR_BID(Corridor8_ID, CX8, CY8));
		}
	}

	/**
	 * Initialise and updates the system. The input datas are taken directly from the serialport reading class
	 * The system will initialised with 3 reading of same beacon from whereever from the floors and assigne the
	 * position of that beacon which has been harcoded into the arraylist. The cordinates position are always
	 * fixed. Only the beacon id will changed in case of damage or sth else..
	 * The corridors has assigned certain rules that it can moves.
	 * @param mobiletagid
	 * @param bID_FIRST
	 * @param AlarmInfo
	 * @param RSSI
	 */
	public void update(int mobiletagid, int bID_FIRST, int alarmInfo, int rSSI){	
		//Beaconimport.Beaconimporting();
		//Coridoor_BEACON_INFO.BeaconVariable(Beaconimport.AllBeaconVariable);
		MOBILE_TAG_ID = mobiletagid;
		Beaconid = bID_FIRST;
		Alarm_Notification= alarmInfo;
		
		if (OriginGot == false) {
			/* Resetting array contents */
			if (i > 2) {
				i = 0;
				j[0] = 0;
				j[1] = 0;
				j[2] = 0; 
			}
			 j[i] =bID_FIRST; System.out.println("Firstreading_BID: " + j[i]);
			i++; System.out.println("COrdinates: " + " " + j[0] + " " + j[1] + " "	+ j[2]);
			//System.out.println("All Cordinates ID: "+ Corridor3_ID +","+ Corridor4_ID +","+Corridor5_ID+","+Corridor6_ID+","+Corridor7_ID+","+Corridor8_ID);
			/* Inatializing the system with first 3 reading of same corridor beacons */
			if (j[0] == j[1] && j[1] == j[2] && (j[2] == Corridor3_ID || j[2] == Corridor4_ID || j[2] == Corridor5_ID
					|| j[2] == Corridor6_ID || j[2] == Corridor7_ID
					|| j[2] == Corridor8_ID)) {
				Selected_CorridorID = j[0]; 	
				System.out.println("Selected COrdinates: "+ Selected_CorridorID);
				
			/* Selects the matching beacon from the arraylists and assign the position */
				for (Object Corridor : Corridor_Beacon_Parameter) {
					CORRIDOR_BID BC = (CORRIDOR_BID) Corridor;
					System.out.println("BC.getBeacon_ID();: "+ BC.getBeacon_ID());

					if (Selected_CorridorID == BC.getBeacon_ID()) {
						OriginGot = true; //Initialised the system from corridor
						MOBILE_TAG_ID = mobiletagid;
						Beaconid = bID_FIRST;
						Alarm_Notification= alarmInfo;
						Previous_BeaconID = Selected_CorridorID;
						X = BC.getCorX();
						Y = BC.getCorY();
						FLoornumber = 2;
						People_Shadow_Image(X, Y);
						System.out.println("Your Sistem has initialised " + MOBILE_TAG_ID + " :: "	+Beaconid);
						break;
					}
				}
			}
		}
		/* Initializing and making the rule for corridor beacons Corridor Handling algorithm */
		if (OriginGot) { 		
			 if (Beaconid == Corridor3_ID	&&  Previous_BeaconID == Corridor4_ID) {
				Previous_BeaconID = Beaconid;
				X = CX3;
				Y = CY3;
				FLoornumber = 2;
				People_Shadow_Image(X, Y);
				System.out.println(X + " " + Y);
			} else if (Beaconid == Corridor4_ID	&& (Previous_BeaconID == Corridor3_ID || Previous_BeaconID == Corridor5_ID)) {
				Previous_BeaconID = Beaconid;
				X = CX4;
				Y = CY4;
				FLoornumber = 2;
				People_Shadow_Image(X, Y);
				System.out.println(X + " " + Y);
			} else if (Beaconid == Corridor5_ID	&& (Previous_BeaconID == Corridor4_ID|| Previous_BeaconID == Corridor6_ID || Previous_BeaconID == Corridor8_ID)) {
				Previous_BeaconID = Beaconid;
				X = CX5;
				Y = CY5;
				FLoornumber = 2;
				People_Shadow_Image(X, Y);
				System.out.println(X + " " + Y);
			} else if (Beaconid == Corridor6_ID && (Previous_BeaconID == Corridor5_ID || Previous_BeaconID == Corridor7_ID)) {
				Previous_BeaconID = Beaconid;
				X = CX6;
				Y = CY6;
				FLoornumber = 2;
				People_Shadow_Image(X, Y);
				System.out.println(X + " " + Y);
			} else if (Beaconid == Corridor7_ID	&& Previous_BeaconID == Corridor6_ID ) {
				Previous_BeaconID = Beaconid;
				X = CX7;
				Y = CY7;
				FLoornumber = 2;
				People_Shadow_Image(X, Y);
				System.out.println(X + " " + Y);
			} else if (Beaconid == Corridor8_ID	&& Previous_BeaconID == Corridor5_ID ) {
				Previous_BeaconID = Beaconid;
				X = CX8;
				Y = CY8;
				FLoornumber = 2;
				People_Shadow_Image(X, Y);
				System.out.println(X + " " + Y);
			
			}
			/* End of Corridor Beacon handling case */
			
			/* Lost case handling. When the mobiletag fails to read the consecutive beacons then this statement will executes */
			else {

				/* Resetting the array */
				if (lost_id_count > 2) {
					lost_id_count = 0;
					lost_id_Buffer_Cori[0] = 0;
					lost_id_Buffer_Cori[1] = 0;
					lost_id_Buffer_Cori[2] = 0;
				}
				lost_id_Buffer_Cori[lost_id_count] = Beaconid;
				lost_id_count++;



				if (lost_id_Buffer_Cori[0] == lost_id_Buffer_Cori[1]
						&& lost_id_Buffer_Cori[1] == lost_id_Buffer_Cori[2]
						&& (lost_id_Buffer_Cori[2] == Corridor3_ID
						|| lost_id_Buffer_Cori[2] == Corridor4_ID
						|| lost_id_Buffer_Cori[2] == Corridor5_ID
						|| lost_id_Buffer_Cori[2] == Corridor6_ID
						|| lost_id_Buffer_Cori[2] == Corridor7_ID
						|| lost_id_Buffer_Cori[2] == Corridor8_ID)) {
					
					Selected_CorridorID = lost_id_Buffer_Cori[0];
					for (Object LOST_BEACON : Corridor_Beacon_Parameter) {
						CORRIDOR_BID BC = (CORRIDOR_BID) LOST_BEACON;
						// BC.getBeacon_ID();
						if (lost_id_Buffer_Cori[0] == BC.getBeacon_ID()) {
							X = BC.getCorX();
							Y = BC.getCorY();
							FLoornumber = 2;
							People_Shadow_Image(X, Y);
							Previous_BeaconID = Selected_CorridorID;
							System.out.println("Your idxy " + MOBILE_TAG_ID + " " + X + " " + Y);
							break;
						}
					}
					
				}
			}	
	/* @Alarm Checking
	 * When the button has pressed the Alarm_Notification, value changes to 126. The system sends that
	 * information to the server and reset the mobiletag by sending some signals. Since the serial port
	 * works on dual-mode, no need to worry about the timeing and other things. Once the mobiletag received that 
	 * signal from the system, it will reset the value of Alarm_notification to 9 ...
	 */
			
//			 try{
//					if(Alarm_Notification == 126){
//						new SerialPortSending(MOBILE_TAG_ID);
//						System.out.println("Alarm Signal has received");
//					}else if(Alarm_Notification ==15){
//						REDledoff();
//						System.out.println("Alarm Red signal has reset");
//					}	
//				}catch(Exception e){
//					e.printStackTrace();
//				}			
									
			 
			}
		}

	}
//}


