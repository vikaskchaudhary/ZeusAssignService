package com.zeus.assign;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assign.enums.UserStatus;
import com.assign.enums.UserType;
import com.assign.zeus.model.User;
import com.zeus.singleResource.UserData;
import com.zeus.validate.ValidateuserData;


/**
 * Handles requests for the User service.
 */
@CrossOrigin(origins = "*")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//Map to store employees, ideally we should use database
	UserData userData= UserData.getUserDataInstance();
	
	@RequestMapping(value = UserConstants.DUMMY_EMP, method = RequestMethod.GET)
	public @ResponseBody User getDummyUser() {
		logger.info("Start getDummyUser");
		User usr = new User();
		usr.setId(9999);
		usr.setEmailId("dummy@dummy.com");
		usr.setFirstName("Dummy");
		usr.setLastName("Data");
		usr.setUserStatus(UserStatus.Active);
		usr.setUserType(UserType.superAdmin);
		
		return usr;
	}
	
	@RequestMapping(value = UserConstants.GET_USER, method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable("id") int userId) {
		logger.info("Start getUser. ID="+userId);
		
		return userData.getUserData().get(userId);
	}
	
	@RequestMapping(value = UserConstants.GET_ALL_USER, method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {
		logger.info("Start getAllUsers.");
		List<User> usrs = new ArrayList<User>();
		Set<Integer> empIdKeys = userData.getUserData().keySet();
		for(Integer i : empIdKeys){
			usrs.add(userData.getUserData().get(i));
		}
		return usrs;
	}
	
	@RequestMapping(value = UserConstants.CREATE_USER, method = RequestMethod.POST)
	public @ResponseBody User createUser(@RequestBody User usrs) {
		logger.info("Start createUser.");
		//emp.setCreatedDate(new Date());
		if(ValidateuserData.validateData(usrs))
			userData.getUserData().put(usrs.getId(), usrs);
		
		return usrs;
	}
	
	@RequestMapping(value = UserConstants.DELETE_USER, method = RequestMethod.PUT)
	public @ResponseBody User deleteUser(@PathVariable("id") int usrId) {
		logger.info("Start deleteUser.");
		User usr = userData.getUserData().get(usrId);
		userData.getUserData().remove(usrId);
		return usr;
	}
	
}
