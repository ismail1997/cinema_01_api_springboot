package com.ismail.cinema_management_system.controllers;

import com.ismail.cinema_management_system.entities.Category;
import com.ismail.cinema_management_system.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RestController
@RequestMapping("/categories")
public class CategoryController implements IAbstractRestController<Category,Long> {
    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category)
    {
        return ResponseEntity.created(URI.create("/categories/categoryID")).body(service.add(category));
    }

    @GetMapping
    public ResponseEntity<Page<Category>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size",defaultValue = "10") int size)
    {
        return ResponseEntity.ok().body(service.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getAll(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteOneById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
