package com.ismail.cinema_management_system.controllers;

import com.ismail.cinema_management_system.entities.Theater;
import com.ismail.cinema_management_system.services.TheaterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/theaters")
public class TheaterController implements IAbstractRestController<Theater,Long> {
    private TheaterService service;

    public TheaterController(TheaterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Theater> create(@RequestBody Theater theater)
    {
        return ResponseEntity.created(URI.create("/theaters/theaterID")).body(service.add(theater));
    }

    @GetMapping
    public ResponseEntity<Page<Theater>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size",defaultValue = "10") int size)
    {
        return ResponseEntity.ok().body(service.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getAll(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Theater> deleteOneById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/cinema/{id}")
    public ResponseEntity<List<Theater>> getTheatersOfCinema(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(service.getTheatersOfCinemaByID(id));
    }
}
