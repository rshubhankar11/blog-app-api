package fun.kananika.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @Author Rabiroshan Shubhankar
 * 
 * This is the entity class fo User 
 * 
 * */

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "user_name",nullable = false)
	private String name;
	
	@Column(name = "user_email", nullable = false)
	private String email;
	
	@Column(name = "user_password", nullable = false)
	private String password;
	
	@Column(name = "user_about")
	private String about;
	
}
