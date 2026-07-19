package com.rizalamar.moodflix.service;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public void validateGeolocation(double lat, double lon){
        if(lat < -90 || lat > 90){
            throw new IllegalArgumentException("Latitude must be between -90 and 90 degrees");
        }

        if(lon < -180 || lon > 180){
            throw new IllegalArgumentException("Longitude must be between -180 and 180 degrees");
        }
    }
}
