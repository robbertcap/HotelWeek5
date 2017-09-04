package com.capgemini.controller;

import com.capgemini.exception.NotFoundException;
import com.capgemini.model.Guest;
import com.capgemini.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guest/")
public class GuestController {

    @Autowired
    GuestRepository guestRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Guest> getAll() {

        return guestRepository.findAll();
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public Guest get(@PathVariable long id) {

        Guest guest = guestRepository.findOne(id);

        if (guest == null) throw new NotFoundException();

        return guest;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void create(@RequestBody Guest guest) {
        guestRepository.save(guest);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Guest updatedGuest) {
        Guest guest = guestRepository.findOne(id);

        if (guest == null) throw new NotFoundException();

        guestRepository.save(updatedGuest);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        Guest g = guestRepository.findOne(id);

        if (g == null) throw new NotFoundException();

        guestRepository.delete(g);
    }

}
