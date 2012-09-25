package de.bbcdaas.opendata.gwt.client.services;


import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ILoginServiceAsync {

	void checkCredentials(String username, String password,
			AsyncCallback<Boolean> callback);

	void loginUser(String username, AsyncCallback<Boolean> callback);

	void logoutUser(String username, AsyncCallback<Boolean> callback);

}
