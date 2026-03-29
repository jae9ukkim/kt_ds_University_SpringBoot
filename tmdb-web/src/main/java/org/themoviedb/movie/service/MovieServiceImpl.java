package org.themoviedb.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.themoviedb.movie.dao.MovieDao;
import org.themoviedb.movie.vo.MovieVO;
import org.themoviedb.movie.vo.request.WriteVO;
import org.themoviedb.movie.vo.response.SearchResultVO;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;
    
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
        return insertCount == 1;
    }

}
