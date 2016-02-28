package com.metropolia.electria.MainView;
/** 
 * People information panel for on Peoples Information on button click 
 * Input : JsonArray string from Server 
 * Output: New Panel and hides the main button panels
 * Note  : Showing the information of the peoples in Table format
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.metropolia.electria.Floorview.SecondfloorView;

public class PanelPeople extends JPanel {
	private JTable table;
	private int pHeight = 40;
	public String selectedData = null;
	protected  int mid;	String fn, ln, pto; int Alm, id;
	Object[][] pdata;
	private JScrollPane scrollPane;
	String[] columnNames = { "PHOTO", "PERSON", "Home", "NOTE"};
	
	public PanelPeople() {

	/**        
	* Fetching pdata from the cloud server ///////
	* Input : URL containing JSONArray String
	* Output: Extracting to Table contents
	* Note  : Updates the pdata upon functioncall, downloads and uploads the Imagefile 
	* if it doesnot exists else not. 
	*/ 
      try {
          URL url = new URL("http://indoor-location.herokuapp.com/pos/");
          URLConnection connection = url.openConnection();
          System.out.println(url);
          String line;
          StringBuilder builder = new StringBuilder();
          BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

          while ((line = reader.readLine()) != null) {
              builder.append(line);
          }

          JSONArray items = new JSONArray(builder.toString());
          final int itemCount = items.length();
          pdata = new Object[itemCount][4]; 
          for (int i = 0; i < itemCount; i++) {
              JSONObject json = (JSONObject) items.get(i);
              if(json != null){
              	mid = (Integer) json.get("mid");
              	System.out.print(mid+" ");
              	fn = (String) json.get("firstName");
              	System.out.print(fn+" ");
              	ln = (String) json.get("lastName");
              	System.out.print(ln+" ");
        	
              	/* Checking if the photo is already existed, if so, not need to download */
              	File file = new File("URL_Images/"+fn+".jpg");
              	if(!file.exists()){
              		UploadImg_Server_ToFolder("http://indoor-location.herokuapp.com/patient/"+mid+"/image", "URL_Images/"+fn+".jpg");
              	}else{
              		System.out.println("Imagealready uploaded");
              	}
              	
              	Alm = (Integer) json.get("alarm");
              	System.out.print(Alm +" ");
              	id = (Integer) json.get("id");
              	System.out.print(id+" ");
              }
                pdata[i][0] = new ImageIcon("URL_Images//"+fn+".jpg");
				pdata[i][1] = fn;
				pdata[i][2] = ln;
				pdata[i][3] = mid;			
          }
          
      } catch (Exception e) {
          e.printStackTrace();
      }
      DefaultTableModel model = new DefaultTableModel(pdata, columnNames);
      /* Plotting the fetched pdata from server to the Table*/
      table = new JTable(model) {
      	 /*Implement table cell tool tips.*/
          public String getToolTipText(MouseEvent e) {
              String tip = null;
              java.awt.Point p = e.getPoint();
              int rowIndex = rowAtPoint(p);
              int colIndex = columnAtPoint(p);
              int realColumnIndex = convertColumnIndexToModel(colIndex);

              if (realColumnIndex == 1) { //Sport column
                  tip = "Click to find the Current Position of the Person!!!";
              } else if (realColumnIndex == 2) { tip = "Click to find the Person Room!!!"
                      + "Room number is : "
                      + getValueAt(rowIndex, colIndex);} 
              else { 
            	  /*You can omit this part if you know you don't have any renderers that supply their own tool tips.*/
                  tip = super.getToolTipText(e);
              }
              return tip;
          }

          /* Returning the Class of each column will allow different renderers to be used based on Class*/
			public Class getColumnClass(int column) {
              return getValueAt(0, column).getClass();
          }

          public boolean isCellEditable(int rowIndex, int colIndex) {
              return false; // Disallow the editing of any cell
          }
      };
      
      /* Adjusting number of columns and the specific column width and height */
      TableColumn column0 = table.getColumnModel().getColumn(0); // ///
      column0.setPreferredWidth(200);
      TableColumn column1 = table.getColumnModel().getColumn(1);
      column1.setPreferredWidth(130);
      TableColumn column2 = table.getColumnModel().getColumn(2);
      column2.setPreferredWidth(113);
      TableColumn column3 = table.getColumnModel().getColumn(3);
      column3.setPreferredWidth(140);

      table.setRowHeight(pHeight);
      table.setPreferredScrollableViewportSize(table.getPreferredSize());
      table.setShowGrid(true); // / turn off the line drawn between cells
      table.setShowHorizontalLines(true); //To disable just the the horizontal
      table.setShowVerticalLines(false); // or just the vertical lines:
      table.setGridColor(Color.WHITE); // change the color of the lines
      table.setBackground(Color.GREEN);     
      table.setCellSelectionEnabled(true);
      final ListSelectionModel cellSelectionModel = table.getSelectionModel();
      cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      /* Adding table into the scrollpane */
      scrollPane = new JScrollPane(table); 
      /* Adding  Scrollpane to the panel */
      add(scrollPane);
      scrollPane.setPreferredSize(new Dimension(600, 230));
      
      /* Listener for cell selection model */
      cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
         public void valueChanged(ListSelectionEvent arg0) {
          String selectedData = null;
          int[] selectedRow = table.getSelectedRows();
          int[] selectedColumn = table.getSelectedColumns();
          try{
            selectedData = (String) table.getValueAt(selectedRow[0], selectedColumn[0]); 
            //System.out.println("Number of Row and column in Jtable "+selectedRow[0] +" "+selectedColumn[0]+" "+selectedData );
            new SecondfloorView(selectedData); //// passing this pdata for painting selected one
            cellSelectionModel.clearSelection();  
          }catch(Exception e){
          	e.getMessage();
          }
         }
      });  
              
		setPreferredSize(new Dimension(1184, 230));
		setBackground(Color.gray);
		setVisible(true);
	}
	/**
	 * Uploads image from the server and saves it to the Folder
	 * @param imageUrl
	 * @param destinationFile
	 */
	public static void UploadImg_Server_ToFolder(String imageUrl, String destinationFile) {
		try {
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			System.out.println("Image uploaded to the system");
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Image didn't upload to the system");
		}
	}
	
	public void UpdatePanelPeople() {
		new PanelPeople();
	}
	/*public static void main(String[] args){
		JFrame hello = new JFrame();
		hello.add(new PanelPeople());
		System.out.println("Show sth...");
		hello.setVisible(true);
		hello.setSize(1184, 230);
	}*/
}          