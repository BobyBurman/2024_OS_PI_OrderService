package com.ospi.order.service;

import com.ospi.order.dto.OrderRequest;

public interface OrderService {
	
	public void placeOrder(OrderRequest orderRequest);
}
