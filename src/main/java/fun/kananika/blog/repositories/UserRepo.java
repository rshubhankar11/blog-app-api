package fun.kananika.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fun.kananika.blog.entity.User;

/*
 *@Author Rabiroshan Shubhankar 
 * 
 */


public interface UserRepo extends JpaRepository<User, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "delete from users where user_name = :name", nativeQuery = true)
	void deleteByName(@Param("name") String name);

}
