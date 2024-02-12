package com.ismail.cinema_management_system.controllers;

import com.ismail.cinema_management_system.entities.City;
import com.ismail.cinema_management_system.services.CityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/cities")
public class CityController implements IAbstractRestController<City,Long> {
    private CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<City> create(@RequestBody City city)
    {
        return ResponseEntity.created(URI.create("/cities/cityID")).body(service.add(city));
    }

    @GetMapping
    public ResponseEntity<Page<City>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size)
    {
        return ResponseEntity.ok().body(service.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getAll(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteOneById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
