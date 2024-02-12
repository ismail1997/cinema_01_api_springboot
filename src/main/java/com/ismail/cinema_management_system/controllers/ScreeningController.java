package com.ismail.cinema_management_system.controllers;

import com.ismail.cinema_management_system.entities.Projection;
import com.ismail.cinema_management_system.entities.Screening;
import com.ismail.cinema_management_system.services.ScreeningService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/screenings")
public class ScreeningController implements IAbstractRestController<Screening,Long> {
    private ScreeningService service;

    public ScreeningController(ScreeningService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Screening> create(@RequestBody Screening screening)
    {
        return ResponseEntity.created(URI.create("/screenings/screeningID")).body(service.add(screening));
    }

    @GetMapping
    public ResponseEntity<Page<Screening>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size",defaultValue = "10") int size)
    {
        return ResponseEntity.ok().body(service.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Screening> getAll(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Screening> deleteOneById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
