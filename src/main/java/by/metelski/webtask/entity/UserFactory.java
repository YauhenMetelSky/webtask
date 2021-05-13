package by.metelski.webtask.entity;

import java.util.Map;

import by.metelski.webtask.command.ParameterAndAttribute;

public class UserFactory {
	private final static UserFactory instance = new UserFactory();
	
  private UserFactory() {
	  
  }
  public static UserFactory getInstance() {
	  return instance;
  }
  
  public User build(Map<String,String> userData) {
	  User user = new User();
	  user.setName(userData.get(ParameterAndAttribute.USER_NAME));
	  user.setSurname(userData.get(ParameterAndAttribute.USER_SURNAME));
	  user.setEmail(userData.get(ParameterAndAttribute.USER_EMAIL));
	  user.setPhone(userData.get(ParameterAndAttribute.USER_PHONE));
	  return user;
  }  
}
