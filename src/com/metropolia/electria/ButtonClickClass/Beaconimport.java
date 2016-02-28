package com.metropolia.electria.ButtonClickClass;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.metropolia.electria.MainAlgorithm.Coridoor_BEACON_INFO;
import com.metropolia.electria.ButtonClickClass.Beaconimport_Structure;

public class Beaconimport {
	public static HashMap<String, Beaconimport_Structure> AllBeaconVariable = new HashMap<String, Beaconimport_Structure>();
	
/**
 * Function for importing all variable of the Beacons from the file.
 * Function checks the data from file and collects the data to the HashMap for 
 * further processing.
 */
public static void Beaconimporting() {
String BeaconName ;
int BeaconID;

try {
		File fXmlFile = new File("BeaconID_XML.xml");
		if (fXmlFile.exists()) {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			// Get the Beacon element by tag name directly
			NodeList Beaconlist = doc.getElementsByTagName("BeaconName");
			for (int index = 0; index < Beaconlist.getLength(); index++) {
				Node Bnode = Beaconlist.item(index);
				NamedNodeMap attr = Bnode.getAttributes();
				Node nodeAttr = attr.getNamedItem("id");
				//System.out.print("BeaconName: " + nodeAttr.getTextContent());
				BeaconName = nodeAttr.getTextContent();
				
				NodeList list = Bnode.getChildNodes();
					Node node = list.item(1);
					//System.out.println("		BeaconID: "	+ node.getTextContent());
					BeaconID = Integer.parseInt(node.getTextContent());
				////// Storing the data into the Hashmap //////
			if(AllBeaconVariable.containsKey(BeaconName)){
				AllBeaconVariable.get(BeaconName).updatenewBvalue(BeaconID);
				//System.out.println("HashMapcontains: "+ BeaconName+"--"+BeaconID);
			}else{
				AllBeaconVariable.put(BeaconName, new Beaconimport_Structure(BeaconName, BeaconID));
				System.out.println("Hashmapcontainsnew value: "+ BeaconName+"--"+BeaconID);
			}
			}
			Coridoor_BEACON_INFO.BeaconVariable(AllBeaconVariable);
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("File did not find");
	}
}
}