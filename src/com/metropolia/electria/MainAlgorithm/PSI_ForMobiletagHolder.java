package com.metropolia.electria.MainAlgorithm;

import java.awt.Image;

public class PSI_ForMobiletagHolder {
//	private int BID_Selected;
	private int X_Position;
	private int Y_Position;
	private int X1, Y1;
	private Image Shadow_Image;
	
	public PSI_ForMobiletagHolder( int x_Position, int y_Position, Image shadow_Image, int x1, int y1) {
		super();
		X_Position = x_Position;
		Y_Position = y_Position;
		Shadow_Image = shadow_Image;
		this.X1 = x1;
		this.Y1 = y1;
		
	}

	public int getX_Position() {
		return X_Position;
	}
	public void setX_Position(int x_Position) {
		X_Position = x_Position;
	}
	public int getY_Position() {
		return Y_Position;
	}
	public void setY_Position(int y_Position) {
		Y_Position = y_Position;
	}
	public Image getShadow_Image() {
		return Shadow_Image;
	}
	public void setShadow_Image(Image shadow_Image) {
		Shadow_Image = shadow_Image;
	}
	public int getX1() {
		return X1;
	}
	public void setX1(int x1) {
		this.X1 = x1;
	}
	
	public int getY1() {
		return Y1;
	}
	public void setY1(int y1) {
		this.Y1 = y1;
	}
	
	
}
