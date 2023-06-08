package com.test.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.entity.Hotel;
import com.test.entity.Rating;
import com.test.entity.User;
import com.test.external.services.HotelService;
import com.test.sevice.UserService;

@Service
public class UserServiceImpl implements UserService {
	static List<User> list = new ArrayList<>();

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	HotelService hotelService;

	public UserServiceImpl() {
		User user = new User("101", "DKD", "dkd@gmail.com", "SE", null);
		User user1 = new User("102", "PK", "pk@gmail.com", "AE", null);

		list.add(user);
		list.add(user1);
	}

	@Override
	public User saveUser(User user) {
		String random = UUID.randomUUID().toString();
		user.setId(random);
		list.add(user);
		return user;
	}

	@Override
	public List<User> getAllUser() {
		return list;
	}

	@Override
	public User getUser(String userId) {
		for (User z : list) {
			if (z.getId().equals(userId)) {
				Rating[] ratinglist = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + z.getId(),
						Rating[].class);
				List<Rating> RatingList1 = Arrays.stream(ratinglist).map(rating -> {
//					ResponseEntity<Hotel> forEntity = restTemplate
//							.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//					Hotel hotel = forEntity.getBody();
					Hotel hotel = hotelService.getHotel(rating.getHotelId());
					rating.setHotel(hotel);
					return rating;
				}).collect(Collectors.toList());

				z.setRatings(RatingList1);
				return z;
			}
		}
		return null;
	}

	@Override
	public User deleteUser(String userId) {
		for (User z : list) {
			if (z.getId().equals(userId)) {
				list.remove(z);
				return z;
			}
		}
		return null;
	}

	@Override
	public User updateUser(String userId) {
		for (User z : list) {
			if (z.getId().equals(userId)) {
				z.setName(userId);
				z.setEmail(userId);
				z.setAbout(userId);
				return z;
			}
		}
		return null;
	}

}
