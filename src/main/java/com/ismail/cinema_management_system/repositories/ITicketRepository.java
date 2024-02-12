package com.ismail.cinema_management_system.repositories;

import com.ismail.cinema_management_system.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Long> {

    @Query("select t from Ticket  t where t.projection.id =?1")
    Page<Ticket> getTicketsOfProjection(Long id, Pageable pageable);
}
