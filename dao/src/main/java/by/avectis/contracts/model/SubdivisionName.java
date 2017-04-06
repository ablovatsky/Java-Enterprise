package by.avectis.contracts.model;

import java.io.Serializable;

public enum SubdivisionName implements Serializable{
	SDD("ОРПО"),
	MARKETING("Маркетинг");

	String subdivisionName;

	SubdivisionName(String subdivisionName){
		this.subdivisionName = subdivisionName;
	}
	
	public String getSubdivisionName(){
		return subdivisionName;
	}
	
}
