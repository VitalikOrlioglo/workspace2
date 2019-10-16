package model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
//"TITLE": "Empire Burlesque",
//"ARTIST": "Bob Dylan",
//"COUNTRY": "USA",
//"COMPANY": "Columbia",
//"PRICE": "10.90",
//"YEAR": "1985"
public class CD implements Serializable{
	
	@SerializedName("TITLE")
	private String title;
	@SerializedName("ARTIST")
	private String artist;
	@SerializedName("COUNTRY")
	private String country;
	@SerializedName("COMPANY")
	private String company;
	@SerializedName("PRICE")
	private String price;
	@SerializedName("YEAR")
	private String year;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "CD [title=" + title + ", artist=" + artist + ", country=" + country + ", company=" + company
				+ ", price=" + price + ", year=" + year + "]";
	}
	
	
	

}
