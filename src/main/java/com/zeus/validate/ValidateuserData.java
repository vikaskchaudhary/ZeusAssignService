package com.zeus.validate;

import com.assign.zeus.model.User;

public class ValidateuserData {
	
	public static boolean validateData(User usr)
	{
		if(usr.getFirstName()==null ||usr.getLastName()==null||usr.getCity()==null||usr.getEmailId()==null||usr.getUserStatus()==null||usr.getUserType()==null)
		return false;
		return true;
	}

}
