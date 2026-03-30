package org.themoviedb.movie.service;

import org.themoviedb.movie.vo.MovieVO;
import org.themoviedb.movie.vo.request.UpdateVO;
import org.themoviedb.movie.vo.request.WriteVO;
import org.themoviedb.movie.vo.response.SearchResultVO;

public interface MovieService {

    SearchResultVO findMovieAll();

    boolean createNewMovie(WriteVO writeVO);

	MovieVO findMovieDtailByMovieId(String movieId);

	boolean updateMovieByMovieId(UpdateVO updateVO);

	Boolean deleteMovieByMovieId(String movieId);

}
