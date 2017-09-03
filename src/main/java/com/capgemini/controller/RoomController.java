package com.capgemini.controller;

import com.capgemini.exception.NotFoundException;
import com.capgemini.model.Room;
import com.capgemini.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room/")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Room> getAll() {

        return roomRepository.findAll();
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public Room get(@PathVariable long id) {

        Room room = roomRepository.findOne(id);

        if (room == null) throw new NotFoundException();

        return room;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void create(@RequestBody Room room) {
        roomRepository.save(room);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Room updatedRoom) {
        Room room = roomRepository.findOne(id);

        if (room == null) throw new NotFoundException();

        roomRepository.save(updatedRoom);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        Room room = roomRepository.findOne(id);

        if (room == null) throw new NotFoundException();

        roomRepository.delete(room);
    }
}
