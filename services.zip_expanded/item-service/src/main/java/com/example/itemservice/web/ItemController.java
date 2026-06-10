package com.example.itemservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.itemservice.service.ItemService;
import com.example.itemservice.vo.CreateItemVO;
import com.example.itemservice.vo.ResponseItemVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/item-service")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/items")
	public ResponseEntity<List<ResponseItemVO>> getAllItems() {
		// TODO 상품 조회 코드 작성.
		return null;
	}
	
	@PostMapping("/items")
	public ResponseEntity<ResponseItemVO> createItems(@Valid @RequestBody CreateItemVO createItemVO, BindingResult bindingResult) {
		// TODO 상품 등록 코드 작성.
		return null;
	}

}
