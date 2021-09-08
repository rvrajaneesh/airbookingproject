package com.bookingMicroservice.Repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bookingMicroservice.Model.Bookings;



@Repository
public interface BookingInterface extends MongoRepository<Bookings, String>{
	
	List<Bookings> findAllByUserEmail(String email);
}
