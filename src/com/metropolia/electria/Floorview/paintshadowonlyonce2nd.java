package com.metropolia.electria.Floorview;
import java.awt.Image;
/*
 * Structure for 2ndfloor rooms shadow only
 */
public class paintshadowonlyonce2nd {
	private Image Imageid;
	private int Ix;
	private int Iy;
	
	public paintshadowonlyonce2nd(Image imageid, int ix, int iy) {
		super();
		Imageid = imageid;
		Ix = ix;
		Iy = iy;
	}
	
	public paintshadowonlyonce2nd() {
		// TODO Auto-generated constructor stub
	}

	public Image getImageid() {
		return Imageid;
	}

	public void setImageid(Image imageid) {
		Imageid = imageid;
	}

	public int getIx() {
		return Ix;
	}

	public void setIx(int ix) {
		Ix = ix;
	}

	public int getIy() {
		return Iy;
	}

	public void setIy(int iy) {
		Iy = iy;
	}

	public void updateimage(int ix, int iy){
//		Imageid = Imageid;
		Ix = ix;
		Iy = iy;			
	}

}