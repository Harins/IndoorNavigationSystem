package com.metropolia.electria.Floorview;
/*
 * Structure for storing 2ndfloor Image information  only.
 * @note: needs for painting shadow only one time
 */
import java.awt.Image;
public class Secondfloorroomstructure {
	
	private Image Rimg;
	private int Rix;
	private int Riy;
	public Secondfloorroomstructure(Image rimg, int rix, int riy) {
		super();
		Rimg = rimg;
		Rix = rix;
		Riy = riy;
	}
	public Image getRimg() {
		return Rimg;
	}
	public void setRimg(Image rimg) {
		Rimg = rimg;
	}
	public int getRix() {
		return Rix;
	}
	public void setRix(int rix) {
		Rix = rix;
	}
	public int getRiy() {
		return Riy;
	}
	public void setRiy(int riy) {
		Riy = riy;
	}

}
