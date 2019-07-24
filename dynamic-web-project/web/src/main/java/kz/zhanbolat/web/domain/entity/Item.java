package kz.zhanbolat.web.domain.entity;

import java.util.Objects;

public class Item implements Entity {
	private Long id;
	private String name;
	private String description;
	private Integer price;
	private Integer categoryId;
	
	private Item() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	
	public static Builder newBuilder() {
		return new Item().new Builder();
	}
	
	public class Builder {
		
		private Builder() {
			
		}
		
		public Builder setId(Long id) {
			Item.this.id = id;
			
			return this;
		}
		
		public Builder setName(String name) {
			Item.this.name = name;
			
			return this;
		}
		
		public Builder setDescription(String description) {
			Item.this.description = description;
			
			return this;
		}
		
		public Builder setPrice(Integer price) {
			Item.this.price = price;
			
			return this;
		}
		
		public Builder setCategoryId(Integer categoryId) {
			Item.this.categoryId = categoryId;
			
			return this;
		}
		
		public Item build() {
			return Item.this;
		}
		
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Item [id=");
		builder2.append(id);
		builder2.append(", name=");
		builder2.append(name);
		builder2.append(", description=");
		builder2.append(description);
		builder2.append(", price=");
		builder2.append(price);
		builder2.append(", categoryId=");
		builder2.append(categoryId);
		builder2.append("]");
		return builder2.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, description, id, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(categoryId, other.categoryId) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price);
	}
	
}
