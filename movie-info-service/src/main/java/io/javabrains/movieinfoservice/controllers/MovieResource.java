package io.javabrains.movieinfoservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.movieinfoservice.dtos.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {

//	, consumes = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE }
	@GetMapping(value = "/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

		if ("1234".equalsIgnoreCase(movieId)) {
			return new Movie(movieId, "Movie 1234");
		} else if ("5678".equalsIgnoreCase(movieId)) {
			return new Movie(movieId, "Movie 5678");
		} else {
			return new Movie(movieId, "Movie Not found");
		}
	}
}
