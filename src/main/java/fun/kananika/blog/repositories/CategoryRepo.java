package fun.kananika.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fun.kananika.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
