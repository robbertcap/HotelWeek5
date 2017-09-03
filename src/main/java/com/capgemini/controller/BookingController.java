package com.capgemini.controller;

import com.capgemini.exception.NotFoundException;
import com.capgemini.model.Booking;
import com.capgemini.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking/")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Booking> getAll() {

        return bookingRepository.findAll();
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public Booking get(@PathVariable long id) {

        Booking booking = bookingRepository.findOne(id);

        if (booking == null) throw new NotFoundException();

        return booking;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void create(@RequestBody Booking booking) {
        bookingRepository.save(booking);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Booking updatedBooking) {
        Booking booking = bookingRepository.findOne(id);

        if (booking == null) throw new NotFoundException();

        bookingRepository.save(updatedBooking);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        Booking booking = bookingRepository.findOne(id);

        if (booking == null) throw new NotFoundException();

        bookingRepository.delete(booking);
    }
}
