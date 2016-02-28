package com.metropolia.electria.MainAlgorithm;

public class CORRIDOR_BID {
	private int Beacon_ID;
	private int CorX;
	private int CorY;
	public CORRIDOR_BID(int beacon_ID, int corX, int corY) {
		super();
		Beacon_ID = beacon_ID;
		CorX = corX;
		CorY = corY;		
	}
	public int getBeacon_ID() {
		return Beacon_ID;
	}
	public void setBeacon_ID(int beacon_ID) {
		Beacon_ID = beacon_ID;
	}
	public int getCorX() {
		return CorX;
	}
	public void setCorX(int corX) {
		CorX = corX;
	}
	public int getCorY() {
		return CorY;
	}
	public void setCorY(int corY) {
		CorY = corY;
	}
	

}
