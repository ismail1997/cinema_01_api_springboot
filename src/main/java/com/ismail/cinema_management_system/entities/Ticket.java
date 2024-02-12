package com.ismail.cinema_management_system.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tickets")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private double price;
    @Column(unique = false,nullable = true)
    private Integer paymentCode;
    private boolean reserved;
    @ManyToOne
    private Seat seat;


    @ManyToOne
    private Projection projection;
}
