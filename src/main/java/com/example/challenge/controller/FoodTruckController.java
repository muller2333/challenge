package com.example.challenge.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodTruckController {
    @GetMapping("/foodtrucks")
    public Set<Map<String, String>> getFoodTrackInfo() throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader("./Mobile_Food_Facility_Permit.csv"));
        String row;
        Map<Integer, String> headMap = new HashMap<>();
        // store columnNumber-headName mappings
        if ((row = reader.readLine()) != null) {
            String[] heads = row.split(",");
            for (int i = 0; i < heads.length; i++) {
                headMap.put(i, heads[i]);
            }
        }
        Set<Map<String, String>> set = new LinkedHashSet<>();
        while ((row = reader.readLine()) != null) {
            String[] cells = row.split(",");
            // filter type
            if ("Truck".equals(cells[2])) {
                Map<String, String> map = new LinkedHashMap<>();
                for (int i = 0; i < cells.length; i++) {
                    // filter null value
                    if (headMap.get(i) != null) {
                        // save headName-cellValue mappings
                        map.put(headMap.get(i), cells[i]);
                    }
                }
                set.add(map);
            }
        }
        reader.close();
        return set;
    }
}
