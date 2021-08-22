package com.shopping.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shopping.userservice.model.UserProfile;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
	public UserProfile getUserByEmail(String email);
	public UserProfile getUserByMobileNo(String mobileNo);

}
