package com.educandoweb.course.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Order;

// Nesse caso não precisa da Anotation @Repository, porque já herda de JpaRepository
public interface OrderRepository extends JpaRepository<Order, Long>{

	
	
}
