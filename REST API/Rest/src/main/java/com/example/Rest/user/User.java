package com.example.Rest.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private Date joinDate;
}
