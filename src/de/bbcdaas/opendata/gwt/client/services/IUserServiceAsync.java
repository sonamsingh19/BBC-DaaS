package de.bbcdaas.opendata.gwt.client.services;


import com.google.gwt.user.client.rpc.AsyncCallback;

import de.bbcdaas.opendata.gwt.shared.beans.UserCompleteDetails;
import de.bbcdaas.opendata.gwt.shared.beans.UserDetails;

public interface IUserServiceAsync {

	void createUser(UserCompleteDetails user,
			AsyncCallback<Void> callback);

	void updateUser(UserDetails user, AsyncCallback<Void> callback);

	void getUser(String userName, AsyncCallback<UserDetails> callback);

	void DeleteUser(UserDetails user, AsyncCallback<Void> callback);

	void userExists(String email, AsyncCallback<Boolean> callback);

	
}
