package com.educandoweb.course.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.pk.OrderItemPK;

// Nesse caso não precisa da Anotation @Repository, porque já herda de JpaRepository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

	
	
}
