package com.example.orderservice.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderservice.exceptions.OrderException;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrderVO;
import com.example.orderservice.vo.ResponseOrderVO;

import feign.FeignException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order-service")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@PostMapping("/{userId}/orders")
	public ResponseEntity<ResponseOrderVO> makeNewOrder(@PathVariable String userId,
			@RequestHeader("USER_ID") String authUserId,
			@RequestBody @Valid RequestOrderVO requestOrderVO, BindingResult bindingResult) {
		if(!userId.equals(authUserId)) {
			//Method Not Allowed
			return new ResponseEntity<>(HttpStatusCode.valueOf(405));
		}
		// 주문 생성 코드 작성.
		if(bindingResult.hasErrors()) {
			throw new OrderException(bindingResult.getFieldErrors());
		}
		requestOrderVO.setUserId(userId);
		ResponseOrderVO newOrder = this.orderService.createNewOrder(requestOrderVO);
		
		return new ResponseEntity<ResponseOrderVO>(newOrder, HttpStatusCode.valueOf(201));
	}

	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<ResponseOrderVO>> fetchAllOrders(@PathVariable String userId, @RequestHeader("USER_ID") String authUserId) {
		if(!userId.equals(authUserId)) {
			//Method Not Allowed
			return new ResponseEntity<>(HttpStatusCode.valueOf(405));
		}
		
		// 사용자별 주문 내역 조회 코드 작성.
		List<ResponseOrderVO> usersOrderList = this.orderService.fetchAllOrdersByUserId(userId);
		return new ResponseEntity<>(usersOrderList, HttpStatus.OK);
	}

}
