package com.example.secondservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private int id;
    private int x;
    private float y;
    private Long z; //Поле не может быть null
    private String name; //Поле не может быть null
}
