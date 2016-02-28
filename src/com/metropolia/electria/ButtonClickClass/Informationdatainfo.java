package com.metropolia.electria.ButtonClickClass;

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
/**
 * @author haris
 * class that shows all the information of the patients.
 * patients data can be updated, deleted and displayed.
 * Dates are stored in .XML file.
 */


public class Informationdatainfo extends JFrame {
//static Informationdataimport import_IV = new Informationdataimport();
	private static JComboBox Mobile_Tag_ID = new JComboBox();
	private static String SelectionSetting;
	private JTextField New_MobileTagID, Person_name, Photo_ID, Room_Address, Note_Disableties;
	private JButton finish;
	private static JTextArea ShowALLInfo;
	private JScrollPane Scrolle;
	private String mobiletagid = "   MobileTagID:";
	private String name = " Name: ";
	private String photo = "PhotoID: ";
	private String home = " Home: ";
	private String note = " Note: ";
	private static String updatedBeaconName;
	private static int updatedName;

	/* Constructor */
	public Informationdatainfo() {
		super("People Information");
//		import_IV.Informationdataimporting();
		
		final Button Add = new Button("Add");
		final Button Update = new Button("Update");
		final Button ShowAll = new Button("ShowAll");
		final Button Delete = new Button("Delete");
		New_MobileTagID = new JTextField();
		Person_name = new JTextField();
		Photo_ID = new JTextField();
		Room_Address = new JTextField();
		Note_Disableties = new JTextField();
		JPanel up = new JPanel();
		up.add(new JLabel("Select the MobiletagID to Update/Delete"));
		up.add(Mobile_Tag_ID);
		up.add(new JLabel("OR add new MobileTagID"));
		up.add(New_MobileTagID);
		up.add(new JLabel("Person_name"));
		up.add(Person_name);
		up.add(new JLabel("Photo_ID"));
		up.add(Photo_ID);
		up.add(new JLabel("Room_Address"));
		up.add(Room_Address);
		up.add(new JLabel("Note_Disableties"));
		up.add(Note_Disableties);
		up.setPreferredSize(new Dimension(250, 300));
		Mobile_Tag_ID.setEnabled(true);
		Mobile_Tag_ID.setPreferredSize(new Dimension(200, 25));
		Mobile_Tag_ID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mobile_Tag_ID = (JComboBox) e.getSource();
				SelectionSetting = (String) Mobile_Tag_ID.getSelectedItem();
				//System.out.println("MobileTagID: "+SelectionSetting);
			}
		});

		New_MobileTagID.setPreferredSize(new Dimension(200, 24));
		Person_name.setPreferredSize(new Dimension(200, 24));
		Photo_ID.setPreferredSize(new Dimension(200, 24));
		Room_Address.setPreferredSize(new Dimension(200, 24));
		Note_Disableties.setPreferredSize(new Dimension(200, 24));
		JPanel center = new JPanel();
		center.setPreferredSize(new Dimension(65, 275));
		center.add(ShowAll);
		ShowAll.setPreferredSize(new Dimension(100, 70));
		ShowAll.setBackground(Color.ORANGE);
		center.add(Add);
		Add.setPreferredSize(new Dimension(100, 70));
		Add.setBackground(Color.GREEN);
		center.add(Update);
		Update.setPreferredSize(new Dimension(100, 70));
		Update.setBackground(Color.YELLOW);
		center.add(Delete);
		Delete.setPreferredSize(new Dimension(100, 70));
		Delete.setBackground(Color.RED);
		
		JPanel Show = new JPanel();
		ShowALLInfo = new JTextArea();
		Scrolle = new JScrollPane(ShowALLInfo);
		ShowALLInfo.setFont(new Font("Serif", Font.ITALIC, 13));
		ShowALLInfo.setBorder(BorderFactory.createTitledBorder(null,"Show All People Information",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP));
		ShowALLInfo.setLineWrap(true); // wrap line
		ShowALLInfo.setWrapStyleWord(true); // wrap line at word boundary
		ShowALLInfo.setBackground(new Color(204, 238, 241)); // light blue
		ShowALLInfo.setBounds(0, 0, 400, 600);
		ShowALLInfo.setEditable(false);
		Scrolle.setBounds(3, 3, 400, 1000);
		Scrolle.setBorder(BorderFactory.createEmptyBorder());
		Scrolle.setPreferredSize(new Dimension(500, 300));

		Scrolle.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Show.add(Scrolle, BorderLayout.CENTER);
		ShowALLInfo.append(mobiletagid + ": ");
		ShowALLInfo.append("\t"+name + ": ");
		ShowALLInfo.append("\t"+photo + ": ");
		ShowALLInfo.append("\t"+home + ": ");
		ShowALLInfo.append("\t"+note + ": \n");

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
		
		/* Listeners for the Buttons*/
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowALLInfo.setText(""); // clears all the TextArea contents
				ShowALLInfo.append(mobiletagid + ": ");
				ShowALLInfo.append("\t"+name + ": ");
				ShowALLInfo.append("\t"+photo + ": ");
				ShowALLInfo.append("\t"+home + ": ");
				ShowALLInfo.append("\t"+note + ": \n");
				try {
					Informationadd(New_MobileTagID.getText(), Person_name.getText(),Photo_ID.getText(), Room_Address.getText(),Note_Disableties.getText());
				} catch (Exception ee) {
					System.out.println("Nothing added");
				}
			}
		});
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ShowALLInfo.setText(""); // clears all the TextArea contents
					ShowALLInfo.append(mobiletagid + ": ");
					ShowALLInfo.append("\t"+name + ": ");
					ShowALLInfo.append("\t"+photo + ": ");
					ShowALLInfo.append("\t"+home + ": ");
					ShowALLInfo.append("\t"+note + ": \n");
					Peopleinformation_Update(SelectionSetting, Person_name.getText(),Photo_ID.getText(), Room_Address.getText(),Note_Disableties.getText());
					 System.out.println(SelectionSetting + " "+ Person_name.getText() + " "	+ Photo_ID.getText());
					Coridoor_BEACON_INFO.BeaconVariable(Beaconimport.AllBeaconVariable);
					//FirstfloorView.getInstance().repaint(); // testing
					
				} catch (Exception ee) {
					 System.out.println("Nothing updated");
				}
			}
		});
		ShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowALLInfo.setText(""); // clears all the TextArea contents
				ShowALLInfo.append(mobiletagid + ": ");
				ShowALLInfo.append("\t"+name + ": ");
				ShowALLInfo.append("\t"+photo + ": ");
				ShowALLInfo.append("\t"+home + ": ");
				ShowALLInfo.append("\t"+note + ": \n");
				try {
					Informationdisplay();
				} catch (Exception ee) {
					System.out.println("Nothing shown");
				}
			}
		});
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowALLInfo.setText(""); // clears all the TextArea contents
				ShowALLInfo.append(mobiletagid + ": ");
				ShowALLInfo.append("\t"+name + ": ");
				ShowALLInfo.append("\t"+photo + ": ");
				ShowALLInfo.append("\t"+home + ": ");
				ShowALLInfo.append("\t"+note + ": \n");
				try {
					Peopleinformation_Delete(SelectionSetting);
					System.out.println("Delete operation successed");
					
				} catch (Exception ee) {
					System.out.println("Nothing Deleted");
				}
			}
		});

		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Informationdatainfo.this.dispose();
					// System.out.println("Exitted");
				} catch (Exception ee) {
				}
			}
		});

		try {
			File fXmlFile = new File("Peopleinformation.xml");
			if (fXmlFile.exists()) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();

				// Get the MobileTagid element by tag name directly
				NodeList PeopleInformationlist = doc.getElementsByTagName("MobileTagID");
				for (int index = 0; index < PeopleInformationlist.getLength(); index++) {
					Node Bnode = PeopleInformationlist.item(index);
					NamedNodeMap attr = Bnode.getAttributes();
					Node nodeAttr = attr.getNamedItem("id");
					Mobile_Tag_ID.addItem(nodeAttr.getTextContent());
				}
			}
			
		}catch(Exception e) {
			System.out.println("File did not find");
		}
			
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
		Informationdatainfo.updatedBeaconName = updatedBeaconName;
	}
	public static int getUpdatedName() {
		return updatedName;
	}
	public static void setUpdatedName(int updatedName) {
		Informationdatainfo.updatedName = updatedName;
	}

	/**
	 * Function for adding new Peopleinformatin
	 * @param Mobiletagid
	 * @param Name
	 * @param PhotoID
	 * @param Home
	 * @param Note
	 * @throws SAXException
	 */
	public static void Informationadd(String Mobiletagid, String Name, String PhotoID,  String Home, String Note) throws SAXException{
				try {
					Element rootElement;
					String BECON_ID_RSSI = "Peopleinformation.xml";
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					// rootElement elements
					Document doc = docBuilder.newDocument();
					File file = new File(BECON_ID_RSSI);
					if (file.exists()) {
						doc = docBuilder.parse(file);
						rootElement = doc.getDocumentElement();
						String sr = rootElement.getNodeName();
					} else {
						rootElement = doc.createElement("People_Information_Details");
						doc.appendChild(rootElement);
//						rootElement.setAttribute("Date", "02.08.2012");
					}
									
					Element MobileTagIDs = doc.createElement("MobileTagID");
					MobileTagIDs.setAttribute("id", Mobiletagid);
					rootElement.appendChild(MobileTagIDs);

					Element Names = doc.createElement("Name");
					Names.appendChild(doc.createTextNode(Name));
					MobileTagIDs.appendChild(Names);

					Element PhotoIDs = doc.createElement("PhotoID");
					PhotoIDs.appendChild(doc.createTextNode(PhotoID));
					MobileTagIDs.appendChild(PhotoIDs);

					Element Homes = doc.createElement("Home");
					Homes.appendChild(doc.createTextNode(Home));
					MobileTagIDs.appendChild(Homes);

					Element Notes = doc.createElement("Note");
					Notes.appendChild(doc.createTextNode(Note));
					MobileTagIDs.appendChild(Notes);

					// write the content into xml file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					StringWriter sw = new StringWriter();
					StreamResult result = new StreamResult(sw);
					DOMSource source = new DOMSource(doc);
					transformer.transform(source, result);
					String xmlString = sw.toString();
					FileWriter fw = new FileWriter(file, false);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(xmlString);
					bw.flush();
					bw.close();
					System.out.println("New information is added");
					// Refreshing the JcomboBox contents 
					NodeList PeopleInformationlist = doc.getElementsByTagName("MobileTagID");
					for (int index = 0; index < PeopleInformationlist.getLength(); index++) {
						Node Bnode = PeopleInformationlist.item(index);
						NamedNodeMap attr = Bnode.getAttributes();
						Node nodeAttr = attr.getNamedItem("id");
						Mobile_Tag_ID.addItem(nodeAttr.getTextContent());
					}
					Informationdisplay();
				} catch (ParserConfigurationException pce) {
					pce.printStackTrace();
				} catch (TransformerException tfe) {
					tfe.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	/**
	 * Function for displaying the all details of the patients
	 * Checks the file and extracts the data to the Table
	 */
	public static void Informationdisplay() {
		String MobileTagID= null;
		String Name= null;
		try {
			File fXmlFile = new File("Peopleinformation.xml");
			if (fXmlFile.exists()) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();

				// Get the Beacon element by tag name directly
				NodeList PeopleInformationlist = doc.getElementsByTagName("MobileTagID");
				for (int index = 0; index < PeopleInformationlist.getLength(); index++) {
					Node Bnode = PeopleInformationlist.item(index);
					NamedNodeMap attr = Bnode.getAttributes();
					Node nodeAttr = attr.getNamedItem("id");
					System.out.print(MobileTagID + nodeAttr.getTextContent());
					ShowALLInfo.append("\t"+nodeAttr.getTextContent());
					// loop the Beacon child node
					NodeList list = Bnode.getChildNodes();
					for (int i = 0; i < list.getLength(); i++) {
						Node node = list.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) node;
							// System.out.println(Name	+ eElement.getTextContent());
							ShowALLInfo.append("\t"+ eElement.getTextContent());
						}
					}
					ShowALLInfo.append("\n");
				}
			} else {
				System.out.println("File did not find");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function for Updating the people information
	 * @param MobileTagid_Update
	 * @param updateName
	 * @param updatePhotoid
	 * @param updateHome
	 * @param updateNote
	 */
	public static void Peopleinformation_Update(String MobileTagid_Update, String updateName,
			String updatePhotoid, String updateHome, String updateNote) {
		try {
			String filepath = "Peopleinformation.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			NodeList PeopleInformationlist = doc.getElementsByTagName("MobileTagID");
						
				for (int index = 0; index < PeopleInformationlist.getLength(); index++) {
				Node Bnode = PeopleInformationlist.item(index);
				NamedNodeMap attr = Bnode.getAttributes();
				Node nodeAttr = attr.getNamedItem("id");
				// nodeAttr.setTextContent("G5_ID");
				updatedBeaconName = nodeAttr.getTextContent();
				if (nodeAttr.getTextContent().equals(MobileTagid_Update)) {
					System.out.println("It contains this value");			
					// loop the Beacon child node
					NodeList list = Bnode.getChildNodes();
							for (int i = 0; i < list.getLength(); i++) {
								Node node = list.item(i);
								if ("Name".equals(node.getNodeName())) {
									node.setTextContent(updateName);
								}
								if ("Photoid".equals(node.getNodeName())){
									node.setTextContent(updatePhotoid);
								}
								if ("Home".equals(node.getNodeName())) {
									node.setTextContent(updateHome);
								}
								if ("Note".equals(node.getNodeName())) {
									node.setTextContent(updateNote);
								}
									
//									Beaconimport.AllBeaconVariable.get(updatedBeaconName).updatenewBvalue(Integer.parseInt(Mobiletag_PostID));
						
//									System.out.println("updateddatas are :" +MobileTagid_Update +", "+updateName+", "+updatePhotoid+", " +
//											", "+updateHome+", " +updateNote);
													
							}	
						}
				}	
			// write the content into XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
			System.out.println("Done");
								
			Beaconimport.Beaconimporting();
			Coridoor_BEACON_INFO.BeaconVariable(Beaconimport.AllBeaconVariable);
		
			Informationdisplay();
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

/**
 * Function for deleting the Beacons value
 * @param MobileTagID
 */
public static void Peopleinformation_Delete(String MobileTagID) {
	try {
		String filepath = "Peopleinformation.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);
		NodeList PeopleInformationlist = doc.getElementsByTagName("MobileTagID");
		/////////Deleting the existance mobiletag
	
		for (int index = 0; index < PeopleInformationlist.getLength(); index++) {
			Node Bnode = PeopleInformationlist.item(index);
			NamedNodeMap attr = Bnode.getAttributes();
			Node nodeAttr = attr.getNamedItem("id");
			if (nodeAttr.getTextContent().equals(MobileTagID)) {
				Bnode.getParentNode().removeChild(Bnode);
//				System.out.println("Mobiletagid: "+ MobileTagID+ " is deleted");
				break;
			}	
		}
		Mobile_Tag_ID.addItem(null);
		
		// Refreshing the JcomboBox contents 
		for (int index = 0; index < PeopleInformationlist.getLength(); index++) {
			Node Bnode = PeopleInformationlist.item(index);
			NamedNodeMap attr = Bnode.getAttributes();
			Node nodeAttr = attr.getNamedItem("id");
			Mobile_Tag_ID.addItem(nodeAttr.getTextContent());
		}
		// write the content into XML file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
		// System.out.println("Done");
							
		Beaconimport.Beaconimporting();
		Coridoor_BEACON_INFO.BeaconVariable(Beaconimport.AllBeaconVariable);
	
		Informationdisplay();
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
	/*public static void main(String[] args){
		new Informationdatainfo();
	}*/
}
