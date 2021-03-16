package org.smb.kata.java.service;

import org.smb.kata.java.repository.ElectricityRepository;
import org.smb.kata.java.repository.HeatRepository;
import org.smb.kata.java.repository.model.ElectricityConsumption;
import org.smb.kata.java.repository.model.HeatConsumption;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ConsumptionService {

    private final ElectricityRepository electricityRepository;

    private final HeatRepository heatRepository;

    public ConsumptionService(ElectricityRepository electricityRepository, HeatRepository heatRepository) {
        this.electricityRepository = electricityRepository;
        this.heatRepository = heatRepository;
    }

    public List<ElectricityConsumption> findAllElectricityConsumption() throws IOException {
        return electricityRepository.findAll();
    }

    public List<ElectricityConsumption> findElectricityConsumptionByMeterId(UUID meterId) throws IOException {
        return electricityRepository.findByMeterId(meterId);
    }

    public List<HeatConsumption> findHeatConsumptions() throws IOException {
        // TODO convert into model that contains electricity info also
        return heatRepository.findAll();
    }
}
