package com.example.prime.number.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * Pojo class for Response object.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponseModel {
    @JsonProperty("Initial")
    private int initial;
    @JsonProperty("Primes")
    private List<Integer> primes;
}
