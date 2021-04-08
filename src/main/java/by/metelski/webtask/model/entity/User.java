package by.metelski.webtask.model.entity;

public class User extends Entity {
	private long userId;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String login;
	private boolean isBlocked;

	public User() {
	}

	public User(long userId, String name, String surname, String email, String phone, String login, boolean isBlocked) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.login = login;
		this.isBlocked = isBlocked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public long getUserId() {
		return userId;
	}
}
