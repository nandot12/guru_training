package com.nandohusni.sayaguru.ui.home.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItem implements Serializable {

	@SerializedName("order_tanggal")
	private String orderTanggal;

	@SerializedName("order_lat")
	private String orderLat;

	@SerializedName("order_status")
	private String orderStatus;

	@SerializedName("order_guru")
	private String orderGuru;

	@SerializedName("order_alamat")
	private String orderAlamat;

	@SerializedName("order_lon")
	private String orderLon;

	@SerializedName("order_jp")
	private String orderJp;

	@SerializedName("order_user")
	private String orderUser;

	@SerializedName("order_id")
	private String orderId;

	@SerializedName("booking_ket")
	private String bookingKet;

	public void setOrderTanggal(String orderTanggal){
		this.orderTanggal = orderTanggal;
	}

	public String getOrderTanggal(){
		return orderTanggal;
	}

	public void setOrderLat(String orderLat){
		this.orderLat = orderLat;
	}

	public String getOrderLat(){
		return orderLat;
	}

	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus(){
		return orderStatus;
	}

	public void setOrderGuru(String orderGuru){
		this.orderGuru = orderGuru;
	}

	public String getOrderGuru(){
		return orderGuru;
	}

	public void setOrderAlamat(String orderAlamat){
		this.orderAlamat = orderAlamat;
	}

	public String getOrderAlamat(){
		return orderAlamat;
	}

	public void setOrderLon(String orderLon){
		this.orderLon = orderLon;
	}

	public String getOrderLon(){
		return orderLon;
	}

	public void setOrderJp(String orderJp){
		this.orderJp = orderJp;
	}

	public String getOrderJp(){
		return orderJp;
	}

	public void setOrderUser(String orderUser){
		this.orderUser = orderUser;
	}

	public String getOrderUser(){
		return orderUser;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setBookingKet(String bookingKet){
		this.bookingKet = bookingKet;
	}

	public String getBookingKet(){
		return bookingKet;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"order_tanggal = '" + orderTanggal + '\'' + 
			",order_lat = '" + orderLat + '\'' + 
			",order_status = '" + orderStatus + '\'' + 
			",order_guru = '" + orderGuru + '\'' + 
			",order_alamat = '" + orderAlamat + '\'' + 
			",order_lon = '" + orderLon + '\'' + 
			",order_jp = '" + orderJp + '\'' + 
			",order_user = '" + orderUser + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",booking_ket = '" + bookingKet + '\'' + 
			"}";
		}
}