package com.ismail.cinema_management_system.controllers;


import com.ismail.cinema_management_system.entities.Film;
import com.ismail.cinema_management_system.services.FilmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RestController
@RequestMapping("/films")
public class FilmController implements IAbstractRestController<Film,Long> {
    private FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Film> create(@RequestBody Film film)
    {
        return ResponseEntity.created(URI.create("/films/filmID")).body(service.add(film));
    }

    @GetMapping
    public ResponseEntity<Page<Film>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size",defaultValue = "10") int size)
    {
        return ResponseEntity.ok().body(service.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getAll(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Film> deleteOneById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}