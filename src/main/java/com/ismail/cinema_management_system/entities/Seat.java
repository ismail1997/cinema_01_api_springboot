package com.ismail.cinema_management_system.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "seats")
@AllArgsConstructor @NoArgsConstructor
@Builder @Getter @Setter
public class Seat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private double longitude,latitude,altitude;
    @ManyToOne
    private Theater theater;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "seat")
    private Collection<Ticket> tickets;
}
