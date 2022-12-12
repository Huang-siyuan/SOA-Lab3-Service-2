package com.example.secondservice2.dto;

import com.example.secondservice2.entity.Route;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {

    @JsonProperty("route")
    private Route route;

    @JsonProperty("message")
    private String message;

    public MessageDto(String message) {
        this.message = message;
    }

    public MessageDto(Route route) {
        this.route = route;
    }

}
