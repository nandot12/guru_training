package com.nandohusni.sayaguru.ui.home.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePaket{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("status")
	private boolean status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
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
			"ResponsePaket{" + 
			"pesan = '" + pesan + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}