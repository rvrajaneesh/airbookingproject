package com.bookingMicroservice.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bookingMicroservice.Exception.ResourceNotFoundException;
import com.bookingMicroservice.Model.BookingDetails;
import com.bookingMicroservice.Service.Services;
import com.bookingMicroservice.Model.Bookings;
import com.bookingMicroservice.Model.Flight;

@RestController
@RequestMapping("/booking")
public class Controller {

	@Autowired
	private Services services;
	
	@Autowired
	BookingDetails bookingDetails;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/bookings")
	public List<Bookings> getBookings() {
		return services.getBookings();
	}
	//get booking by bookingId
	@GetMapping("/booking/{id}")
	public ResponseEntity<Bookings> getBookingById(@PathVariable(value = "id") String bookingId)
	throws ResourceNotFoundException{
		Bookings booking = services.getBookings(bookingId);
		return ResponseEntity.ok().body(booking);
	}
	
//booking details by email id
	@GetMapping("/bookingDetails/{id}")
	public ResponseEntity <List<BookingDetails>> getBookingsById(@PathVariable(value = "id") String email)
	throws ResourceNotFoundException{
		List<Bookings> bookings = services.getBookingDetailsById(email);
		if(bookings.size() == 0) {
			throw new ResourceNotFoundException("Bookings not found with this id : " + email);
		}
		  Flight[] flightList = restTemplate.postForObject("http://localhost:8084/flight/details", bookings, Flight[].class);
	      List<Flight> flights = new ArrayList<>(Arrays.asList(flightList));
	      ArrayList<BookingDetails> bookingDetails2 = new ArrayList<>() ;
	     for(int i=0;i<flights.size();i++) {
	    	 BookingDetails bd = new BookingDetails();
	    	 bd.setBooking(bookings.get(i));
	    	 bd.setFlight(flights.get(i));
	    	 bookingDetails2.add(bd);
	     }
	    	 
		return ResponseEntity.ok().body(bookingDetails2);
	}
 
 
 	
 	@PostMapping("/bookings")
	public void addBooking( @RequestBody Bookings booking) throws ResourceNotFoundException{
		booking.setBookingDate(new Date());//find flight by id
		Flight flight = restTemplate.getForObject("http://localhost:8084/flight/findOne/" + booking.getFlightId(), Flight.class);
		System.out.println(flight+"--");
		if(flight.getDepartDate().compareTo(LocalDate.now())<=0) {
			throw new ResourceNotFoundException("Date must be greater than the current date");
		}
		
		int seatsRemaining = flight.getSeatsRemaining();
		if( seatsRemaining >= 1 )
		{
			flight.setSeatsRemaining(seatsRemaining-1);
			services.addBooking(booking);
			HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<Flight> entity = new HttpEntity<Flight>(flight,headers);
		      //find put request in flight
		      restTemplate.exchange("http://localhost:8084/flight/update/" + booking.getFlightId(), HttpMethod.PUT,entity, String.class).getBody();
		}
		else {
			throw  new ResourceNotFoundException("Sorry, All seats are full");
		}
	}
 	
	
	@PutMapping("/booking/{id}")
	public ResponseEntity <Bookings> updateBookingById(@PathVariable(value = "id") String bookingId, 
			@RequestBody Bookings booking) throws ResourceNotFoundException {
			
			Bookings updatedBooking = services.updateBooking(bookingId, booking);
			return ResponseEntity.ok().body(updatedBooking);
	}
	
	
	@DeleteMapping("/booking/{id}")
    public Map<String, Boolean> deleteBooking(@PathVariable(value = "id") String bookingId)
    throws ResourceNotFoundException {
        services.deleteBooking(bookingId);
        Map < String, Boolean > response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
}
