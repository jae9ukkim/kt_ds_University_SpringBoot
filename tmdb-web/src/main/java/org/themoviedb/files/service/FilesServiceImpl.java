package org.themoviedb.files.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.themoviedb.files.dao.FilesDao;
import org.themoviedb.files.vo.request.SearchFileVO;
import org.themoviedb.files.vo.response.DownloadVO;

@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesDao filesDao;
    
    @Override
    public DownloadVO findFile(SearchFileVO searchFileVO) {
        
        return filesDao.selectFileByGroupIdAndFileNum(searchFileVO);
    }

}
