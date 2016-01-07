package com.wg.common;

import com.wg.bean.User;

public class Config {
	public static User user;
	public static Boolean Status;
	public enum Passport {
		SESSION_NAME_LOGIN_RESULT {			
			public String getIt(){return user.getUsername()+','+Status.toString();}
			
		};
		public abstract String getIt();
    }
}
