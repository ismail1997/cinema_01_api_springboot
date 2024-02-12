package com.ismail.cinema_management_system.services;

import com.ismail.cinema_management_system.entities.Screening;
import com.ismail.cinema_management_system.repositories.IScreeningRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningService implements IAbstractService<Screening,Long>{
    private IScreeningRepository screeningRepository;

    public ScreeningService(IScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Override
    public List<Screening> getAll() {
        return screeningRepository.findAll();
    }

    @Override
    public Page<Screening> getAll(Pageable pageable) {
        return screeningRepository.findAll(pageable);
    }

    @Override
    public Screening getOneById(Long id) {
        return screeningRepository.findById(id).orElseThrow();
    }

    @Override
    public Screening add(Screening screening) {
        return screeningRepository.save(screening);
    }

    @Override
    public void deleteById(Long id) {
        screeningRepository.deleteById(id);
    }
}
