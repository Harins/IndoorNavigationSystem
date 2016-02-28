package com.metropolia.electria.ButtonClickClass;

public class Beaconimport_Structure {
	
	public String Bname;
	public int Beaid;
	
	public Beaconimport_Structure(String bname, int beaid) {
		super();
		Bname = bname;
		Beaid = beaid;
	}
	
	public String getBname() {
		return Bname;
	}
	public void setBname(String bname) {
		Bname = bname;
	}
	public int getBeaid() {
		return Beaid;
	}
	public void setBeaid(int beaid) {
		Beaid = beaid;
	}
	
	public void updatenewBvalue( int beaid){
//		Bname = bname;
		Beaid = beaid;
	}

}
