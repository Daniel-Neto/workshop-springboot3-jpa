package com.educandoweb.course.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Category;

// Nesse caso não precisa da Anotation @Repository, porque já herda de JpaRepository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	
	
}
