package kz.zhanbolat.web.domain.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Record implements Entity {
	private static Logger logger = LogManager.getLogger(Record.class);
	private Long id;
	private boolean active;
	private LocalDate createdAt;
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
	
	public LocalDate getCreatedAt() {
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
		
		public Builder setCreateAt(LocalDate createdAt) {
			Record.this.createdAt = createdAt;
			
			return this;
		}
		
		public Builder setCreateAt(String createdAt) {
			try {
				Record.this.createdAt = LocalDate.parse(createdAt);
			} catch (DateTimeParseException e) {
				logger.error("Cannot parse the string to a createAt "
						+ "format(year-month-day)."
						+ " CreateAt will be setted to today date.", e);
				Record.this.createdAt = LocalDate.now();
			}
			
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

	@Override
	public String toString() {
		return "Record [id=" + id + ", active=" + active + ", createdAt=" + createdAt + ", userId=" + userId
				+ ", itemId=" + itemId + "]";
	}
	
}
