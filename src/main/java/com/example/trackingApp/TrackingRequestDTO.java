package com.example.trackingApp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


public class TrackingRequestDTO {
    private String originCountryId;
    private String destinationCountryId;
    private BigDecimal weight;
    private OffsetDateTime createdAt;
    private UUID customerId;
    private String customerName;
    private String customerSlug;

    public TrackingRequestDTO(String originCountryId, String destinationCountryId, BigDecimal weight,
                              String createdAtStr, UUID customerId, String customerName, String customerSlug) {
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        this.weight = weight;
        this.createdAt = OffsetDateTime.parse(createdAtStr);
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSlug = customerSlug;
    }
    // getters & setters

    public String getOriginCountryId() {
        return originCountryId;
    }

    public void setOriginCountryId(String originCountryId) {
        this.originCountryId = originCountryId;
    }

    public String getDestinationCountryId() {
        return destinationCountryId;
    }

    public void setDestinationCountryId(String destinationCountryId) {
        this.destinationCountryId = destinationCountryId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSlug() {
        return customerSlug;
    }

    public void setCustomerSlug(String customerSlug) {
        this.customerSlug = customerSlug;
    }
}

