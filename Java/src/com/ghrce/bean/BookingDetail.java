package com.ghrce.bean;

import java.sql.Time;
import java.util.Date;

public class BookingDetail {

	String bookingid;
	String userid;
	int floorno;
	int slotno;
	Date date;
	Time bookingtime;
	String type;
	String vehicleno;
	Time entrytime;
	float duration;
	float extension;
	/**
	 * @return the bookingid
	 */
	public String getBookingid() {
		return bookingid;
	}
	/**
	 * @param bookingid the bookingid to set
	 */
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the floorno
	 */
	public int getFloorno() {
		return floorno;
	}
	/**
	 * @param floorno the floorno to set
	 */
	public void setFloorno(int floorno) {
		this.floorno = floorno;
	}
	/**
	 * @return the slotno
	 */
	public int getSlotno() {
		return slotno;
	}
	/**
	 * @param slotno the slotno to set
	 */
	public void setSlotno(int slotno) {
		this.slotno = slotno;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the bookingtime
	 */
	public Time getBookingtime() {
		return bookingtime;
	}
	/**
	 * @param bookingtime the bookingtime to set
	 */
	public void setBookingtime(Time bookingtime) {
		this.bookingtime = bookingtime;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the vehicleno
	 */
	public String getVehicleno() {
		return vehicleno;
	}
	/**
	 * @param vehicleno the vehicleno to set
	 */
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	/**
	 * @return the entrytime
	 */
	public Time getEntrytime() {
		return entrytime;
	}
	/**
	 * @param entrytime the entrytime to set
	 */
	public void setEntrytime(Time entrytime) {
		this.entrytime = entrytime;
	}
	/**
	 * @return the duration
	 */
	public float getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(float duration) {
		this.duration = duration;
	}
	/**
	 * @return the extension
	 */
	public float getExtension() {
		return extension;
	}
	/**
	 * @param extension the extension to set
	 */
	public void setExtension(float extension) {
		this.extension = extension;
	}
	
	
}
