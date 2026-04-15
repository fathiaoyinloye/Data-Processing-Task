package com.data_processing.dtos.genderizeData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenderData {
        private String name;
        private String gender;
        private double probability;
        private int count;
    }

//    {"count":3461,"name":"nonso","gender":"male","probability":0.91}

