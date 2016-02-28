package com.metropolia.electria.Floorview;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ThirdfloorView extends JPanel {
	private static ThirdfloorView instance = new ThirdfloorView();
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 654;
	private BufferedImage img3rd;
	private Graphics buffer3rd;
	final public Image background3rd = new ImageIcon(getClass().getResource("/Third_Floor_Parvoo.png")).getImage();

	public ThirdfloorView(String selectedonlypaintthird) {  ///Alarm response also use this operation
		super();
		System.out.println("HHHHHHHAAAselectedonlypaintthirdAAAAAAAAAAAAAAAAA"+selectedonlypaintthird);
	}

	private ThirdfloorView() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		img3rd = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		buffer3rd = img3rd.getGraphics();
		buffer3rd.setColor(Color.GRAY);
		buffer3rd.fillRect(0, 0, WIDTH, HEIGHT);

	}

	public static  ThirdfloorView getInstance() {
		return instance;
	}

	public void update3rdscreen() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		buffer3rd.drawImage(background3rd, 0, 0, null);
		buffer3rd.drawString("3rd Floor Parvoo", 450, 43);
		buffer3rd.setColor(Color.BLUE);
		g.drawImage(img3rd, 0, 0, this);
	}
	
}