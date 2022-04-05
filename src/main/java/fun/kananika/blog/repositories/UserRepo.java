package fun.kananika.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fun.kananika.blog.entity.User;

/*
 *@Author Rabiroshan Shubhankar 
 * 
 */


public interface UserRepo extends JpaRepository<User, Integer>{

}
