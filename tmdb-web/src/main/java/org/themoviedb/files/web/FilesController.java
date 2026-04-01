package org.themoviedb.files.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.themoviedb.files.service.FilesService;
import org.themoviedb.files.vo.request.SearchFileVO;
import org.themoviedb.files.vo.response.DownloadVO;

@Controller
public class FilesController {
    
    @Autowired
    private FilesService filesService;

private Map<String, String> mimeTypeMap;
    
    public FilesController() {
        this.mimeTypeMap = new HashMap<>();
        this.mimeTypeMap.put("txt", "text/plain");
        // Images
        this.mimeTypeMap.put("jpg", "image/jpeg");
        this.mimeTypeMap.put("jpeg", "image/jpeg");
        this.mimeTypeMap.put("png", "image/png");
        this.mimeTypeMap.put("webp", "image/webp");
        this.mimeTypeMap.put("png", "image/png");
        this.mimeTypeMap.put("gif", "image/gif");
        this.mimeTypeMap.put("svg", "image/svg");
        
        //Static Resources
        this.mimeTypeMap.put("css", "text/css");
        this.mimeTypeMap.put("js", "text/javascript");
        this.mimeTypeMap.put("html", "text/html");
        
        // MS Office
        this.mimeTypeMap.put("csv", "text/csv");
        this.mimeTypeMap.put("xls", "application/vnd.ms-excel");
        this.mimeTypeMap.put("xls", "application/vnd.ms-excel");
        this.mimeTypeMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        this.mimeTypeMap.put("ppt", "application/vnd.ms-powerpoint");
        this.mimeTypeMap.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        
        // Archive
        this.mimeTypeMap.put("zip", "application/zip");
        
        // Document
        this.mimeTypeMap.put("pdf", "application/pdf");
        
    }
    
    @GetMapping("/file/{fileGroupId}/{fileNum}")
    public ResponseEntity<Resource> doDownloadAction(@PathVariable String fileGroupId, @PathVariable int fileNum) {

        SearchFileVO searchFileVO = new SearchFileVO();
        searchFileVO.setFileGroupId(fileGroupId);
        searchFileVO.setFileNum(fileNum);
        
        DownloadVO downloadVO = this.filesService.findFile(searchFileVO); 
        
        HttpHeaders headers = new HttpHeaders(); 
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadVO.getDisplayName());
        headers.set(HttpHeaders.CONTENT_LENGTH, downloadVO.getFileLength() + "");
        headers.set(HttpHeaders.CONTENT_TYPE, this.mimeTypeMap.getOrDefault(downloadVO.getExtendName().toLowerCase(), "application/octet-stream"));
        
        return ResponseEntity.ok().headers(headers).body(downloadVO.getResource());
    }
    
}
