package org.themoviedb.files.service;

import org.themoviedb.files.vo.request.SearchFileVO;
import org.themoviedb.files.vo.response.DownloadVO;

public interface FilesService {

    DownloadVO findFile(SearchFileVO searchFileVO);

}
