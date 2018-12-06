package com.nandohusni.sayaguru.ui.signIn.model;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private Data data;

	@SerializedName("status")
	private boolean status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLogin{" + 
			"pesan = '" + pesan + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}