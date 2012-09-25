package de.bbcdaas.opendata.gwt.client;

import java.util.Date;

import com.google.gwt.user.client.Cookies;

public class CookiesHandler {
	
	public static String getCookie(CookieConstants cookieConstant) {
		return Cookies.getCookie(cookieConstant.toString());
	}

	public static void setcookie(CookieConstants cookieConstant,String value) {
	  Date date=new Date();
	long iTimeStamp = date.getTime() ;
	
	 date.setTime(iTimeStamp+TIME_TO_EXPIRE);
	  
	Cookies.setCookie(CookieConstants.USERNAME.toString(), value);
		
	}
	
	public static void DeleteCookie(CookieConstants cookiesConstants)
	{
		Cookies.removeCookie(cookiesConstants.toString());
	
	}
	static int TIME_TO_EXPIRE = 10000; //milliseconds
 
}
