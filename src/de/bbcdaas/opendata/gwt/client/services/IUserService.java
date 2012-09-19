package de.bbcdaas.opendata.gwt.client.services;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.bbcdaas.opendata.gwt.shared.beans.UserCompleteDetails;
import de.bbcdaas.opendata.gwt.shared.beans.UserDetails;

@RemoteServiceRelativePath("userService")
public interface IUserService extends RemoteService {

  void  createUser(UserCompleteDetails user);
  void updateUser(UserDetails user);
  void DeleteUser(UserDetails user);
  UserDetails getUser(String userEmail);
  Boolean userExists(String email);
  
}
