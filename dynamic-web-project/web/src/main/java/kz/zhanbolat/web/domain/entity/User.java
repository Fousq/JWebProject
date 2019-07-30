package kz.zhanbolat.web.domain.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.exception.InvalidValueException;
import kz.zhanbolat.web.domain.validator.UserValidator;

public class User implements Entity {
	private static Logger logger = LogManager.getLogger(User.class);
	private Long id;
	private String username;
	private String password;
	private String telephoneNumber;
	private String country;
	private Date birthday;
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
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
	
	public String getFormatedBirthday() {
		logger.debug(birthday);
		logger.debug(format.format(birthday));
		return format.format(birthday);
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
		
		public Builder setPassword(String password) throws InvalidValueException {
			if (UserValidator.validatePassword(password)) {
				User.this.password = password;
			} else {
				throw new InvalidValueException("Password is not valid.");
			}
			
			return this;
		}
		
		public Builder setTelephoneNumber(String telephoneNumber) throws InvalidValueException {
			if (!(UserValidator.validateTelephoneNumber(telephoneNumber) 
					|| telephoneNumber.isEmpty())) {
				throw new InvalidValueException("Telephone number is not valid.");
			}
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
		
		public Builder setBirthday(String birthday) {
			try {
				User.this.birthday = format.parse(birthday);
			} catch (ParseException e) {
				logger.error("Cannot parse the string to a birthday "
						+ "format(day-month-year). Birthday will be setted "
						+ "to today date.", e);
				User.this.birthday = new Date();
			}
			
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
