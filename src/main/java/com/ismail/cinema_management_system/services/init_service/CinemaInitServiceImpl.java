package com.ismail.cinema_management_system.services.init_service;

import com.ismail.cinema_management_system.entities.*;
import com.ismail.cinema_management_system.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{
    private ICityRepository cityRepository;
    private ICinemaRepository cinemaRepository;
    private ITheaterRepository theaterRepository;
    private ISeatRepository seatRepository;
    private IScreeningRepository screeningRepository;
    private ICategoryRepository categoryRepository;
    private IFilmRepository filmRepository;
    private IProjectionRepository projectionRepository;
    private ITicketRepository ticketRepository;

    public CinemaInitServiceImpl(ICityRepository cityRepository, ICinemaRepository cinemaRepository, ITheaterRepository theaterRepository, ISeatRepository seatRepository, IScreeningRepository screeningRepository, ICategoryRepository categoryRepository, IFilmRepository filmRepository, IProjectionRepository projectionRepository, ITicketRepository ticketRepository) {
        this.cityRepository = cityRepository;
        this.cinemaRepository = cinemaRepository;
        this.theaterRepository = theaterRepository;
        this.seatRepository = seatRepository;
        this.screeningRepository = screeningRepository;
        this.categoryRepository = categoryRepository;
        this.filmRepository = filmRepository;
        this.projectionRepository = projectionRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void initCities() {
        Stream.of("Brest","Paris","Nantes","Lyon")
                .forEach(city->{
                    City c = new City();
                    c.setName(city);
                    cityRepository.save(c);
                });
    }

    @Override
    public void initCinemas() {
        cityRepository.findAll().forEach(city -> {
            Stream.of("Starlight","Galaxy","Moonbeam")
            .forEach(c->{
                Cinema cinema = new Cinema();
                cinema.setName(c);
                cinema.setAltitude(0);
                cinema.setLatitude(0);
                cinema.setLongitude(0);
                cinema.setNumberOfTheaters(3);
                //cinema.setNumberOfTheaters(3+(int) (Math.random()*7));
                cinema.setCity(city);

                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initTheaters() {
        cinemaRepository.findAll().forEach(cinema -> {
            for(int i=0; i<cinema.getNumberOfTheaters(); i++){
                Theater theater = new Theater();
                theater.setName("Theater "+(i+1));
                theater.setCinema(cinema);
                theater.setNombrePlace(5+(int) (Math.random()*20));

                theaterRepository.save(theater);
            }
        });
    }

    @Override
    public void initSeats() {
        theaterRepository.findAll().forEach(theater -> {
            for(int i =0;i<theater.getNombrePlace();i++)
            {
                Seat seat = new Seat();
                seat.setNumber(i+1);
                seat.setTheater(theater);

                seatRepository.save(seat);
            }
        });
    }

    @Override
    public void initScreenings() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("17:00","19:00","21:00").forEach(s -> {
            Screening screening = new Screening();
            try {
                screening.setStartingHour(dateFormat.parse(s));
                screeningRepository.save(screening);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Action","Comedy","Drama","Horror","Science Fiction (Sci-Fi)").forEach(category->{
                 Category c = new Category();
                 c.setName(category);

                 categoryRepository.save(c);
        });
    }

    @Override
    public void initFilms() {
        double [] durations = new double[]{1,1.5,2,2.5,3};
        List<Category> categories = categoryRepository.findAll();
        Stream.of(
                "The Shawshank Redemption", "Inception", "The Lord of the Rings", "Forrest Gump"
        ).forEach(title->{
            Film film = new Film();
            film.setTitle(title);
            film.setDuration(durations[new Random().nextInt(durations.length)]);
            film.setCategory(categories.get(new Random().nextInt(categories.size())));
            film.setPhoto(title+".jpg");
            filmRepository.save(film);
        });
    }

    @Override
    public void initProjections() {
        double[] prices = new double[] {5,10,15,20,25,30} ;
        List<Film> films = filmRepository.findAll();
        cityRepository.findAll().forEach(city -> {
            city.getCinemas().forEach(cinema -> {
                cinema.getTheaters().forEach(theater -> {
                    int index = new Random().nextInt(films.size());
                        Film film = films.get(index);
                        screeningRepository.findAll().forEach(screening -> {
                            Projection projection = new Projection();
                            projection.setProjectionDate(new Date());
                            projection.setFilm(film);
                            projection.setPrice(prices[new Random().nextInt(prices.length)]);
                            projection.setTheater(theater);
                            projection.setScreening(screening);

                            projectionRepository.save(projection);

                        });

                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(projection ->
        {
            projection.getTheater().getSeats().forEach(seat ->{
                Ticket ticket = new Ticket();
                ticket.setSeat(seat);
                ticket.setPrice(projection.getPrice());
                ticket.setProjection(projection);
                ticket.setReserved(false);
                ticketRepository.save(ticket);
            });
        });
    }
}
