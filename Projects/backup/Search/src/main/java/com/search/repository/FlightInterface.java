package com.search.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.search.model.Flight;

public interface FlightInterface extends MongoRepository<Flight, Integer> {
	
	List<Flight> findBySourceAndDestinationAndDepartDate(String source,String destination,LocalDate date );
	List<Flight> findBySourceAndDestinationAndDepartDate(String source,String destination,String date );

	List<Flight> findBySourceAndDestination(String source,String destination);


}
