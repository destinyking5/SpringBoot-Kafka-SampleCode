package com.pluralsight.conference.controller;

import com.pluralsight.conference.model.City;
import com.pluralsight.conference.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private ICityService cityService;

    @GetMapping(value = "/cities")
    public List<City> getCities() {

        List<City> cities = cityService.findAll();

        return cities;
    }
}