package com.nandohusni.sayaguru.ui.signIn.model;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("user_alamat")
	private String userAlamat;

	@SerializedName("user_ktp")
	private Object userKtp;

	@SerializedName("user_nama")
	private String userNama;

	@SerializedName("user_email")
	private String userEmail;

	@SerializedName("user_password")
	private String userPassword;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("user_telpon")
	private String userTelpon;

	@SerializedName("user_kec")
	private Object userKec;

	@SerializedName("user_level")
	private String userLevel;

	@SerializedName("user_status")
	private String userStatus;

	public String getUserStatus() {
		return userStatus;
	}

	@SerializedName("user_kelurahan")
	private Object userKelurahan;

	@SerializedName("user_kab")
	private Object userKab;

	@SerializedName("user_ijazah")
	private Object userIjazah;

	public void setUserAlamat(String userAlamat){
		this.userAlamat = userAlamat;
	}

	public String getUserAlamat(){
		return userAlamat;
	}

	public void setUserKtp(Object userKtp){
		this.userKtp = userKtp;
	}

	public Object getUserKtp(){
		return userKtp;
	}

	public void setUserNama(String userNama){
		this.userNama = userNama;
	}

	public String getUserNama(){
		return userNama;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserTelpon(String userTelpon){
		this.userTelpon = userTelpon;
	}

	public String getUserTelpon(){
		return userTelpon;
	}

	public void setUserKec(Object userKec){
		this.userKec = userKec;
	}

	public Object getUserKec(){
		return userKec;
	}

	public void setUserLevel(String userLevel){
		this.userLevel = userLevel;
	}

	public String getUserLevel(){
		return userLevel;
	}

	public void setUserKelurahan(Object userKelurahan){
		this.userKelurahan = userKelurahan;
	}

	public Object getUserKelurahan(){
		return userKelurahan;
	}

	public void setUserKab(Object userKab){
		this.userKab = userKab;
	}

	public Object getUserKab(){
		return userKab;
	}

	public void setUserIjazah(Object userIjazah){
		this.userIjazah = userIjazah;
	}

	public Object getUserIjazah(){
		return userIjazah;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"user_alamat = '" + userAlamat + '\'' + 
			",user_ktp = '" + userKtp + '\'' + 
			",user_nama = '" + userNama + '\'' + 
			",user_email = '" + userEmail + '\'' + 
			",user_password = '" + userPassword + '\'' + 
			",user_id = '" + userId + '\'' + 
			",user_telpon = '" + userTelpon + '\'' + 
			",user_kec = '" + userKec + '\'' + 
			",user_level = '" + userLevel + '\'' + 
			",user_kelurahan = '" + userKelurahan + '\'' + 
			",user_kab = '" + userKab + '\'' + 
			",user_ijazah = '" + userIjazah + '\'' + 
			"}";
		}
}