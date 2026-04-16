package com.data_processing.utils;

import com.data_processing.dtos.genderizeData.GenderData;
import com.data_processing.dtos.responses.Data;

public class Mappers {

    public static Data mapData(GenderData externalData, String name){
        Data data = new Data();
        data.setGender(externalData.getGender());
        data.setProbability(externalData.getProbability());
        data.setConfident(computeIsConfident(externalData.getProbability(), externalData.getCount()));
        data.setSample_size(externalData.getCount());
        data.setName(name);
        return data;
    }
    private static  boolean computeIsConfident(double probability, int count){
        return probability >= 0.7 && count >= 100;
    }

}
