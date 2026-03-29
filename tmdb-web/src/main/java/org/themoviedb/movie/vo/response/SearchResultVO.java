package org.themoviedb.movie.vo.response;

import java.util.List;

import org.themoviedb.movie.vo.MovieVO;

public class SearchResultVO {
    
    private List<MovieVO> movieLst;
    private int count;
    
    public List<MovieVO> getMovieLst() {
        return this.movieLst;
    }
    public void setMovieLst(List<MovieVO> movieLst) {
        this.movieLst = movieLst;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
}
