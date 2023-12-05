package net.javaguides.springboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles") // Specify the collection name for MongoDB
public class Role {

	@Id
	private String id; // Use String as the ID type for MongoDB

	private String name;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
