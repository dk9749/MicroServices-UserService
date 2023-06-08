package com.test.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.entity.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
	@GetMapping("/hotels/{id}")
	Hotel getHotel(@PathVariable String id);
}
