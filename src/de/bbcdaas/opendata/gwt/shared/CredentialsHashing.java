package de.bbcdaas.opendata.gwt.shared;

public class CredentialsHashing {

	String salt;
	String hashed;

	public CredentialsHashing(String password) {
//		this.salt = BCrypt.gensalt(12);
//
//		hashed = BCrypt.hashpw(password, salt);
	}

	public String getSalt() {
		return salt;
	}

	public String getHashedPassword() {
		return hashed;
	}

}
