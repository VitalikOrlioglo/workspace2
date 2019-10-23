package app;

import java.lang.reflect.Field;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Student {

	private int id;
	private String vorname;
	private String nachname;
	private LocalDate geburtsdatum;

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

	@Override
	public String toString() {
		Field[] fields =  getClass().getDeclaredFields();
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName()).append("[");
		for (Field field : fields) {
			try {
				sb.append(field.getName()).append("=").append(field.get(this)).append(",");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				
			}
		}
		sb.replace(sb.length()-1, sb.length(), "");
		sb.append("]");
		return sb.toString();
	}
//	
//	public String toString() {
//		return ReflectionToStringBuilder.toString(this);
//	}

}
