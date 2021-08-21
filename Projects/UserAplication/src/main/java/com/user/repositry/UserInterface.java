package com.user.repositry;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.user.userinfo.*;

public interface UserInterface extends MongoRepository<User, String>{

}
