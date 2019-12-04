package com.ruoyi.duge.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.base.BaseEntity;

public class CarOut extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date time;
	
	private int number;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "CarOut [time=" + time + ", number=" + number + "]";
	}
	
	

}
