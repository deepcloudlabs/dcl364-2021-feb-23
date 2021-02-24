package com.example.imdb.api;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.example.imdb.entity.Genre;
import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@Path("/movies")
@ApplicationScoped
public class ImdbRestApi {

	@Inject
	private MovieService movieService;
	
	// http://localhost:7100/imdb-api/api/v1/movies/genres
	@GET
	@Path("/genres")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Genre> getAllGenres(){
		return movieService.findAllGenres();
	}
	
	// http://localhost:7100/imdb-api/api/v1/movies?genre=Drama&fromYear=1970&toYear=1979
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public Collection<Movie> findMovies(
			@QueryParam("genre") String genre, 
			@QueryParam("fromYear") int fromYear,
			@QueryParam("toYear") int toYear){
		return movieService.findAllMoviesByYearRangeAndGenre(genre, fromYear, toYear);
	}
}
