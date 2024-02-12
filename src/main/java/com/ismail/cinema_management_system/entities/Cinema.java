package com.ismail.cinema_management_system.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;


@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "cinemas")
public class Cinema implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 55)
    private String name;
    private double longitude,latitude,altitude;
    private int numberOfTheaters;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "cinema")
    private Collection<Theater> theaters;

    @ManyToOne
    private City city;
}
