package com.ismail.cinema_management_system.services;

import com.ismail.cinema_management_system.entities.City;
import com.ismail.cinema_management_system.repositories.ICityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements IAbstractService<City,Long>{
    private ICityRepository cityRepository;

    public CityService(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public Page<City> getAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public City getOneById(Long id) {
        return cityRepository.findById(id).orElseThrow();
    }

    @Override
    public City add(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }
}
