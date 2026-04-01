package org.themoviedb.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.themoviedb.files.helpers.MultipartFileHandler;
import org.themoviedb.movie.dao.MovieDao;
import org.themoviedb.movie.vo.MovieVO;
import org.themoviedb.movie.vo.request.UpdateVO;
import org.themoviedb.movie.vo.request.WriteVO;
import org.themoviedb.movie.vo.response.SearchResultVO;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;
    
    @Autowired
    private MultipartFileHandler multipartFileHandler;
    
    @Override
    public SearchResultVO findMovieAll() {
        
        SearchResultVO searchResult = new SearchResultVO();
        
        int count = this.movieDao.selectMovieCount();
        searchResult.setCount(count);
        
        if(count == 0) {
            return searchResult;            
        }
        
        List<MovieVO> list = this.movieDao.selectMovieList();
        searchResult.setMovieLst(list);
        
        return searchResult;
    }

    @Override
    public boolean createNewMovie(WriteVO writeVO) {
        int insertCount =  this.movieDao.insertNewMovie(writeVO);
        // 파일 업로드
        this.multipartFileHandler.upload(writeVO.getPosterFiles(), writeVO.getMovieId());
        
        return insertCount == 1;
    }

	@Override
	public MovieVO findMovieDtailByMovieId(String movieId) {

		MovieVO movie = this.movieDao.selectMovieByMovieId(movieId);
		
		return movie;
	}

	@Override
	public boolean updateMovieByMovieId(UpdateVO updateVO) {
		int updateCount = this.movieDao.updateMovieByMovieId(updateVO);
		return updateCount == 1;
	}

	@Override
	public Boolean deleteMovieByMovieId(String movieId) {
		
		int deleteCount = this.movieDao.deleteMovieByMovieId(movieId);
		
		return deleteCount == 1;
	}

}
