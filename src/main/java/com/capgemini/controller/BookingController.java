package com.capgemini.controller;

import com.capgemini.exception.NotFoundException;
import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.model.Room;
import com.capgemini.repository.BookingRepository;
import com.capgemini.repository.GuestRepository;
import com.capgemini.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking/")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    RoomRepository roomRepository;

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

        Guest guest = guestRepository.findOne(booking.getGuest().getId());

        if (guest == null) throw new NotFoundException();

        Room room = roomRepository.findOne(booking.getRoom().getId());

        if (room == null) throw new NotFoundException();

        booking.setGuest(guest);
        booking.setRoom(room);

        bookingRepository.save(booking);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Booking updatedBooking) {
        Booking booking = bookingRepository.findOne(id);

        if (booking == null) throw new NotFoundException();

        bookingRepository.save(updatedBooking);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        Booking booking = bookingRepository.findOne(id);

        if (booking == null) throw new NotFoundException();

        bookingRepository.delete(booking);
    }

}
