package com.ismail.cinema_management_system.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketForm {
        private String clientName;
        private Integer paymentCode;
        private List<Long> tickets = new ArrayList<>();
}
