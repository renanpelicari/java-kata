package org.smb.kata.java.controller;


import org.smb.kata.java.repository.model.ElectricityConsumption;
import org.smb.kata.java.repository.model.HeatConsumption;
import org.smb.kata.java.service.ConsumptionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/consumption", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsumptionController {

    private final ConsumptionService consumptionService;

    public ConsumptionController(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }

    @GetMapping
    public List<ElectricityConsumption> findAllConsumption() throws IOException {
        return consumptionService.findAllElectricityConsumption()
                .stream()
                .sorted(Comparator.comparing(ElectricityConsumption::getTimestampUtc)
                    .thenComparing(ElectricityConsumption::getMeterId)
                )
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{meterId}")
    public List<ElectricityConsumption> findConsumptionByMeterId(@PathVariable final UUID meterId) throws IOException {
        return consumptionService.findElectricityConsumptionByMeterId(meterId)
                .stream()
                .sorted(Comparator.comparing(ElectricityConsumption::getTimestampUtc))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/energy")
    public List<HeatConsumption> findAllEnergyConsumption() throws IOException {
        return consumptionService.findHeatConsumptions();
    }
}
