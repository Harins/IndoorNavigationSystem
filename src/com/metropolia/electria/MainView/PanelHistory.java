package com.metropolia.electria.MainView;

/**
 * History information panel will execute on History button click 
 * History setting buttom class
 * Input : History Button click 
 * Output: New Panel and hides the main button panels
 * Note  : Showing the alarm history information of the peoples
*/
import java.awt.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.metropolia.electria.Floorview.SecondfloorView;

public class PanelHistory extends JPanel {
	/* Declaring the variables */
	private JScrollPane scrollPane;
	private JTable table;
	private int pHeight = 100;
	protected  String ALMPERSON, ALMPHOTO, ALMTIME, ALMPLACE;
	String[] columnNames = { "NAME", "ALARM_ICON", "DATE_TIME", "PLACE" };
	Object[][] dataa;
	
	/* Constructor*/
	public PanelHistory() {
		
		/* Extracting data's from XML file and showing them in panel GUI in Table form*/
		try {
			String filepath = "AlarmHistory.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new InputSource(filepath));
			NodeList PeopleInformationlist = doc.getElementsByTagName("MobileTagID");
			dataa = new Object[PeopleInformationlist.getLength()][4];		
			for (int index = 0; index < PeopleInformationlist.getLength(); index++) {
				Node Bnode = PeopleInformationlist.item(index);
				NamedNodeMap attr = Bnode.getAttributes();
				//        					 loop the Beacon child node
				NodeList list = Bnode.getChildNodes();
				for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					if ("Name".equals(node.getNodeName())) {
						ALMPERSON = node.getTextContent();
									// System.out.println("Name::::" + ALMPERSON);
					}
					if ("Alarm_Icon".equals(node.getNodeName())){
						ALMPHOTO = node.getTextContent();
									//System.out.println("Photoid::::" + ALMPHOTO);
					}
					if ("Date_Time".equals(node.getNodeName())) {
						ALMTIME = node.getTextContent();
									//System.out.println("TIme::::" + ALMTIME);
					}
					if ("Place".equals(node.getNodeName())) {
						ALMPLACE = node.getTextContent();
									 //System.out.println("Note::::" + ALMPLACE);
					}	
				}
				dataa[index][0] = ALMPERSON;
				dataa[index][1] = ALMPHOTO;
				dataa[index][2] = ALMTIME;
				dataa[index][3] = ALMPLACE;
				System.out.println("Row_number&Length" +index+"::"+PeopleInformationlist.getLength()+
						ALMPHOTO+" "+ALMPERSON+" "+ ALMTIME+" "+ ALMPLACE);
  					}	
  			} catch (ParserConfigurationException pce) {
  				pce.printStackTrace();
  			} catch (IOException ioe) {
  				ioe.printStackTrace();
  			} catch (SAXException sae) {
  				sae.printStackTrace();
  			}
		
		/* Plotting the data's into the Table that will show in GUI Panel*/
		DefaultTableModel model = new DefaultTableModel(dataa, columnNames);
		table = new JTable(model) {
			// Returning the Class of each column will allow different renderer to be used based on Class
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}

		};
		/* Adjusting the number of columns and specific column width */
		TableColumn column0 = table.getColumnModel().getColumn(0);
		column0.setPreferredWidth(130);
		TableColumn column1 = table.getColumnModel().getColumn(1);
		column1.setPreferredWidth(200);
		TableColumn column2 = table.getColumnModel().getColumn(2);
		column2.setPreferredWidth(190);
		TableColumn column3 = table.getColumnModel().getColumn(3);
		column3.setPreferredWidth(190);

		/* Decorating the components with height, width and color */
		table.setRowHeight(pHeight);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setShowGrid(true); // / turn off the line drawn between cells
		table.setShowHorizontalLines(true); // To disable just the the horizontal
		table.setShowVerticalLines(false); // or just the vertical lines:
		table.setGridColor(Color.black); // change the color of the lines
		table.setBackground(Color.YELLOW);
		
		/* Adding the scrollpane to the panel*/
		scrollPane = new JScrollPane(table); // adding table into the scrollpane
		scrollPane.setPreferredSize(new Dimension(600, 230));
		add(scrollPane, BorderLayout.CENTER);
		setPreferredSize(new Dimension(600, 230));
		setBackground(Color.gray);

		
		setPreferredSize(new Dimension(1184, 230));
		setBackground(Color.gray);
		setVisible(true);
	}

	public void UpdatePanelHistory() {
		new PanelHistory();
	}
	
	/*Testing using Main class */
	/*public static void main(String[] args){
		JFrame hello = new JFrame();
		hello.add(new PanelHistory());
		System.out.println("Show sth...");
		hello.setVisible(true);
		hello.setSize(1184, 230);
	}*/
}