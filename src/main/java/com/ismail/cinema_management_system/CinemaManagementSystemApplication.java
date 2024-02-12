package com.ismail.cinema_management_system;

import com.ismail.cinema_management_system.services.init_service.ICinemaInitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaManagementSystemApplication implements CommandLineRunner {

    private ICinemaInitService cinemaInitService;

    public CinemaManagementSystemApplication(ICinemaInitService cinemaInitService) {
        this.cinemaInitService = cinemaInitService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CinemaManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cinemaInitService.initCities();
        cinemaInitService.initCinemas();
        cinemaInitService.initTheaters();
        cinemaInitService.initSeats();
        cinemaInitService.initScreenings();
        cinemaInitService.initCategories();
        cinemaInitService.initFilms();
        cinemaInitService.initProjections();
        cinemaInitService.initTickets();/**/
    }
}
