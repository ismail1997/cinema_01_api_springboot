package com.ismail.cinema_management_system.repositories;

import com.ismail.cinema_management_system.entities.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IScreeningRepository extends JpaRepository<Screening,Long> {
}
