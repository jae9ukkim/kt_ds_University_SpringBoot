package org.themoviedb.movie.vo.request;

public class UpdateVO extends WriteVO{

	private String movieId;

	public String getMovieId() {
		return this.movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
}
