package com.test.sevice;

import java.util.List;

import com.test.entity.User;

public interface UserService {

	User saveUser(User user);

	List<User> getAllUser();

	User getUser(String userId);

	User deleteUser(String userId);

	User updateUser(String userId);
}
