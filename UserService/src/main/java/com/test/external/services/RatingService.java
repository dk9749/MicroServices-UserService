package com.test.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.entity.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@PostMapping("/ratings")
	Rating createRating(Rating rating);

}
