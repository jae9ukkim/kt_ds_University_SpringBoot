package com.example.itemservice.web;

import com.example.itemservice.exceptions.ItemException;
import com.example.itemservice.exceptions.handler.ItemExceptionHandler;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.itemservice.service.ItemService;
import com.example.itemservice.vo.CreateItemVO;
import com.example.itemservice.vo.RequestUpdateItemVO;
import com.example.itemservice.vo.ResponseItemVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/item-service")
public class ItemController {

	private final ItemExceptionHandler itemExceptionHandler;
	@Autowired
	private ItemService itemService;

	ItemController(ItemExceptionHandler itemExceptionHandler) {
		this.itemExceptionHandler = itemExceptionHandler;
	}

	@GetMapping("/items")
	public ResponseEntity<List<ResponseItemVO>> getAllItems() {
		// 상품 조회 코드 작성.
		List<ResponseItemVO> itemList = this.itemService.fetchAllItems();
		return new ResponseEntity<List<ResponseItemVO>>(itemList, HttpStatusCode.valueOf(200));
	}
	
	@PostMapping("/items")
	public ResponseEntity<ResponseItemVO> createItems(@Valid @RequestBody CreateItemVO createItemVO, BindingResult bindingResult) {
		// 상품 등록 코드 작성.
		if(bindingResult.hasErrors()) {
			throw new ItemException(bindingResult.getFieldErrors());
		}
		ResponseItemVO responseItem = this.itemService.createItems(createItemVO);
		return new ResponseEntity<ResponseItemVO>(responseItem, HttpStatusCode.valueOf(201));
	}
	
	@PutMapping("/{itemId}/items")
	  public ResponseEntity<? extends Object> updateItemStock(@PathVariable String itemId, 
	          @RequestBody RequestUpdateItemVO requestUpdateItemVO) {
	    requestUpdateItemVO.setItemId(itemId);
	    ResponseItemVO item = this.itemService.updateItemStock(requestUpdateItemVO);
	    if(item == null) {
	    	return new ResponseEntity<String>("재고가 부족합니다.", HttpStatus.valueOf(400));
	    }
	    return new ResponseEntity<ResponseItemVO>(item, HttpStatus.OK);
	  }


}
