package com.example.trackingApp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/next-tracking-number")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping
    public ResponseEntity<TrackingResponseDTO> getNextTrackingNumber(
            @RequestParam("origin_country_id") String originCountryId,
            @RequestParam("destination_country_id") String destinationCountryId,
            @RequestParam("weight") BigDecimal weight,
            @RequestParam("created_at") String createdAt,
            @RequestParam("customer_id") UUID customerId,
            @RequestParam("customer_name") String customerName,
            @RequestParam("customer_slug") String customerSlug
    ) {
        TrackingRequestDTO request = new TrackingRequestDTO(
                originCountryId,
                destinationCountryId,
                weight,
                createdAt,
                customerId,
                customerName,
                customerSlug
        );

        TrackingResponseDTO response = trackingService.generateTrackingNumber(request);
        return ResponseEntity.ok(response);
    }
}
