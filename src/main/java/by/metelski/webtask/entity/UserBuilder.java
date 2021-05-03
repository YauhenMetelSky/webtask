package by.metelski.webtask.entity;

import java.util.Map;

import by.metelski.webtask.command.ParameterAndAttribute;

public class UserBuilder {
	private final static UserBuilder instance = new UserBuilder();
	
  private UserBuilder() {
	  
  }
  public static UserBuilder getInstance() {
	  return instance;
  }
  
  public User build(Map<String,String> userData) {
	  User user = new User();
	  user.setName(userData.get(ParameterAndAttribute.USER_NAME));
	  user.setSurname(userData.get(ParameterAndAttribute.USER_SURNAME));
	  user.setLogin(userData.get(ParameterAndAttribute.USER_LOGIN));
	  user.setEmail(userData.get(ParameterAndAttribute.USER_EMAIL));
	  user.setPhone(userData.get(ParameterAndAttribute.USER_PHONE));
	  return user;
  }
  
}
