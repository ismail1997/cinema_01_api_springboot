package com.ismail.cinema_management_system.controllers;

import com.ismail.cinema_management_system.entities.Film;
import com.ismail.cinema_management_system.services.FilmService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class CinemaRestController {

    private FilmService filmService;

    public CinemaRestController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(path = "/films/{id}/image",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable("id") Long id) throws IOException {
        Film film = filmService.getOneById(id);
        String photoName = film.getPhoto();

        File file = new File(System.getProperty("user.home")+"/cinema_project/images/film_images/"+photoName);

        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }
}
