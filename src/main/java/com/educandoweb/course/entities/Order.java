package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	// Recebe o valor referente ao status do pedido
	private Integer orderStatus;
	
	private Double total;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

	// Quando se tem uma relação Muitos para Um, o JPA carrega por padrão o objeto
	// "Um" para
	// o objeto Muitos. Nesse caso, se eu chamar Order, por padrão carrega o User.
	// Isso se chama lazy loading.
	@ManyToOne
	@JoinColumn(name = "client_id") // nome da chave estrangeira nessa table. Chave do cliente
	private User client;
	
	// 1 Order possui Muitos OrderItem
	// "id.order" porque a gente acessa o id de OrderItemPk
	// e acessa o objeto "order" de OrderItemPk
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)  // Essa notação serve para indicar que o payment e o Order vão ter sempre o mesmo id. Ex: Order = 5, Payment = 5
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment, User client, OrderStatus orderStatus, Double total) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
		this.total = total;
		setOrderStatus(orderStatus);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
			};
	}
	
	
	public Set<OrderItem> getItems(){
		return items;
	}
	
	public Double getOrderTotal() {
		double sum = 0.0;
		for(OrderItem o : items) {
			sum += o.getSubTotal();
		}
		return sum;
	}
	

	public void setTotal(Double total) {
		this.total = total;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

}
