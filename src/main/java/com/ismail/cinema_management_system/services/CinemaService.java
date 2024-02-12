package com.ismail.cinema_management_system.services;

import com.ismail.cinema_management_system.entities.Cinema;
import com.ismail.cinema_management_system.repositories.ICinemaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService implements IAbstractService<Cinema,Long> {
    private ICinemaRepository cinemaRepository;

    public CinemaService(ICinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public List<Cinema> getAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public Page<Cinema> getAll(Pageable pageable) {
        return cinemaRepository.findAll(pageable);
    }

    @Override
    public Cinema getOneById(Long id) {
        return cinemaRepository.findById(id).orElseThrow();
    }

    @Override
    public Cinema add(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public void deleteById(Long id) {
        cinemaRepository.deleteById(id);
    }

    public List<Cinema> getCinemasOfCity(Long id){
        return this.cinemaRepository.getCinemasByCityID(id);
    }
}
