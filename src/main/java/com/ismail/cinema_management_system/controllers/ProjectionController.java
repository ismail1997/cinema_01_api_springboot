package com.ismail.cinema_management_system.controllers;

import com.ismail.cinema_management_system.entities.Film;
import com.ismail.cinema_management_system.entities.Projection;
import com.ismail.cinema_management_system.services.ProjectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projections")
public class ProjectionController implements IAbstractRestController<Projection,Long> {
    private ProjectionService service;

    public ProjectionController(ProjectionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Projection> create(@RequestBody Projection projection)
    {
        return ResponseEntity.created(URI.create("/projections/projectionID")).body(service.add(projection));
    }

    @GetMapping
    public ResponseEntity<Page<Projection>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size",defaultValue = "10") int size)
    {
        return ResponseEntity.ok().body(service.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projection> getAll(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Projection> deleteOneById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/theater/{id}")
    public ResponseEntity<List<Projection>> getProjectionsByTheaterID(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getProjectionsByTheaterID(id));
    }
}
