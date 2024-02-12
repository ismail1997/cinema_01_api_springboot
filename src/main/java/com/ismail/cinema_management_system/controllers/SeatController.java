package com.ismail.cinema_management_system.controllers;

import com.ismail.cinema_management_system.entities.Seat;
import com.ismail.cinema_management_system.services.SeatService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/seats")
public class SeatController implements IAbstractRestController<Seat,Long> {
    private SeatService service;

    public SeatController(SeatService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Seat> create(@RequestBody Seat seat)
    {
        return ResponseEntity.created(URI.create("/seats/seatID")).body(service.add(seat));
    }

    @GetMapping
    public ResponseEntity<Page<Seat>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size",defaultValue = "10") int size)
    {
        return ResponseEntity.ok().body(service.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getAll(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Seat> deleteOneById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
