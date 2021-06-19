package by.metelski.webtask.entity;

/**
 * Entity class User
 * @author Yauhen Metelski
 *
 */
public class User {
	private long userId;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private boolean isBlocked;
	private Role role;

	public enum Role {
		ADMIN, GUEST, USER, DOCTOR
	}

	private User() {

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

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public long getUserId() {
		return userId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (isBlocked ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (isBlocked != other.isBlocked)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role != other.role)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", isBlocked=");
		builder.append(isBlocked);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}

	public static class Builder {
		private User newUser;

		public Builder() {
			newUser = new User();
		}

		public Builder setUserID(long userId) {
			newUser.userId = userId;
			return this;
		}

		public Builder setName(String name) {
			newUser.name = name;
			return this;
		}

		public Builder setSurname(String surname) {
			newUser.surname = surname;
			return this;
		}

		public Builder setEmail(String email) {
			newUser.email = email;
			return this;
		}

		public Builder setPhone(String phone) {
			newUser.phone = phone;
			return this;
		}

		public Builder setIsBlocked(boolean isBlocked) {
			newUser.isBlocked = isBlocked;
			return this;
		}

		public Builder setRole(Role role) {
			newUser.role = role;
			return this;
		}

		public User build() {
			return newUser;
		}
	}
}
