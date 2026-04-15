package com.data_processing.service;

import com.data_processing.dtos.genderizeData.GenderData;
import com.data_processing.dtos.responses.Data;
import com.data_processing.dtos.responses.ErrorResponse;
import com.data_processing.dtos.responses.GenderDataResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GenderDataService {

    private final RestClient restClient;

    public GenderDataService(RestClient restClient) {
        this.restClient = restClient;
    }

    public GenderDataResponse getGenderInfo(String name) {
        String url = "https://api.genderize.io/?name=" + name;

        GenderData externalData = restClient.get()
                .uri(url)
                .retrieve()
                .body(GenderData.class);


        Data data = new Data();
        data.setGender(externalData.getGender());
        data.setProbability(externalData.getProbability());
        data.set_confident(computeIsConfident(externalData.getProbability(), externalData.getCount()));
        data.setSample_size(externalData.getCount());
        data.setName(name);


        GenderDataResponse finalResponse = new GenderDataResponse();
        finalResponse.setStatus("success");
        finalResponse.setData(data);

        return finalResponse;
    }

    private Boolean computeIsConfident(double probability, int count){
        return probability >= 0.7 && count >= 100;
    }

    private ErrorResponse validateGenderAndCount(String message){

    }
}
