package com.metropolia.electria.marimills;
/*
 * Data structure for holding the information of data that will send to the Server.
 * Stores the information of MObiletagid, beaconid and alarmid and also contains update method
 */
public class senddata_structure {
	private int mid;
	private int bid;
	private int aid;

public senddata_structure(int mid, int bid, int aid) {
		super();
		this.mid = mid;
		this.bid = bid;
		this.aid = aid;
	}

	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public void updatadatatosent(int bid, int aid){
		this.bid = bid;
		this.aid = aid;
}
}