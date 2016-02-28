package com.metropolia.electria.ButtonClickClass;
/**
 * class for showing the information of the beacons, unchanges datas 
 * and can be updated during the runtime. Datas are saved in xml files
 * updated datas are always saved in xml file and latest updated data will be 
 * available for futher used...
 */
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.metropolia.electria.MainAlgorithm.Coridoor_BEACON_INFO;
import com.metropolia.electria.ButtonClickClass.Beaconimport;

public class Beaconinfo extends JFrame {
	static Beaconimport import_BV = new Beaconimport();
	private JComboBox First_Floor_Selection = new JComboBox();
	private JComboBox Second_Floor_Selection = new JComboBox();
	private JComboBox Third_Floor_Selection = new JComboBox();
	
	/* Hard coded static variables and datas*/
	private String[] Select_First = { "Select_Firstfloor_Beacons", "G1_IDF",
			"G2_IDF", "G3_IDF", "G4_IDF", "G5_IDF", "G6_IDF", "G7_IDF",
			"G8_IDF", "Room1_IDF", "Room2_IDF", "Room3_IDF", "Room4_IDF", "Room5_IDF",
			"Corridor3_IDF", "Corridor4_IDF", "Corridor5_IDF", "Corridor6_IDF",	};
	
	private String[] Select_Second = { "Select_Secondfloor_Beacons", "GA06_ID",
			"GA08_ID", "GA10_ID", "GA12_ID", "GA14_ID", "GA17_ID", "GA19_ID", "GA29_ID",
			"GA27_ID", "GA25_ID", "GA23_ID",
			"A06_ID", "A08_ID",
			"A10_ID", "A12_ID", "A14_ID", "A17_ID", "A19_ID",
			"A29_ID", "A27_ID", "A25_ID", "A23_ID", 
			"Corridor1_ID", "Corridor2_ID", "Corridor3_ID", "Corridor4_ID",
			"Corridor5_ID", "Corridor6_ID", "Corridor7_ID", "Corridor8_ID",
			"Corridor9_ID" };

	private String[] Select_Third = { "Select_Thirdfloor_Beacons", "G1_IDT",
			"G2_IDT", "G3_IDT", "G4_IDT", "G5_IDT", "G6_IDT", "G7_IDT",
			"G8_IDT", "Room1_IDT", "Room2_IDT", "Room3_IDT", "Room4_IDT", "Room5_IDT",
			"Room6_IDT", "Room7_IDT", "Room8_IDT", "Room9_IDT", "Room10_IDT",
			 "Corridor1_IDT", "Corridor2_IDT",
			"Corridor3_IDT", "Corridor4_IDT", "Corridor5_IDT", "Corridor6_IDT",
			"Corridor7_IDT", "Corridor8_IDT", "Corridor9_IDT", };

	private static String SelectionSetting;
	private JTextField BeaconOldID, BeaconNewID, PlaceInfo;
	private JButton finish;
	private static JTextArea ShowALLInfo;
	private JScrollPane Scrolle;
	private String name = "BeaconName: ";
	private String ids = " BeaconID: ";
	private static String updatedBeaconName;
	private static int updatedBeaconID;

	public Beaconinfo() {
		super("Beacon Setting");
		import_BV.Beaconimporting();
		 
		final Button Update = new Button("Update");
		final Button ShowAll = new Button("ShowAll");
		BeaconOldID = new JTextField();
		BeaconNewID = new JTextField();
		PlaceInfo = new JTextField();
		JPanel up = new JPanel();
		up.add(First_Floor_Selection);
		up.add(Second_Floor_Selection);
		up.add(Third_Floor_Selection);

		up.add(new JLabel("BeaconOldID"));
		up.add(BeaconOldID);
		up.add(new JLabel("BeaconNewID"));
		up.add(BeaconNewID);
		up.add(new JLabel("BeaconPlace"));
		up.add(PlaceInfo);
		up.setPreferredSize(new Dimension(250, 300));
		
		/* Assigning strings datas to the JComboBox */
		First_Floor_Selection.setEnabled(true);
		First_Floor_Selection.setPreferredSize(new Dimension(200, 30));
		for (int i = 0; i < Select_First.length; i++) {
			First_Floor_Selection.addItem(Select_First[i]);
		}
		Second_Floor_Selection.setEnabled(true);
		Second_Floor_Selection.setPreferredSize(new Dimension(200, 30));
		for (int i = 0; i < Select_Second.length; i++) {
			Second_Floor_Selection.addItem(Select_Second[i]);
		}
		Third_Floor_Selection.setEnabled(true);
		Third_Floor_Selection.setPreferredSize(new Dimension(200, 30));
		for (int i = 0; i < Select_Third.length; i++) {
			Third_Floor_Selection.addItem(Select_Third[i]);
		}
		
		/* JComboBox listeners*/
		First_Floor_Selection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				First_Floor_Selection = (JComboBox) e.getSource();
				SelectionSetting = (String) First_Floor_Selection
						.getSelectedItem();
				System.out.println(SelectionSetting);
			}
		});
		Second_Floor_Selection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Second_Floor_Selection = (JComboBox) e.getSource();
				SelectionSetting = (String) Second_Floor_Selection
						.getSelectedItem();
				System.out.println(SelectionSetting);
			}
		});
		Third_Floor_Selection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Third_Floor_Selection = (JComboBox) e.getSource();
				SelectionSetting = (String) Third_Floor_Selection.getSelectedItem();
				System.out.println(SelectionSetting);
			}
		});
		BeaconOldID.setPreferredSize(new Dimension(200, 25));
		BeaconNewID.setPreferredSize(new Dimension(200, 25));
		PlaceInfo.setPreferredSize(new Dimension(200, 25));
		
		JPanel center = new JPanel();
		center.setPreferredSize(new Dimension(65, 275));
		center.add(ShowAll);
		ShowAll.setPreferredSize(new Dimension(100, 125));
		ShowAll.setBackground(Color.yellow);
		center.add(Update);
		Update.setPreferredSize(new Dimension(100, 125));
		Update.setBackground(Color.red);
		
		JPanel Show = new JPanel();
		ShowALLInfo = new JTextArea();
		Scrolle = new JScrollPane(ShowALLInfo);
		ShowALLInfo.setFont(new Font("Serif", Font.ITALIC, 13));
		ShowALLInfo.setBorder(BorderFactory.createTitledBorder(null, "Show All Beacon Name and ID",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP));
		ShowALLInfo.setLineWrap(true); // wrap line
		ShowALLInfo.setWrapStyleWord(true); // wrap line at word boundary
		ShowALLInfo.setBackground(new Color(204, 238, 241)); // light blue
		ShowALLInfo.setBounds(0, 0, 400, 600);
		ShowALLInfo.setEditable(false);
		Scrolle.setBounds(3, 3, 400, 1000);
		Scrolle.setBorder(BorderFactory.createEmptyBorder());
		Scrolle.setPreferredSize(new Dimension(500, 260));

		Scrolle.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Show.add(Scrolle, BorderLayout.CENTER);
		ShowALLInfo.append(name + ": ");
		ShowALLInfo.append("\t\t\t"+ids + ": \n");

		JPanel bu = new JPanel();
		finish = new JButton("Finish");
		bu.add(finish);
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(765, 25));
		this.add(up, BorderLayout.WEST);
		this.add(center, BorderLayout.CENTER);
		this.add(Show, BorderLayout.EAST);
		this.add(bu, BorderLayout.SOUTH);
		this.add(top, BorderLayout.NORTH);
		
		/* Listeners to the buttons */
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					BeaconID_Update(SelectionSetting, BeaconOldID.getText(),BeaconNewID.getText());
					System.out.println(SelectionSetting + " "+ BeaconOldID.getText() + " "	+ BeaconNewID.getText());
					Coridoor_BEACON_INFO.BeaconVariable(Beaconimport.AllBeaconVariable);										
				} catch (Exception ee) {
					System.out.println("Nothing updated");
				}
			}
		});
		ShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowALLInfo.setText(""); // clears all the TextArea contents
				ShowALLInfo.append(name + ": ");
				ShowALLInfo.append("\t\t\t"+ids + ": \n");
				try {
					Beacondisplay();
				} catch (Exception ee) {
					System.out.println("Nothing shown");
				}
			}
		});

		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Beaconinfo.this.dispose();
					System.out.println("Exitted");
				} catch (Exception ee) {
				}
			}
		});

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocation(400, 600);
		this.setVisible(true);
		this.setResizable(false);
	}
	

	public static String getUpdatedBeaconName() {
		return updatedBeaconName;
	}
	public static void setUpdatedBeaconName(String updatedBeaconName) {
		Beaconinfo.updatedBeaconName = updatedBeaconName;
	}
	public static int getUpdatedBeaconID() {
		return updatedBeaconID;
	}
	public static void setUpdatedBeaconID(int updatedBeaconID) {
		Beaconinfo.updatedBeaconID = updatedBeaconID;
	}


	/**
	 *  Function for displaying the all details of the Beacons information
	 */
	public static void Beacondisplay() {
		String BeaconName= null;
		String BeaconID= null;
		try {
			File fXmlFile = new File("BeaconID_XML.xml");
			if (fXmlFile.exists()) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();

				/* Get the Beacon element by tag name directly */
				NodeList Beaconlist = doc.getElementsByTagName("BeaconName");
				for (int index = 0; index < Beaconlist.getLength(); index++) {
					Node Bnode = Beaconlist.item(index);
					NamedNodeMap attr = Bnode.getAttributes();
					Node nodeAttr = attr.getNamedItem("id");
					System.out.print(BeaconName + nodeAttr.getTextContent());
					ShowALLInfo.append(nodeAttr.getTextContent());
					// loop the Beacon child node
					NodeList list = Bnode.getChildNodes();
					for (int i = 0; i < list.getLength(); i++) {
						Node node = list.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) node;
							System.out.println(BeaconID	+ eElement.getTextContent());
							ShowALLInfo.append("\t\t\t"+ eElement.getTextContent() + "\n");
						}
					}
				}
			} else {
				System.out.println("File did not find");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function for Updating the Beacons value
	 * @param BeconName
	 * @param preID
	 * @param postID
	 */
	public static void BeaconID_Update(String BeconName,String preID, String postID) {
		boolean AlreadyExit = false;
		try {
			String filepath = "BeaconID_XML.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			NodeList Beaconlist = doc.getElementsByTagName("BeaconName");
			/////////////Checking for the existence of the beacon value
			for (int index = 0; index < Beaconlist.getLength(); index++) { 
				Node BBnode = Beaconlist.item(index);
				NodeList llist = BBnode.getChildNodes();
				for (int i = 0; i < llist.getLength(); i++) {
				Node Tnode = llist.item(i);
				//System.out.println("Node "+i+" context: "+ Tnode.getTextContent());
				
				if(postID.equals(Tnode.getTextContent())){
					AlreadyExit=true;
					break;
				}
			}
				System.out.println("Update didn't succeed since value already exits in the systems");
			}
			
				for (int index = 0; index < Beaconlist.getLength(); index++) {
				Node Bnode = Beaconlist.item(index);
				NamedNodeMap attr = Bnode.getAttributes();
				Node nodeAttr = attr.getNamedItem("id");
				// nodeAttr.setTextContent("G5_ID");
				updatedBeaconName = nodeAttr.getTextContent();
				if (nodeAttr.getTextContent().equals(BeconName)) {
					System.out.println("It contains this value");			
					// loop the Beacon child node
					NodeList list = Bnode.getChildNodes();
					/* checking the duplicate value existing */
					System.out.println("Multiple value checking: "+ postID);					
						if(!AlreadyExit){
							for (int i = 0; i < list.getLength(); i++) {
								Node node = list.item(i);
								if ("BeaconID".equals(node.getNodeName())&& preID.equals(node.getTextContent())) {
									node.setTextContent(postID);
									Beaconimport.AllBeaconVariable.get(updatedBeaconName).updatenewBvalue(Integer.parseInt(postID));
									updatedBeaconID = Integer.parseInt(postID);
									System.out.println("updatedBeaconName: " +updatedBeaconName);
									System.out.println("updatedBeaconID:" +postID);
								}					
							}	
						}
				}	
			}
			/* write the content into XML file */
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
			System.out.println("Done");
								
			Beaconimport.Beaconimporting();
			Coridoor_BEACON_INFO.BeaconVariable(Beaconimport.AllBeaconVariable);
			/* Checking */
			/*System.out.println("G1_ID =  " + Beaconimport.AllBeaconVariable.get("G1_ID").getBeaid()); 
			System.out.println("G2_ID =  " + Beaconimport.AllBeaconVariable.get("G2_ID").getBeaid());
			System.out.println("G3_ID =  " + Beaconimport.AllBeaconVariable.get("G3_ID").getBeaid());
			System.out.println("G4_ID =  " + Beaconimport.AllBeaconVariable.get("G4_ID").getBeaid());
			System.out.println("G5_ID =  " + Beaconimport.AllBeaconVariable.get("G5_ID").getBeaid());
			System.out.println("G6_ID =  " + Beaconimport.AllBeaconVariable.get("G6_ID").getBeaid());*/
			
			ShowALLInfo.setText(""); // clears all the TextArea contents
			ShowALLInfo.append("BeaconName: "+ ": ");
			ShowALLInfo.append("\t\t\t"+" BeaconID :: \n");
			Beacondisplay();
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
}
