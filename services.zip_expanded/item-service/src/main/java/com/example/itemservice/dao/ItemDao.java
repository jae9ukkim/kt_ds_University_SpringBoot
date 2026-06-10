package com.example.itemservice.dao;

import java.util.List;

import com.example.itemservice.vo.CreateItemVO;
import com.example.itemservice.vo.ResponseItemVO;

public interface ItemDao {

	List<ResponseItemVO> selectAllItems();
	
	int insertItem(CreateItemVO createItemVO);
	
	ResponseItemVO selectItemById(String id);
}
