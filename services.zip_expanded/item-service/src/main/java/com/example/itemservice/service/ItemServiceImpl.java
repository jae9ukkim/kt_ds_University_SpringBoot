package com.example.itemservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemservice.dao.ItemDao;
import com.example.itemservice.vo.CreateItemVO;
import com.example.itemservice.vo.ResponseItemVO;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	@Override
	public List<ResponseItemVO> fetchAllItems() {
		// TODO 상품 조회 기능 코드 작성.
		return null;
	}

	@Override
	public ResponseItemVO createItems(CreateItemVO createItemVO) {
		// TODO 상품 등록 기능 코드 작성.
		return null;
	}


}
