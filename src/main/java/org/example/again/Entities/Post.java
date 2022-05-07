package org.example.again.Entities;

import java.util.Date;
import java.util.List;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Table
public class Post {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private User user;


	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<Pic> pics;

	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Comment> comments; 

	@NotEmpty(message="can't be empty")
	private String content;

	@Column( updatable = false, nullable = false)
	private Date creationDate;
	
	@OneToMany(mappedBy = "post", cascade=CascadeType.ALL)
	private List<Like> likes; 

    
	public Post() {
	}

	public Post(String content) {
		this.content = content;
	}

	public Post(User user,Date creationDate, String content) {

		this.user = user;
		this.creationDate = creationDate;
		this.content = content;
	}

	public Post(User user, String content) {
		this.user = user;
		this.content = content;
	}

	public Post(int id, User user) {
		super();
		this.id = id;
		this.user= user;
	}

	public Post( User user) {
		super();
		this.user= user;
	}
	public Post(Integer id) {
		this.id=id;
	}

	@PrePersist
	protected void onCreate() {
		this.creationDate = new Date();
	}

	//		@PreUpdate
	//		protected void onUpdate() {
	//			this.lastUpdateDate = new Date();
	//		}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Pic> getPics() {
		return pics;
	}

	public void setPics(List<Pic> pics) {
		this.pics = pics;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}


	
}
