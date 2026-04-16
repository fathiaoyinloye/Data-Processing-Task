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


