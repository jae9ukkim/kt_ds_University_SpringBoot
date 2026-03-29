package org.themoviedb.movie.service;

import org.themoviedb.movie.vo.request.WriteVO;
import org.themoviedb.movie.vo.response.SearchResultVO;

public interface MovieService {

    SearchResultVO findMovieAll();

    boolean createNewMovie(WriteVO writeVO);

}
