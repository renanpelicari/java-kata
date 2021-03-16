package org.smb.kata.java.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * The HeatConsumption model to represent the heat-consumption.json file.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeatConsumption {

    @JsonProperty("ts")
    private String timestampUtc;

    @JsonProperty("mid")
    private UUID meterId;

    @JsonProperty("v")
    private BigDecimal heatConsumption;

    @JsonProperty("bid")
    private UUID buildingId;
}
