package com.example.spring.cor.repository.impl;

import com.example.spring.cor.record.GeoLocation;
import com.example.spring.cor.repository.GeoLocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeoLocationService implements GeoLocationRepository {

    @Override
    public Optional<GeoLocation> findGeoLocationByIp(String ip) {
        return Optional.of(new GeoLocation(12, 13));
    }
}
