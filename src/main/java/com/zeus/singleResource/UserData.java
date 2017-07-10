package com.zeus.singleResource;

import java.util.HashMap;
import java.util.Map;

import com.assign.zeus.model.User;

public class UserData {
	private Map<Integer, User> userData = new HashMap<Integer, User>();
	public Map<Integer, User> getUserData() {
		return userData;
	}
	/*public void setUserData(Map<Integer, User> userData) {
		this.userData = userData;
	}*/
	public static UserData getUserInstance() {
		return userInstance;
	}
	public static void setUserInstance(UserData userInstance) {
		UserData.userInstance = userInstance;
	}
	private static UserData userInstance=null;
	private UserData()
	{}
	public static UserData getUserDataInstance()
	{
		if(userInstance==null)
			userInstance = new UserData();
		return userInstance;
	}

}
