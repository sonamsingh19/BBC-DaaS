package de.bbcdaas.opendata.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.bbcdaas.opendata.gwt.client.services.IUserService;
import de.bbcdaas.opendata.gwt.shared.beans.UserCompleteDetails;
import de.bbcdaas.opendata.gwt.shared.beans.UserDetails;


public class UserServiceImpl extends RemoteServiceServlet implements IUserService {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1271190088285345212L;

	@Override
	public void createUser(UserCompleteDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DeleteUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDetails getUser(String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean userExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
