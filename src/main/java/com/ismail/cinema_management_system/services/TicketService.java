package com.ismail.cinema_management_system.services;

import com.ismail.cinema_management_system.dto.TicketForm;
import com.ismail.cinema_management_system.entities.Ticket;
import com.ismail.cinema_management_system.repositories.ITicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketService implements IAbstractService<Ticket,Long> {
    private ITicketRepository repository;

    public TicketService(ITicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Ticket> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Ticket> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Ticket getOneById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Ticket add(Ticket ticket) {
        return repository.save(ticket);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Ticket> payTickets(TicketForm ticketForm){
        List<Ticket> ticketList = new ArrayList<>();
        ticketForm.getTickets().forEach(id->{
            Ticket ticket = getOneById(id);
            ticket.setClientName(ticketForm.getClientName());
            ticket.setReserved(true);
            ticket.setPaymentCode(ticketForm.getPaymentCode());
            add(ticket);
            ticketList.add(ticket);
        });

        return ticketList;
    }


    public Page<Ticket> getTicketsByProjectionID(Long projectionID, int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return this.repository.getTicketsOfProjection(projectionID,pageable);
    }
}
