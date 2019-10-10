package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Student  implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String matrikelnummer;
	private String vorname;
	private String nachname;
	private LocalDate geburtsdatum;  //sq.Date, String, ?
	private String bild="";  // nur Pfad Speichern/ oder nur Name
	//private transient String bild;  //  transient -> wird nicht serialisiert 
	
	public Student() {
		
	}
	public Student(String matrikelnummer, String vorname, String nachname, LocalDate geburtsdatum) {
		this.matrikelnummer = matrikelnummer;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
	}
	public Student(String matrikelnummer, String vorname, String nachname, LocalDate geburtsdatum, String bild) {
		this.matrikelnummer = matrikelnummer;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.bild = bild;
	}
	public String getMatrikelnummer() {
		return matrikelnummer;
	}
	public void setMatrikelnummer(String matrikelnummer) {
		this.matrikelnummer = matrikelnummer;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public String getBild() {
		return bild;
	}
	public void setBild(String bild) {
		this.bild = bild;
	}
	@Override
	public String toString() {
		return "Student [matrikelnummer=" + matrikelnummer + ", vorname=" + vorname + ", nachname=" + nachname
				+ ", geburtsdatum=" + geburtsdatum + ", bild=" + bild + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matrikelnummer == null) ? 0 : matrikelnummer.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (matrikelnummer == null) {
			if (other.matrikelnummer != null)
				return false;
		} else if (!matrikelnummer.equals(other.matrikelnummer))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
