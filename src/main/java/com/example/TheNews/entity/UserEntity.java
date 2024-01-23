package com.example.TheNews.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long user_id;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;

    @Column(name = "role")
	private String role;

	@Column(name = "username")
	private String username;

    @Column(name = "password")
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_l")
	private List<Like> likes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_a")
	private List<Article> articles;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_c")
	private List<Comment> comments;


	public UserEntity(String first_name, String last_name, String role, String username, String password) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return user_id;
	}

	public String getFirstName() {
		return first_name;
	}

	public String getLastName() {
		return last_name;
	}

		public String getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + user_id + 
		", first name=" + first_name + ", last name=" + last_name + 
		", role=" + role + ", username=" + username +"]";
	}
}
