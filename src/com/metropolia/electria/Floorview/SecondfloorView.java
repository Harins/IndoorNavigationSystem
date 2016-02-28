package com.metropolia.electria.Floorview;

/** Paints the second floor map, rooms and the peoples
 * Input: All the information from Coridoor_BEACON_INFO class
 * @HashMap "paintselectedroom2nd" will store all the static datas of the rooms and its positions
 * @HashMap "paintshadow2nd" will allow to paints the shadow only once
 * @Reference: Images where the mobiletag holder resides
 * Output: Paints number of people in particular place
 */

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

import com.metropolia.electria.MainAlgorithm.Coridoor_BEACON_INFO;
import com.metropolia.electria.marimills.PortUI_Setting;


public class SecondfloorView extends JPanel {
	private static SecondfloorView instance = new SecondfloorView();
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 656;
	private BufferedImage img2nd;
	private Graphics buffer2nd;
	final public Image background2nd = new ImageIcon(getClass().getResource("/Second_Floor_Parvoo.png")).getImage();
	private  int No_People_Place;
	String Selectedonlypaint2nd;
	
	
	public SecondfloorView(String selectedonlypaint2nd) {  ///Alarm response also use this operation
		super();
		Selectedonlypaint2nd = selectedonlypaint2nd;
//		System.out.println("HHHHHHHAAAselectedonlypaintthirdAAAAAAAAAAAAAAAAA"+selectedonlypaint2nd);
	}
	private SecondfloorView() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		img2nd = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		buffer2nd = img2nd.getGraphics();
		buffer2nd.setColor(Color.gray);
		buffer2nd.fillRect(0, 0, WIDTH, HEIGHT);
	}

	public static SecondfloorView getInstance() {
		return instance;
	}

	public void update2ndscreen() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		buffer2nd.drawImage(background2nd, 0, 0, null);
		buffer2nd.drawString("2nd Floor Parvoo", 450, 43);
		buffer2nd.setColor(Color.GREEN);
		No_People_Place = 0;


		Collection<Coridoor_BEACON_INFO> readings = PortUI_Setting.multiple_mobile.values();
		HashMap<Image, paintshadowonlyonce2nd> paintshadow2nd = new HashMap<Image, paintshadowonlyonce2nd>(); // Shadow painting only once
//		HashMap<String, Secondfloorroomstructure>paintselectedroom2nd = new HashMap<String, Secondfloorroomstructure>(); // for selected room painting, finding rooms
		HashMap<Image, Integer> Noof_People_inPlace = new HashMap<Image, Integer>(); //for total number of people in particular place
		
	
		
		for (Coridoor_BEACON_INFO cbi : readings) {
			if (cbi.getFLoornumber() == 2) {
				//System.out.println("PaintgarneMobileTagandBID: "+ cbi.getMOBILE_TAG_ID()+", "+ cbi.getBeaconid());
				if(!paintshadow2nd.containsKey(cbi.getSelectedImage())){ 
					paintshadow2nd.put(cbi.getSelectedImage(), new paintshadowonlyonce2nd(cbi.getSelectedImage(), cbi.getPositionX(), cbi.getPositionY()));
					No_People_Place = 1;
					buffer2nd.drawImage(cbi.getSelectedImage(), cbi.getPositionX(), cbi.getPositionY(), null);
					buffer2nd.fillOval(cbi.getX(), cbi.getY(), 10, 10);
					buffer2nd.drawString(-cbi.getMOBILE_TAG_ID() + "", cbi.getX(),	cbi.getY());
					
//					if(!Noof_People_inRoom.containsKey(cbi.getSelectedImage())){
//						Noof_People_inRoom.put(cbi.getSelectedImage(), new Noofpeopleinplace2nd(cbi.getSelectedImage(), No_People_Place));
//					}	
//				}else{
//					No_People_Place = No_People_Place + 1;
//					paintshadow2nd.get(cbi.getSelectedImage()).updateimage(cbi.getPositionX(), cbi.getPositionY());
//					Noof_People_inRoom.get(cbi.getSelectedImage()).Update_Noofpeople(No_People_Place);
				}else{
					paintshadow2nd.get(cbi.getSelectedImage()).updateimage(cbi.getPositionX(), cbi.getPositionY());
					No_People_Place = No_People_Place + 1;
					Noof_People_inPlace.put(cbi.getSelectedImage(), No_People_Place);
					buffer2nd.drawImage(cbi.getSelectedImage(), cbi.getPositionX(), cbi.getPositionY(), null);
					buffer2nd.fillOval(cbi.getX(), cbi.getY(), 10, 10);
					buffer2nd.drawString("No: "+ Noof_People_inPlace.get(cbi.getSelectedImage()), cbi.getX()-5, cbi.getY()-15);
					buffer2nd.drawString(-cbi.getMOBILE_TAG_ID() + "", cbi.getX(), cbi.getY());
					System.out.println("No_People_Place: "+ Noof_People_inPlace.get(cbi.getSelectedImage()));
				}
				//buffer2nd.drawImage(cbi.getSelectedImage(), cbi.getPositionX(), cbi.getPositionY(), null);
				//buffer2nd.fillOval(cbi.getX(), cbi.getY(), 10, 10);
				//buffer2nd.drawString(-cbi.getMOBILE_TAG_ID() + "", cbi.getX(),	cbi.getY());
				//Testing the x, y cordinates
				//System.out.println("imgcordinated: "+ cbi.getPositionX()+", "+ cbi.getPositionY());
				//System.out.println("mobiletagcordinate: "+ cbi.getX()+", "+ cbi.getY());
			}	
			
		}
		g.drawImage(img2nd, 0, 0, this);
	}
}
