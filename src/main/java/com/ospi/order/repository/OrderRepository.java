package com.ospi.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ospi.order.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
