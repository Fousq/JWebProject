package kz.zhanbolat.entity;

import java.util.Objects;

public class User implements Entity {
	private Long id;
	private String username;
	private String password;
	
	public User() {
		
	}
	
	public User(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
