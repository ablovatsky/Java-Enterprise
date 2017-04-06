package by.avectis.contracts.model;

import java.io.Serializable;

public enum ProfileType implements Serializable{
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	
	String profileType;
	
	ProfileType(String userProfileType){
		this.profileType = userProfileType;
	}
	
	public String getProfileType(){
		return profileType;
	}
	
}
