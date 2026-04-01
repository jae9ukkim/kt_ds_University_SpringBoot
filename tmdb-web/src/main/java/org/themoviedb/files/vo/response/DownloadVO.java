package org.themoviedb.files.vo.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

public class DownloadVO {

    private String displayName;
    private String extendName;
    private Long fileLength;
    private String filePath;

    // 사용자에게 전달해 줄 파일 객체
    private File file;
    
    // 브라우저에게 전달하기 위한 파일 객체
    private Resource resource;

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        try {
            this.displayName = URLEncoder.encode(displayName, "UTF-8");
        } catch (UnsupportedEncodingException e) {}
    }

    public String getExtendName() {
        return this.extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }

    public Long getFileLength() {
        return this.fileLength;
    }

    public void setFileLength(Long fileLength) {
        this.fileLength = fileLength;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        
        this.file = new File(this.filePath);
        try {
            this.resource = new InputStreamResource(new FileInputStream(this.file));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public File getFile() {
        return this.file;
    }

    public Resource getResource() {
        return this.resource;
    }
    
}
