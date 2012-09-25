package de.bbcdaas.opendata.gwt.client.services;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("loginService")
public interface ILoginService extends RemoteService {
	
	  boolean checkCredentials(String username,String password);
	  boolean loginUser(String username);
	  boolean logoutUser(String username);
}
