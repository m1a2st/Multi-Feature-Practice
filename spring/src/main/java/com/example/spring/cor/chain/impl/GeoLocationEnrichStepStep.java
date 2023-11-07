package com.example.spring.cor.chain.impl;


import com.example.spring.cor.Message;
import com.example.spring.cor.repository.impl.GeoLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Order(2)
@RequiredArgsConstructor
public class GeoLocationEnrichStepStep extends AbstractEnrichmentStep {

    private final GeoLocationService geoLocationService;

    @Override
    protected Optional<Message> enrichAndApplyNext(Message message) {
        return message.getValue("ip")
                .flatMap(geoLocationService::findGeoLocationByIp)
                .map(geo -> message.with("geoLocation", geo.toString()));
    }
}
