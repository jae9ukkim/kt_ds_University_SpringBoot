package com.ktdsuniversity.edu.files.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.files.vo.request.SearchFileGroupVO;
import com.ktdsuniversity.edu.files.vo.request.SearchFileVO;
import com.ktdsuniversity.edu.files.vo.request.UploadVO;
import com.ktdsuniversity.edu.files.vo.response.DownloadVO;

@Mapper
public interface FilesDao {

	int insertAttachFile(UploadVO uploadVO);

	DownloadVO selectFilesByGroupIdAndFileNum(SearchFileVO searchFileVO);

	List<String> selectFilePathByFileGroupIdAndFileNums(SearchFileGroupVO updateVO);

	int deleteFilesByFileGroupIdAndFileNums(SearchFileGroupVO updateVO);

	List<String> selectFilePathByFileGroupId(String id);

	int deleteFilesByFileGroupId(String id);

	String selectNewFileGroupId();

	int insertFileGroupId(String fileGroupId);

}
