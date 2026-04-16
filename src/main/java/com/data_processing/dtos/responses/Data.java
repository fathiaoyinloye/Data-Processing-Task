package com.data_processing.dtos.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Data{
    private String name;
    private String gender;
    private double probability;
    private int sample_size;

    @JsonProperty("is_confident")
    private boolean isConfident;
    private String processed_at = Instant.now().toString();
}




