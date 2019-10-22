package app;

import java.lang.reflect.Method;
import java.time.LocalDate;


public class Student {
	private int id;
	private String vorname;
	private String nachname;
	private LocalDate geburtsdatum;
	public Student() {
	}
	
	public Student(int id, String vorname, String nachname, LocalDate geburtsdatum) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
//	@Override
//	public String toString() {
//		return "Student [id=" + id + ", vorname=" + vorname + ", nachname=" + nachname + ", geburtsdatum="
//				+ geburtsdatum + "]";
//	}
//	

	
	/*
	 * generische toString mit Hilfe der Reflection API
	 * wenn Felder dazu kommen, dann soll toString immer noch funktionieren
	 */

	
	
}
