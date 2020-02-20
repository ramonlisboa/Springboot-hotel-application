package net.javahotel.springboot.Springboothotelapplication.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hotelcheckin")
public class Hotelcheckin implements Serializable {

private static final long serialVersionUID = 1676987749L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "hotelguest_id", referencedColumnName="id")
	private Hotelguest hotelguest;
	
	@Column(name = "entry_date", nullable = false)
	private Date entryDate;
	
	@Column(name = "out_date", nullable = true)
	private Date outDate;
	
	@Column(name = "ie_vehicle", nullable = true)
	private boolean ieVehicle;
	
	@Column(name = "ie_status", nullable = false)
	private char ieStatus;	
	
	@Column(name = "vl_total", nullable = true)
	private double vlTotal;
	
	/*
	 * private static final double VL_DAILY_UTIL = 120; private static final double
	 * VL_DAILY_WEEKEND = 150; private static final double VL_GARAGE_UTIL = 15;
	 * private static final double VL_GARAGE_WEEKEND = 20;
	 */
	
	public Hotelcheckin() {}

	public Hotelcheckin(long id, Hotelguest hotelGuest, Date entryDate, Date outDate, boolean ieVehicle, char ieStatus, double vlTotal) {
		this.id = id;
		this.hotelguest = hotelGuest;
		this.entryDate = entryDate;
		this.outDate = outDate;
		this.ieVehicle = ieVehicle;
		this.ieStatus = ieStatus;
		this.vlTotal = vlTotal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Hotelguest getHotelguest() {
		return hotelguest;
	}

	public void setHotelguest(Hotelguest hotelGuest) {
		this.hotelguest = hotelGuest;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public boolean isIeVehicle() {
		return ieVehicle;
	}

	public void setIeVehicle(boolean ieVehicle) {
		this.ieVehicle = ieVehicle;
	}

	public char getIeStatus() {
		return ieStatus;
	}

	public void setIeStatus(char ieStatus) {
		this.ieStatus = ieStatus;
	}

	public double getVlTotal() {
		return vlTotal;
	}
	
	public void setVlTotal(double vlTotal) {
		this.vlTotal = vlTotal;
	}
	/*
	public boolean isWeekDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		
		if(day == 1 || day == 7) {
			return false;
		}
		
		return true;
	}
	
	public double getVlDaily() {
		if(isWeekDay()) {
			return VL_DAILY_UTIL;
		} 
		
		return VL_DAILY_WEEKEND;
	}
	
	public double getVlGarage() {
		if(isIeVehicle()) {
			if(isWeekDay()) {
				return VL_GARAGE_UTIL;
			} else {
				return VL_GARAGE_WEEKEND;
			}
		}
		return 0;
	}
	
	public double getVlTotalInitial() {
		double vlCalculated = 0;
		
		vlCalculated += getVlDaily() + getVlGarage();
		
		return vlCalculated;
	}
	
	public static Calendar DateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);		
		
		return calendar;
	}
	
	public boolean isVlExtraDaily() {
		Calendar actualDate = DateToCalendar(new Date());
		Calendar hourCalendar = DateToCalendar(getOutDate());
		actualDate.set(Calendar.HOUR_OF_DAY, 16);
		actualDate.set(Calendar.MINUTE, 30);
		
		if(hourCalendar.after(actualDate)) {
			return true;
		}
		return false;
	}
	
	public double getVlExtraDaily() {
		double vlExtra = 0;
		if(isVlExtraDaily()) {
			vlExtra = getVlDaily();
		}
		return vlExtra;
	}
	*/
	
	
	
}
