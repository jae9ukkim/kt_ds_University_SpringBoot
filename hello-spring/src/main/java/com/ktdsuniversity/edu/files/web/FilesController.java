package com.ktdsuniversity.edu.files.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.files.service.FilesService;

@Controller
public class FilesController {

	@Autowired
	private FilesService filesService;
	
	@GetMapping("/file/{fileGroupId}/{fileNum}")
	public MultipartFile doDownloadFileAction(@PathVariable String fileGroupId, @PathVariable String fileNum) {
		
		return null;
	}
	
}
