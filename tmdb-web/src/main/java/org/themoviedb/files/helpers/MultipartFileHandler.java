package org.themoviedb.files.helpers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.themoviedb.files.dao.FilesDao;
import org.themoviedb.files.vo.request.UploadVO;

@Component
public class MultipartFileHandler {

    @Autowired
    private FilesDao filesDao;
    
    public void upload(List<MultipartFile> uploadFiles, String id) {
        
        if(uploadFiles == null || uploadFiles.size() <= 0 ) {
            return ;
        }
        
        for(int i = 0; i < uploadFiles.size(); i++) {
            String obfuscateName = UUID.randomUUID().toString();

            File storeFile = new File("C:\\uploadFilese", obfuscateName);
//            File storeFile = new File("/Users/codemakers/uploadFiles", obfuscateName);
            
            if(!storeFile.getParentFile().exists()) {
                storeFile.getParentFile().mkdirs();
            }
            
            try {
                uploadFiles.get(i).transferTo(storeFile);
                
                String fileName = uploadFiles.get(i).getOriginalFilename();
                String ext = fileName.substring(fileName.indexOf(".") + 1);
                
                UploadVO uploadVO = new UploadVO();
                uploadVO.setFileGroupId(id);
                uploadVO.setDisplayName(uploadFiles.get(i).getOriginalFilename());
                uploadVO.setObfuscateName(obfuscateName);
                uploadVO.setExtendName(ext);
                uploadVO.setFileLength(storeFile.length());
                uploadVO.setFilePath(storeFile.getAbsolutePath());
                
                this.filesDao.insertUploadFile(uploadVO);
                
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            
            
        }
        
    }
}
