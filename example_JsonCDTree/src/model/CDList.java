package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CDList implements Serializable{
	
	@SerializedName("CD")
	private List<CD> cdlist = new ArrayList<>();

	public List<CD> getCdlist() {
		return cdlist;
	}

	public void setCdlist(List<CD> cdlist) {
		this.cdlist = cdlist;
	}

	@Override
	public String toString() {
		return "CDList [cdlist=" + cdlist + "]";
	}
	

}
