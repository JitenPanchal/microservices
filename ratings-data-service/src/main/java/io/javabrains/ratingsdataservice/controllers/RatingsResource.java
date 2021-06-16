package io.javabrains.ratingsdataservice.controllers;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingsdataservice.dtos.Rating;
import io.javabrains.ratingsdataservice.dtos.UserRatings;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

//	, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
	@GetMapping(value = "/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@GetMapping(value = "/users/{userId}")
	public UserRatings getUserRating(@PathVariable("userId") String userId) {
		UserRatings userRatings = new UserRatings();
		userRatings.setRatings(Arrays.asList(new Rating("1234", 4), new Rating("5678", 3)));
		return userRatings;
	}
}