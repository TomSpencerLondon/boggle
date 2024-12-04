package org.example.bogglesolver;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class CsvLoader {

    private Map<String, String> dictionary;

    @PostConstruct
    public void loadCsv() {
        dictionary = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/dictionary.csv")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t", 2);
                if (parts.length == 2) {
                    dictionary.put(parts[0].toUpperCase(), parts[1]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load dictionary CSV", e);
        }
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }

    public String getDefinition(String word) {
        return dictionary.get(word.toUpperCase());
    }
}
