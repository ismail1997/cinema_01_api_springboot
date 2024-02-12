package com.ismail.cinema_management_system.repositories;

import com.ismail.cinema_management_system.entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProjectionRepository extends JpaRepository<Projection,Long> {

    @Query("SELECT p FROM Projection p where p.theater.id=?1")
    List<Projection> getProjectionsByTheaterID(Long id);
}
