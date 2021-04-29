package by.metelski.webtask.entity;

import java.util.Map;

import by.metelski.webtask.command.RequestParameter;

public class UserBuilder {
	private final static UserBuilder instance = new UserBuilder();
	
  private UserBuilder() {
	  
  }
  public static UserBuilder getInstance() {
	  return instance;
  }
  
  public User build(Map<String,String> userData) {
	  User user = new User();
	  user.setName(userData.get(RequestParameter.USER_NAME));
	  user.setSurname(userData.get(RequestParameter.USER_SURNAME));
	  user.setLogin(userData.get(RequestParameter.USER_LOGIN));
	  user.setEmail(userData.get(RequestParameter.USER_EMAIL));
	  user.setPhone(userData.get(RequestParameter.USER_PHONE));
	  return user;
  }
  
}
