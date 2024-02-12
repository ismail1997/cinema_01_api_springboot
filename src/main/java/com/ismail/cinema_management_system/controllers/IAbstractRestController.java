package com.ismail.cinema_management_system.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
public interface IAbstractRestController<T,K> {
    ResponseEntity<T> create( T t);
    ResponseEntity<Page<T>> get( int page,int size);
    ResponseEntity<T> getAll( K id);
    ResponseEntity<T> deleteOneById(K id);
}
