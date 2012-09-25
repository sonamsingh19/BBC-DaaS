package de.bbcdaas.opendata.gwt.shared.beans;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserDetails implements IsSerializable  {

	String username;
	String email;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
