package com.bookingMicroservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingMicroservice.Exception.ResourceNotFoundException;
import com.bookingMicroservice.Model.Bookings;
import com.bookingMicroservice.Repository.BookingInterface;

@Service
public class Services {
	
	@Autowired
	BookingInterface bookingInterface;
	
	public void addBooking(Bookings booking) {
		bookingInterface.save(booking);
	}

	
	public List<Bookings> getBookings() {
		return bookingInterface.findAll();
	}

	public Bookings getBookings(String id) throws ResourceNotFoundException{
		return bookingInterface.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
	}
	
	public List<Bookings> getBookingDetailsById(String id){
		return bookingInterface.findAllByUserEmail(id);
	}

	public Bookings updateBooking(String id, Bookings b) throws ResourceNotFoundException{
		Bookings booking =  bookingInterface.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
		booking = b;
		booking.setBookingId(id);
		bookingInterface.save(booking);
		return booking;
	}

	public Bookings deleteBooking(String id) throws ResourceNotFoundException{
		Bookings booking =  bookingInterface.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
		bookingInterface.delete(booking);
		return booking;
	}

}
