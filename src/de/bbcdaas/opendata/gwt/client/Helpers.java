package de.bbcdaas.opendata.gwt.client;

import java.util.HashMap;

import com.google.gwt.user.client.History;

public class Helpers {
	public static Boolean IsUserLoggedIn(String username) {
		return true;
	}

	public static HashMap<String, String> getParameters(String queryString) {
		HashMap<String, String> params = new HashMap<String, String>();

		String[] paramsArray = queryString.split("&&");

		for (int i = 0; i < paramsArray.length; i++) {
			String[] parameters = paramsArray[i].split("=");
			if (parameters.length == 2) {
				params.put(parameters[0], parameters[1]);
			}

		}
		return params;

	}

	public static String getPresenterID(String queryString) {
		String[] paramsArray = queryString.split("&&");
		String id = "";
		for (int i = 0; i < paramsArray.length; i++) {
			String[] parameters = paramsArray[i].split("=");
			if (parameters.length == 2 && parameters[0].equals("ID")) {
				id = parameters[1];

			}

		}
		return id;

	}

	public static void addToHistory(String queryParamater, String value) {
		History.newItem(queryParamater + "=" + value);
	}
}
