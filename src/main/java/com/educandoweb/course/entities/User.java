package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Serializable serve para transformar o objeto em cadeias de bytes, pra que ele trafegue
// na rede.
@Entity
@Table(name="tb_user") // serve para não dar conflito com a palavra reservada User que já existe // no banco H2				
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id // identifica que a coluna Id é o id da table User
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento do banco de dados
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	// Isso serve para obter os Users
	@JsonIgnore // serve para evitar um loop infinito entre as entidades Cliente e Order
	@OneToMany(mappedBy="client")  // aqui a gente passa o atributo client da class Order, isso serve para informar o objeto que está guardando os Orders na classe Order
	private List<Order> orders = new ArrayList<>(); // já instancia a lista de pedidos
	
	public User() {}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
