package com.example.orderservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.orderservice.vo.RequestUpdateItemVO;
import com.example.orderservice.vo.ResponseItemVO;



@FeignClient(name = "ITEM-SERVICE", fallback = com.example.orderservice.service.client.ItemServiceClient.ItemFallback.class)
public interface ItemServiceClient {

	@PutMapping("/item-service/{itemId}/items")
	public ResponseEntity<ResponseItemVO> updateItemStock(@PathVariable String itemId, 
	          @RequestBody RequestUpdateItemVO requestUpdateItemVO);

	// Feign Fallback(Circuit Breaker)
	@Component
	public class ItemFallback implements ItemServiceClient {

		@Override
		public ResponseEntity<ResponseItemVO> updateItemStock(String itemId, RequestUpdateItemVO requestUpdateItemVO) {
			return null;
		}
		
	}

}
