package kz.zhanbolat.web.domain.entity;

import java.util.Date;

public class Record implements Entity {
	private Long id;
	private boolean active;
	private Date createdAt;
	private Long userId;
	private Long itemId;
	
	private Record() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getItemId() {
		return itemId;
	}
	
	public static Builder newBuilder() {
		return new Record().new Builder();
	}
	
	public class Builder {
		private Builder() {
			
		}
		
		public Builder setId(Long id) {
			Record.this.id = id;
			
			return this;
		}
		
		public Builder setActive(boolean active) {
			Record.this.active = active;
			
			return this;
		}
		
		public Builder setCreateAt(Date createdAt) {
			Record.this.createdAt = createdAt;
			
			return this;
		}
		
		public Builder setUserId(Long userId) {
			Record.this.userId = userId;
			
			return this;
		}
		
		public Builder setItemId(Long itemId) {
			Record.this.itemId = itemId;
			
			return this;
		}
		
		public Record build() {
			return Record.this;
		}
		
	}
	
}
