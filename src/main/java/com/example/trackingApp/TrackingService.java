package com.example.trackingApp;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TrackingService {

    private final TrackingNumberRepository repository;

    public TrackingService(TrackingNumberRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TrackingResponseDTO generateTrackingNumber(TrackingRequestDTO request) {
        // Sanitize country codes
        String origin = sanitizeCode(request.getOriginCountryId());
        String destination = sanitizeCode(request.getDestinationCountryId());
        String prefix = origin + destination;

        OffsetDateTime now = OffsetDateTime.now();

        // Create new entity with temporary trackingNumber (required not null)
        TrackingNumber temp = new TrackingNumber();
        temp.setCreatedAt(now);
        temp.setTrackingNumber("TEMP000000000000"); // temp placeholder, length 16
        repository.saveAndFlush(temp);  // force ID generation

        String base36Id = Long.toString(temp.getId(), 36).toUpperCase();

        String slugPart = sanitizeCode(request.getCustomerSlug());
        if (slugPart.length() > 3) slugPart = slugPart.substring(0, 3);

        String weightStr = request.getWeight().toPlainString().replace(".", "");
        String weightPart = weightStr.substring(0, Math.min(3, weightStr.length()));

        String createdAtHash = Integer.toHexString(request.getCreatedAt().hashCode()).toUpperCase();

        String rawTracking = prefix + slugPart + weightPart + createdAtHash + base36Id;

        rawTracking = rawTracking.replaceAll("[^A-Z0-9]", "");

        String trackingNumber;
        if (rawTracking.length() > 16) {
            trackingNumber = rawTracking.substring(0, 16);
        } else {
            // Pad left with zeros to length 16
            trackingNumber = String.format("%16s", rawTracking).replace(' ', '0');
        }

        // Update entity with real tracking number
        temp.setTrackingNumber(trackingNumber);
        repository.save(temp);

        return new TrackingResponseDTO(trackingNumber, now);
    }

    private String sanitizeCode(String code) {
        if (code == null) return "";
        return code.toUpperCase().replaceAll("[^A-Z0-9]", "");
    }
}
