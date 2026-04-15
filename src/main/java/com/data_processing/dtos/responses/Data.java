package com.data_processing.dtos.responses;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
@Getter
@Setter
public class Data{
    private String name;
    private String gender;
    private double probability;
    private int sample_size;
    private boolean is_confident;
    private Instant processed_at = Instant.now();
}




