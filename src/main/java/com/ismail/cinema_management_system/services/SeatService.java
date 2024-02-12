package com.ismail.cinema_management_system.services;

import com.ismail.cinema_management_system.entities.Seat;
import com.ismail.cinema_management_system.repositories.ISeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService implements IAbstractService<Seat,Long> {
    private ISeatRepository seatRepository;
    @Override
    public List<Seat> getAll() {
        return seatRepository.findAll();
    }

    @Override
    public Page<Seat> getAll(Pageable pageable) {
        return seatRepository.findAll(pageable);
    }

    @Override
    public Seat getOneById(Long id) {
        return seatRepository.findById(id).orElseThrow();
    }

    @Override
    public Seat add(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void deleteById(Long id) {
        seatRepository.deleteById(id);
    }
}
