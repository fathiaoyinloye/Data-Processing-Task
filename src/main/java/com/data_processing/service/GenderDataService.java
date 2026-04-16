package com.data_processing.service;

import com.data_processing.dtos.genderizeData.GenderData;
import com.data_processing.dtos.responses.Data;
import com.data_processing.dtos.responses.GenderDataResponse;
import com.data_processing.exceptions.GenderApiException;
import com.data_processing.utils.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GenderDataService {

    private final RestClient restClient;

    public GenderDataService(RestClient restClient) {
        this.restClient = restClient;
    }

    public GenderDataResponse getGenderInfo(String name) {
        validateName(name);
        String url = "https://api.genderize.io/?name=" + name;

        GenderData externalData = restClient.get()
                .uri(url)
                .retrieve()
                .body(GenderData.class);
        if (externalData == null || externalData.getGender() == null || externalData.getCount() == 0)
            throw new GenderApiException("No prediction available for the provided name", HttpStatus.OK);

        validateGenderAndCount(externalData.getGender(),externalData.getCount());
        Mappers.mapData(externalData, name);
        GenderDataResponse finalResponse = new GenderDataResponse();
        finalResponse.setStatus("success");
        finalResponse.setData(Mappers.mapData(externalData, name));
        return finalResponse;
    }

    private Boolean computeIsConfident(double probability, int count){
        return probability >= 0.7 && count >= 100;
    }

    private void validateGenderAndCount(String gender, int count ){
        if (gender ==null || count == 0) {
            throw new GenderApiException("No prediction available for the provided name", HttpStatus.BAD_REQUEST);
        }

    }

    private void validateName(String name) {

        if (name.isBlank())
            throw new GenderApiException("Name cannot be empty", HttpStatus.BAD_REQUEST);

        if (!name.matches("^[a-zA-Z]+$"))
            throw new GenderApiException("Name cannot be or contains number", HttpStatus.UNPROCESSABLE_ENTITY);

    }

    }
