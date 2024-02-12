package com.ismail.cinema_management_system.repositories;

import com.ismail.cinema_management_system.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeatRepository extends JpaRepository<Seat,Long> {
}
