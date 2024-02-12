package com.ismail.cinema_management_system.services;

import com.ismail.cinema_management_system.entities.Theater;
import com.ismail.cinema_management_system.repositories.ITheaterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService implements IAbstractService<Theater,Long> {
    private ITheaterRepository repository;

    public TheaterService(ITheaterRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Theater> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Theater> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Theater getOneById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Theater add(Theater theater) {
        return repository.save(theater);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Theater> getTheatersOfCinemaByID(Long id)
    {
        return this.repository.getTheatersByCinemaID(id);
    }
}
