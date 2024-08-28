package com.savleen.apidemo1.repository;

import com.savleen.apidemo1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface categoryRepo extends JpaRepository<Category,Long> {
    Category findByTitle(String title); //if category exists
    Category save(Category category); //if doesnt, create new category
    List<Category> findAll();
    @Override
    Optional<Category> findById(Long id);
}
