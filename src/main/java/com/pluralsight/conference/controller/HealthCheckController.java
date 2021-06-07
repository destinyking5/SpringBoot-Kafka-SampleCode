package com.pluralsight.conference.controller;

import com.fasterxml.jackson.annotation.JsonFormat; // jackson:jsr310
import com.fasterxml.jackson.annotation.JsonInclude; // jackson:jsr310
import com.fasterxml.jackson.databind.ObjectMapper; // jackson:jsr310
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;


import java.time.ZonedDateTime;

@RestController
class HealthCheckController {


    @GetMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity healthCheck(@RequestParam String format) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        ApiResponse apiStatus;
        if(format.equals("short")) {
            apiStatus = new ApiResponse(HttpStatus.OK);
        } else if(format.equals("full")) {
            apiStatus = new ApiResponse(HttpStatus.OK);
            apiStatus.setTime();
        } else {
            apiStatus = new ApiResponse(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(apiStatus, apiStatus.status);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        ApiResponse apiStatus = new ApiResponse(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiStatus, apiStatus.status);
    }

//    @PutMapping(value = "/healthcheck")
//    public void healthCheck() {
//        return;
//    }
//
//    @PostMapping(value = "/healthcheck")
//    public void healthCheck() {
//        return;
//    }
}

class ApiResponse {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'")
    private ZonedDateTime currentTime;
    public HttpStatus status;

    ApiResponse(HttpStatus status) {
        this.status = status;
    }

    void setTime() {
        this.currentTime = ZonedDateTime.now();
    }
}