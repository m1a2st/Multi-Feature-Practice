package com.example.spring.cor.repository;

import com.example.spring.cor.record.GeoLocation;

import java.util.Optional;

public interface GeoLocationRepository {
    Optional<GeoLocation> findGeoLocationByIp(String ip);
}
