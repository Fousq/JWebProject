package kz.zhanbolat.web.domain.entity;

import java.util.Objects;

public class User implements Entity {
	private Long id;
	private String username;
	private String password;
	
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
		
		public User build() {
			return User.this;
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, username, password);
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
		return Objects.equals(id, other.id) 
				&& Objects.equals(username, other.username)
				&& Objects.equals(password, other.password);
	}
}
