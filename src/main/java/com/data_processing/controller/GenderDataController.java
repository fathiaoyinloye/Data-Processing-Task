package com.data_processing.controller;

import com.data_processing.service.GenderDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GenderDataController {
    private final GenderDataService genderDataService;


    public GenderDataController(GenderDataService genderDataService) {
        this.genderDataService = genderDataService;
    }


    @GetMapping("/classify")
    public ResponseEntity<?> getGenderData(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(genderDataService.getGenderInfo(name));
    }
}
