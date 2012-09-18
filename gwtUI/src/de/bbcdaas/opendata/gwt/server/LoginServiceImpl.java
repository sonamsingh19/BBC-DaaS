package de.bbcdaas.opendata.gwt.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.bbcdaas.opendata.gwt.client.services.ILoginService;

public class LoginServiceImpl extends RemoteServiceServlet implements
		ILoginService {


	 
	

	@Override
	public boolean checkCredentials(String username, String password) {

		return true;
	}

	@Override
	public boolean loginUser(String username) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean logoutUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
