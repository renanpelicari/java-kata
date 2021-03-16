package org.smb.kata.java.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.smb.kata.java.repository.model.HeatConsumption;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The repository for heat-consumption.json.
 */
@Repository
public class HeatRepository {

    private static final String RESOURCE_FILE_NAME = "data/heat-consumption.json";

    private ObjectMapper objectMapper;

    public HeatRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<HeatConsumption> findAll() throws IOException {
        String json = getFileAsString();
        return objectMapper.readValue(json, new TypeReference<List<HeatConsumption>>(){});
    }

    private String getFileAsString() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(RESOURCE_FILE_NAME);
        return readFromInputStream(inputStream);
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
