package net.javaguides.springboot.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document(collection = "users") // Specify the collection name for MongoDB
public class User {

	@Id
	private ObjectId _id; // Use String as the ID type for MongoDB

	private String firstName;
	private String lastName;
	private String email;
	private String password;

	private Collection<Role> roles;

	public User() {
	}

	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public ObjectId getId() {
		return _id;
	}

	public void setId(ObjectId id) {
		this._id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
