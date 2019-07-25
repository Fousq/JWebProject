package kz.zhanbolat.web.domain.entity;

import java.sql.Date;
import java.util.Objects;

public class User implements Entity {
	private Long id;
	private String username;
	private String password;
	private String telephoneNumber;
	private String country;
	private Date birthday;
	
	private User() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	
	public String getCountry() {
		return country;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public static Builder newUser() {
		return new User().new Builder();
	}
	
	public class Builder {
		
		private Builder() {
			
		}
		
		public Builder setId(Long id) {
			User.this.id = id;
			
			return this;
		}
		
		public Builder setUsername(String username) {
			User.this.username = username;
			
			return this;
		}
		
		public Builder setPassword(String password) {
			User.this.password = password;
			
			return this;
		}
		
		public Builder setTelephoneNumber(String telephoneNumber) {
			User.this.telephoneNumber = telephoneNumber;
			
			return this;
		}
		
		public Builder setCountry(String country) {
			User.this.country = country;
			
			return this;
		}
		
		public Builder setBirthday(Date birthday) {
			User.this.birthday = birthday;
			
			return this;
		}
		
		public User build() {
			return User.this;
		}
		
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", telephoneNumber="
				+ telephoneNumber + ", country=" + country + ", birthday=" + birthday + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, country, id, password, telephoneNumber, username);
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
		return Objects.equals(birthday, other.birthday) && Objects.equals(country, other.country)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(telephoneNumber, other.telephoneNumber) && Objects.equals(username, other.username);
	}

	
}
