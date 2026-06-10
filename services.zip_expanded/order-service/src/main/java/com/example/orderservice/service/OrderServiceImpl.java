package com.example.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orderservice.dao.OrderDao;
import com.example.orderservice.vo.RequestOrderVO;
import com.example.orderservice.vo.ResponseOrderVO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public ResponseOrderVO createNewOrder(RequestOrderVO requestOrderVO) {
		// TODO 주문 생성 코드 작성.
		return null;
	}

	@Override
	public List<ResponseOrderVO> fetchAllOrdersByUserId(String userId) {
		// TODO 사용자별 주문 내역 조회 코드 작성.
		return null;
	}


}
