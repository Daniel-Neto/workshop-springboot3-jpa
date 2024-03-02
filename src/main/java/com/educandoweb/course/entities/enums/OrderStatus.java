package com.educandoweb.course.entities.enums;

public enum OrderStatus {
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	// código relacionado ao OrderStatus. Cada status tem um código
	private int code;
	// construtor da classe
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	// método para obter o OrderStatus dado um determinado código
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value: OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
