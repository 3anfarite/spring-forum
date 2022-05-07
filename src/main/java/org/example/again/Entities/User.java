package org.example.again.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
public class User {    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique=true)
	private String username;
	@Email
	@Column(unique = true)
	private String email;
	@Size(min=8)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinTable(name = "USER_ROLES", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "id") })
    private List<Role> roles;	
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Pic> pics; 
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Post> posts; 
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Like> likes; 
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Comment> comments; 
	
	public User() {
	}

	public User(String username,String email, String password,List<Role> roles ) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles=roles;
	}

    public int getId() {
    	return id;
    }
    public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Pic> getPics() {
		return pics;
	}

	public void setPics(List<Pic> pics) {
		this.pics = pics;
	}

	public User(int id) {
		this.id = id;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
