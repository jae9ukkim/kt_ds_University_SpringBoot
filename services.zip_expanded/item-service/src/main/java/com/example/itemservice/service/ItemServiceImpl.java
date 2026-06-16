package com.example.itemservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemservice.dao.ItemDao;
import com.example.itemservice.vo.CreateItemVO;
import com.example.itemservice.vo.RequestUpdateItemVO;
import com.example.itemservice.vo.ResponseItemVO;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	@Override
	public List<ResponseItemVO> fetchAllItems() {
		// 상품 조회 기능 코드 작성.
		return this.itemDao.selectAllItems();
	}

	@Override
	public ResponseItemVO createItems(CreateItemVO createItemVO) {
		// 상품 등록 기능 코드 작성.
		int createCount = this.itemDao.insertItem(createItemVO);
		if(createCount > 0) {
			return this.itemDao.selectItemById(createItemVO.getItemId());
		}
		return null;
	}

	@Override
	public ResponseItemVO updateItemStock(RequestUpdateItemVO requestUpdateItemVO) {
		ResponseItemVO remainItem = this.itemDao.selectItemByItemId(requestUpdateItemVO.getItemId());
		if(remainItem.getStock() < requestUpdateItemVO.getStock()) {
			return null;
		}
		this.itemDao.updateItemStock(requestUpdateItemVO);
	    return this.itemDao.selectItemByItemId(requestUpdateItemVO.getItemId());

	}


}
