package io.javabrains.ratingsdataservice.dtos;

import java.util.List;

public class UserRatings {

	private List<Rating> ratings;

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

}
