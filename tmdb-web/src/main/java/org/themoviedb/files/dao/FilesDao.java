package org.themoviedb.files.dao;

import org.apache.ibatis.annotations.Mapper;
import org.themoviedb.files.vo.request.SearchFileVO;
import org.themoviedb.files.vo.request.UploadVO;
import org.themoviedb.files.vo.response.DownloadVO;

@Mapper
public interface FilesDao {

    int insertUploadFile(UploadVO uploadVO);

    DownloadVO selectFileByGroupIdAndFileNum(SearchFileVO searchFileVO);

}
