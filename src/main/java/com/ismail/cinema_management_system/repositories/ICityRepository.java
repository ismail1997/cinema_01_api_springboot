package com.ismail.cinema_management_system.repositories;

import com.ismail.cinema_management_system.entities.Cinema;
import com.ismail.cinema_management_system.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICityRepository extends JpaRepository<City,Long> {

}
