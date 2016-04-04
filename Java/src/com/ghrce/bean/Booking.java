package com.ghrce.bean;

public class Booking {

	private int bookingId;
	private String vehicle;	
	private int slot;
	private float rate;
	
	
	/**
	 * @return the bookingId
	 */
	public int getBookingId() {
		return bookingId;
	}

	
	/**
	 * @return the vehicle
	 */
	public String getVehicle() {
		return vehicle;
	}

	/**
	 * @return the rate
	 */
	public float getRate() {
		return rate;
	}

	/**
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}

	
	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}


	/**
	 * @param slot the slot to set
	 */
	public void setSlot(int slot) {
		this.slot = slot;
	}


	/**
	 * @param rate the rate to set
	 */
	public void setRate(float rate) {
		this.rate = rate;
	}


	public Booking() {
		// TODO Auto-generated constructor stub
	}

}
