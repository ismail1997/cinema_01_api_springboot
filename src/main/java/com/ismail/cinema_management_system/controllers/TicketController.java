package com.ismail.cinema_management_system.controllers;

import com.ismail.cinema_management_system.dto.TicketForm;
import com.ismail.cinema_management_system.entities.Theater;
import com.ismail.cinema_management_system.entities.Ticket;
import com.ismail.cinema_management_system.services.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController implements IAbstractRestController<Ticket,Long> {
    private TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket)
    {
        return ResponseEntity.created(URI.create("/tickets/ticketID")).body(service.add(ticket));
    }

    @GetMapping
    public ResponseEntity<Page<Ticket>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size",defaultValue = "10") int size)
    {
        return ResponseEntity.ok().body(service.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getAll(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteOneById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/payTickets")
    public ResponseEntity<List<Ticket>> payTickets(@RequestBody TicketForm ticketForm){
        return  ResponseEntity.ok(service.payTickets(ticketForm));
    }

    @GetMapping("/projection/{id}")
    public ResponseEntity<Page<Ticket>> getTicketsOfProjectionByID(@PathVariable("id") Long id,
                                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(value = "size",defaultValue = "20") int size)
    {
        return ResponseEntity.ok(this.service.getTicketsByProjectionID(id,page,size));
    }
}
