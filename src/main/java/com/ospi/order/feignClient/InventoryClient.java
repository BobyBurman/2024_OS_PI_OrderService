package com.ospi.order.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="inventory", url="${inventory.url}")
public interface InventoryClient {
	
	@RequestMapping(value="/api/inventory", method=RequestMethod.GET)
	public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
	
}
