package com.example.orderservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrderVO;
import com.example.orderservice.vo.ResponseOrderVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order-service")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/{userId}/orders")
	public ResponseEntity<ResponseOrderVO> makeNewOrder(@PathVariable String userId,
			@RequestBody @Valid RequestOrderVO requestOrderVO, BindingResult bindingResult) {
		// TODO 주문 생성 코드 작성.
		return null;
	}

	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<ResponseOrderVO>> fetchAllOrders(@PathVariable String userId) {
		// TODO 사용자별 주문 내역 조회 코드 작성.
		return null;
	}

}
