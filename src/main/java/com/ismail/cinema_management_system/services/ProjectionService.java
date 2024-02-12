package com.ismail.cinema_management_system.services;

import com.ismail.cinema_management_system.entities.Projection;
import com.ismail.cinema_management_system.repositories.IProjectionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectionService implements IAbstractService<Projection,Long> {
    private IProjectionRepository projectionRepository;

    public ProjectionService(IProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }

    @Override
    public List<Projection> getAll() {
        return projectionRepository.findAll();
    }

    @Override
    public Page<Projection> getAll(Pageable pageable) {
        return  projectionRepository.findAll(pageable);
    }

    @Override
    public Projection getOneById(Long id) {
        return projectionRepository.findById(id).orElseThrow();
    }

    @Override
    public Projection add(Projection projection) {
        return projectionRepository.save(projection);
    }

    @Override
    public void deleteById(Long id) {
        projectionRepository.deleteById(id);
    }


    public List<Projection> getProjectionsByTheaterID(Long id)
    {
        return this.projectionRepository.getProjectionsByTheaterID(id);
    }
}
