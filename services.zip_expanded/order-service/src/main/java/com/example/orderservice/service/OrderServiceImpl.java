package com.example.orderservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.orderservice.dao.OrderDao;
import com.example.orderservice.service.client.ItemServiceClient;
import com.example.orderservice.vo.RequestOrderVO;
import com.example.orderservice.vo.RequestUpdateItemVO;
import com.example.orderservice.vo.ResponseItemVO;
import com.example.orderservice.vo.ResponseOrderVO;

import feign.FeignException;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ItemServiceClient itemServiceClient;

	@Override
	public ResponseOrderVO createNewOrder(RequestOrderVO requestOrderVO) {
		// 주문 생성 코드 작성.
		this.orderDao.insertNewOrder(requestOrderVO);
		RequestUpdateItemVO requestUpdateItemVO = new RequestUpdateItemVO();
	    requestUpdateItemVO.setItemId(requestOrderVO.getItemId());
	    requestUpdateItemVO.setStock(requestOrderVO.getItemOrderCount());
		
	    try {			
	    	ResponseEntity<ResponseItemVO> responseItem = this.itemServiceClient.updateItemStock(requestOrderVO.getItemId(), requestUpdateItemVO);
	    	logger.info("Item Id: {}",responseItem.getBody().getItemId());
	    	logger.info("Item Name: {}",responseItem.getBody().getItemName());
	    	logger.info("Item Stock: {}",responseItem.getBody().getStock());
		} catch (FeignException fe) {
			logger.error(fe.getMessage(), fe);
			this.orderDao.deleteOrder(requestOrderVO.getOrderId());
		}
		return this.orderDao.selectOneOrderByOrderId(requestOrderVO.getOrderId());
	}

	@Override
	public List<ResponseOrderVO> fetchAllOrdersByUserId(String userId) {
		// 사용자별 주문 내역 조회 코드 작성.
		return this.orderDao.selectAllOrdersByUserId(userId);
	}

	@Override
	public int deleteOrder(String orderId) {		
		return this.orderDao.deleteOrder(orderId);
	}


}
