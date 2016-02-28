package com.metropolia.electria.ButtonClickClass;
/* Structure for holding the all information of the patients */

public class Informationdataimport_Structure {
	public int mid;
	public String name;
	public int photoid;
	public String roomno;
	public String note;
	public Informationdataimport_Structure(int mid, String name, int photoid,
			String roomno, String note) {
		super();
		this.mid = mid;
		this.name = name;
		this.photoid = photoid;
		this.roomno = roomno;
		this.note = note;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhotoid() {
		return photoid;
	}
	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
