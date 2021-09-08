package com.search.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.repository.*;
import com.search.model.*;
import com.search.model.*;
import com.search.service.*;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/flight")
public class SearchController {

	@Autowired
	private SearchService service;

	@GetMapping("/findAll")
	@ApiOperation(value = "Get all flight Details")
	public List<Flight> getAllFlights() {
		return service.getFlights();
	}

	@GetMapping("/findOne/{flightNumber}")
	public Flight getFlightById(@PathVariable(value = "flightNumber") int flightNumber) {

		return service.getFlightById(flightNumber);
	}

	@PostMapping("/searchFlights")
	public List<Flight> getFlightsBySourceAndDestinationAndDepartDate(@RequestBody Flight flight) {

		return service.getFlightsBySourceAndDestinationAndDepartDate(flight.getSource(), flight.getDestination(),
				flight.getDepartDate());
	}
	
	@PostMapping("/searchFlightspro")
	public List<Flight> getFlightsBySourceAndDestinationAndDepartDate(@RequestBody searchByThree flight) {

		return service.getFlightsBySourceAndDestinationAndDepartDate(flight.getSource(), flight.getDestination(),
				flight.getDepartDate());
	}
	
	@PostMapping("/searchFlightsop")
	public List<Flight> getFlightsBySourceAndDestinationAndDepartDate(@RequestBody Search flight) {

		return service.getFlightsBySourceAndDestinationAndDepartDate(flight.getSource(), flight.getDestination(),
				flight.getDepartDate());
	}
	
//	@PostMapping("/searchFlights/search")
//	public List<Flight> getFlightsBySourceAndDestination(@RequestBody searchByTwo flight) {
//
//		return service.getFlightsBySourceAndDestination(flight.getSource(), flight.getDestination());
//	}
	

//	@PutMapping("/update/{id}")
//	public String updateFlightById(@PathVariable(value = "id") int flightId, @RequestBody Flight flight) {
//
//		Flight updatedFlight = service.updateFlight(flightId, flight);
//		return "Updated flight with id :: " + updatedFlight.getFlightNumber();
//	}
	@GetMapping("/search/{source}/{destination}/{date}")
	@ApiOperation(value = "Retrieve the Flight Details by giving source, Destination and date")
	public List<Flight> searchFlight(
	        @PathVariable(value= "source") String source,
	        @PathVariable(value= "destination") String destination,
	        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
	    
	return service.getFlightsBySourceAndDestinationAndDepartDate(source,destination,date);
	}

	@PostMapping("/addFlight")
	@ApiOperation(value = "Adds the new Flights into Database")
	public String addFlight(@RequestBody Flight flight) {
		service.addFlight(flight);
		return "Added Flight";
	}

	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> deleteFlight(@PathVariable(value = "id") int flightId) {
		service.deleteFlightById(flightId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}












//	@PostMapping("/flight/details")
//	public  List<Flight> getFlightDetails(@RequestBody List<Bookings> bookings ) throws ResourceNotFoundException{
//
//		return bookings.stream().map(booking -> {
//			try {
//				Flight flight = services.getFlight(booking.getFlightId());
//				return flight;
//			} catch (Exception e) {
//				return null;
//			}
//			
//		})
//		.collect(Collectors.toList());
//	}
//}
