package com.ktdsuniversity.edu.files.helpers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.vo.request.UploadVO;

// upload file handling
@Component
public class MultipartFileHandler {

	private static final Logger logger = LoggerFactory.getLogger(MultipartFileHandler.class);
	
	@Autowired
	private FilesDao filesDao;
	
	public String upload(List<MultipartFile> attachFiles, String fileGroupId) {
		if(attachFiles != null && attachFiles.size() > 0) {
			
			for(int i=0; i < attachFiles.size(); i++) {
				
				if(attachFiles.get(i).isEmpty()) {
					continue;
				}
				
				// UUID ==> 현재 시간을 기준으로 난수화 된 값을 가져오는 방법.
				// 전 세계에서 동시에 발급받더라도 절대로 중복이 일어나지 않는다
				String obfuscateName = UUID.randomUUID().toString();
				
				// 업로드한 파일이 서버컴퓨터의 파일 시스템에 저장되도록 한다.
				File storeFile = new File("C:\\uploadFiles", obfuscateName);
				// C:\\uploadFiles 폴더가 없으면 생성해라!
				if(!storeFile.getParentFile().exists()) {
					storeFile.getParentFile().mkdirs();
				}
				
				try {
					attachFiles.get(i).transferTo(storeFile);
					UploadVO uploadVO = new UploadVO();

					String filename = attachFiles.get(i).getOriginalFilename();
					String ext = filename.substring(filename.lastIndexOf(".") + 1);

//					uploadVO.setFileNum(i + 1);
					// 새롭게 등록이 되는 게시글의 아이디를 지금은 알 수 없다.
					uploadVO.setFileGroupId(fileGroupId); 
					uploadVO.setObfuscateName(obfuscateName);
					uploadVO.setDisplayName(attachFiles.get(i).getOriginalFilename());
					uploadVO.setExtendName(ext);
					// 실제 경로의 파일의 크기
					uploadVO.setFileLength(storeFile.length());
					// 절대경로
					uploadVO.setFilePath(storeFile.getAbsolutePath());
					// FILES 테이블에 첨부파일 데이터를 INSERT
					this.filesDao.insertAttachFile(uploadVO);
				} catch (IllegalStateException | IOException e) {
//					e.printStackTrace();
					logger.error("파일 업로드 중 에러 발생!", e);
				}
				
			}
			
			return fileGroupId;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param attachFiles
	 * @return 첨부파일의 그룹 아이디
	 */
	public String upload(List<MultipartFile> attachFiles) {
		if(attachFiles != null && attachFiles.size() > 0) {
			
			String fileGroupId = this.filesDao.selectNewFileGroupId();
			this.filesDao.insertFileGroupId(fileGroupId);
			this.upload(attachFiles, fileGroupId);
			
			return fileGroupId;
		}
		
		return null;
	}
}
