package by.pvt.restaurants.model;

import java.io.Serializable;

public enum ProfileType implements Serializable{
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	
	String profileType;
	
	private ProfileType(String userProfileType){
		this.profileType = userProfileType;
	}
	
	public String getProfileType(){
		return profileType;
	}
	
}
