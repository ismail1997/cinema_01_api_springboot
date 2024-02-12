package com.ismail.cinema_management_system.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder @Table(name = "films")
@Entity
public class Film {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description ;
    private String maker;
    private Date publishDate;
    private double duration;
    private String photo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "film")
    private Collection<Projection> projections;
    @ManyToOne
    private Category category;
}
