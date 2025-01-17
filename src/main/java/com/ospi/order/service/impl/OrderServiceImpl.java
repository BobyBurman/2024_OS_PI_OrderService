package com.ospi.order.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ospi.order.dto.OrderRequest;
import com.ospi.order.feignClient.InventoryClient;
import com.ospi.order.model.Order;
import com.ospi.order.repository.OrderRepository;
import com.ospi.order.service.OrderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	
	private final InventoryClient inventoryClient;
	
	@Override
	public void placeOrder(OrderRequest orderRequest) {
		
		
		//1. using Mockito
		//2. using Wiremock
		
		var isProductInStock=inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
		
		if(isProductInStock) {
		
		// map orderRequest to JPA entity order
		Order order=new Order();
		
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setPrice(orderRequest.price());
		order.setSkuCode(orderRequest.skuCode());
		order.setQuantity(orderRequest.quantity());
		
		//save order to OrderRepository
		orderRepository.save(order);
		
		}else {
			throw new RuntimeException("Product with SkuCode "+orderRequest.skuCode()+" is not in stock");
		}
		
	}

}
