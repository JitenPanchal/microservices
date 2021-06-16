package io.javabrains.moviecatalogservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.javabrains.moviecatalogservice.dtos.CatalogItem;
import io.javabrains.moviecatalogservice.dtos.Movie;
import io.javabrains.moviecatalogservice.dtos.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	private RestTemplate restTemplate;
	private WebClient.Builder webClientBuilder;

	@Autowired
	public MovieCatalogResource(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
		super();
		this.restTemplate = restTemplate;
		this.webClientBuilder = webClientBuilder;
	}

	@GetMapping(value = "/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		
//		Movie movie1 = webClientBuilder.build().get().uri("http://movie-info-service/movies/1234")
//		.retrieve().bodyToMono(Movie.class).block();
		
//		 Movie movie2 = restTemplate.getForObject("http://movie-info-service/movies/1234", Movie.class);

		UserRatings userRatings = webClientBuilder.build().get()
				.uri("http://ratings-data-service/ratingsdata/users/" + userId).retrieve().bodyToMono(UserRatings.class)
				.block();

		return userRatings.getRatings().stream().map(rating -> {
			// Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" +
			// rating.getMovieId(), Movie.class);

			Movie movie = webClientBuilder.build().get().uri("http://movie-info-service/movies/" + rating.getMovieId())
					.retrieve().bodyToMono(Movie.class).block();

			return new CatalogItem(movie.getName(), "TestDesc", rating.getRating());
		}).collect(Collectors.toList());

	}

}