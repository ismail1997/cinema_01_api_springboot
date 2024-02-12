package com.ismail.cinema_management_system.repositories;

import com.ismail.cinema_management_system.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITheaterRepository extends JpaRepository<Theater,Long> {

    @Query("select t from Theater  t where t.cinema.id =?1")
    List<Theater> getTheatersByCinemaID(Long cinemaID);
}
