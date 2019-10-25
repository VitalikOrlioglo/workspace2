package model;

import java.io.Serializable;
import java.time.LocalTime;
/**
 * 
 * @author Student
 *
 */
public class MessageObject implements Serializable {


    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String name; // Username
    private String message; 
    private LocalTime time; // Rechnerzeit
    
    private boolean isResponseObject;

    public MessageObject(String name, String message, LocalTime time) {
	this.name = name;
	this.message = message;
	this.time = time;
    }

    public String getName() {
    	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

	public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public LocalTime getTime() {
	return time;
    }

    public void setTime(LocalTime time) {
	this.time = time;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((time == null) ? 0 : time.hashCode());
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
	MessageObject other = (MessageObject) obj;
	if (message == null) {
	    if (other.message != null)
		return false;
	} else if (!message.equals(other.message))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (time == null) {
	    if (other.time != null)
		return false;
	} else if (!time.equals(other.time))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "MessageObject [name=" + name + ", message=" + message + ", time=" + time + "]";
    }

	public boolean isResponseObject() {
		return isResponseObject;
	}

	public void setResponseObject(boolean isResponseObject) {
		this.isResponseObject = isResponseObject;
	}

}
