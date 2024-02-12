package com.ismail.cinema_management_system.controllers;


import com.ismail.cinema_management_system.entities.Cinema;
import com.ismail.cinema_management_system.services.CinemaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController implements IAbstractRestController<Cinema,Long> {

    private CinemaService service;

    public CinemaController(CinemaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cinema> create(@RequestBody Cinema cinema)
    {
        return ResponseEntity.created(URI.create("/cinemas/cinemaID")).body(service.add(cinema));
    }

    @GetMapping
    public ResponseEntity<Page<Cinema>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size",defaultValue = "10") int size)
    {
        return ResponseEntity.ok().body(service.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> getAll(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cinema> deleteOneById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<Cinema>> getCinemasByCityID(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getCinemasOfCity(id));
    }
}
