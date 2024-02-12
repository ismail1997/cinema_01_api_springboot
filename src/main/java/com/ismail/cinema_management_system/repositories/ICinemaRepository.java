package com.ismail.cinema_management_system.repositories;

import com.ismail.cinema_management_system.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICinemaRepository extends JpaRepository<Cinema,Long> {
    @Query("select c from Cinema c where c.city.id =?1")
    List<Cinema> getCinemasByCityID(Long id);
}
