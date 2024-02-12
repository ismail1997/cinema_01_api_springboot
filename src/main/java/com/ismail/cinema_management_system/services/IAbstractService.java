package com.ismail.cinema_management_system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAbstractService<E,K> {
    List<E> getAll();
    Page<E> getAll(Pageable pageable);
    E getOneById(K id);
    E add(E e);
    void deleteById(K id);

}
