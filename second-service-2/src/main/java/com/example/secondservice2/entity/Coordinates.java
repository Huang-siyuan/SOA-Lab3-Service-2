package com.example.secondservice2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Coordinates {
    private int id;
    private float x;
    private Integer y; //Поле не может быть null

    public Coordinates(float x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
