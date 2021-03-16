package org.smb.kata.java.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.smb.kata.java.repository.model.ElectricityConsumption;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The repository for electricity-consumption.csv
 */
@Repository
public class ElectricityRepository {

    private static final String RESOURCE_FILE_NAME = "data/electricity-consumption.csv";

    public List<ElectricityConsumption> findAll() throws IOException {
        return readModelFromAFile();
    }

    public List<ElectricityConsumption> findByMeterId(UUID meterId) throws IOException {
        return Objects.requireNonNull(findAll()).stream()
                .filter(e -> e.getMeterId().equals(meterId))
                .collect(Collectors.toList());
    }

    private List<ElectricityConsumption> readModelFromAFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(RESOURCE_FILE_NAME);

        List<ElectricityConsumption> electricityConsumptions = new ArrayList<>();
        boolean firstLine = true;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!firstLine) {

                    String[] columns = line.split(";");
                    ElectricityConsumption electricityConsumption = ElectricityConsumption.builder()
                            .timestampUtc(columns[0].replace("\"", ""))
                            .meterId(UUID.fromString(columns[1].replace("\"", "")))
                            .consumptionKwh(new BigDecimal(columns[2].replace("\"", "")))
                            .buildingId(UUID.fromString(columns[3].replace("\"", "")))
                            .build();

                     electricityConsumptions.add(electricityConsumption);
                }
                firstLine = false;
            }
        }
        return electricityConsumptions;
    }
}
