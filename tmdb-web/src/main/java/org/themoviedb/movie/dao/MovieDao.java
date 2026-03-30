package org.themoviedb.movie.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.themoviedb.movie.vo.MovieVO;
import org.themoviedb.movie.vo.request.UpdateVO;
import org.themoviedb.movie.vo.request.WriteVO;

@Mapper
public interface MovieDao {

    int selectMovieCount();

    List<MovieVO> selectMovieList();

    int insertNewMovie(WriteVO writeVO);

	MovieVO selectMovieByMovieId(String movieId);

	int updateMovieByMovieId(UpdateVO updateVO);

	int deleteMovieByMovieId(String movieId);

}
