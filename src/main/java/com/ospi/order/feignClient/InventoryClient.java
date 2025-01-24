package com.ospi.order.feignClient;


import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Slf4j
//@FeignClient(value="inventory", url="${inventory.url}")
public interface InventoryClient {
	
	  Logger log = org.slf4j.LoggerFactory.getLogger(InventoryClient.class);
	
	//@RequestMapping(value="/api/inventory", method=RequestMethod.GET)
	@GetExchange("/api/inventory")
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
	public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
	
	
	default boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
        return false;
    }
	
}
