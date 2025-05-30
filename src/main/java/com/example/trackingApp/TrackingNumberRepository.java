package com.example.trackingApp;
import com.example.trackingApp.TrackingNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingNumberRepository extends JpaRepository<TrackingNumber, Long> {
    boolean existsByTrackingNumber(String trackingNumber);
}
