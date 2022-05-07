package org.example.again.Entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
      private int id ;
     
	private String name;
	
	@ManyToMany(mappedBy = "roles")
    private List< User > users;
	
	public Role() {
	}

	public Role(List<User> users ,int id, String name) {
		super();
		this.name = name;
		 this.id=id;
		this.users=users;
	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}
	

}
