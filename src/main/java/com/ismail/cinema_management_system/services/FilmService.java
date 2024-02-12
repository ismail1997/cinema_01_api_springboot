package com.ismail.cinema_management_system.services;

import com.ismail.cinema_management_system.entities.Film;
import com.ismail.cinema_management_system.repositories.IFilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService implements IAbstractService<Film,Long> {

    private IFilmRepository filmRepository;

    public FilmService(IFilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Page<Film> getAll(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

    @Override
    public Film getOneById(Long id) {
        return filmRepository.findById(id).orElseThrow();
    }

    @Override
    public Film add(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }
}
