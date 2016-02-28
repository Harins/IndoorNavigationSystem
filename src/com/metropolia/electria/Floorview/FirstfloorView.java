package com.metropolia.electria.Floorview;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class FirstfloorView extends JPanel {
	private static FirstfloorView instance = new FirstfloorView();
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 656;
	private BufferedImage img;
	private Graphics buffer;
	final public Image background = new ImageIcon(getClass().getResource("/First_Floor_Parvoo.png")).getImage();

	public FirstfloorView(String selectedonlypaint) {  ///Alarm response also use this operation
		super();
	}

	private FirstfloorView() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		buffer = img.getGraphics();
		buffer.fillRect(0, 0, WIDTH, HEIGHT);
	}

	public static  FirstfloorView getInstance() {
		return instance;
	}

	public void update1stscreen() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		buffer.drawImage(background, 0, 0, null);
		buffer.drawString("1st Floor Parvoo", 450, 43);
		buffer.setColor(Color.WHITE);
		g.drawImage(img, 0, 0, this);
	}
	}
