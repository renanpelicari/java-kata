package org.smb.kata.java.repository.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * The ElectricityConsumption model to represent the electricity-consumption.csv file.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectricityConsumption {

    @JsonProperty("timestamp_utc")
    private String timestampUtc;

    @JsonProperty("meter_id")
    private UUID meterId;

    @JsonProperty("consumption_kwh")
    private BigDecimal consumptionKwh;

    @JsonProperty("building_id")
    private UUID buildingId;
}
