package com.example.advertising.model;

import java.util.List;
import java.util.Map;

public class WeightCalculator {
    public static int calculateWeight(Advertisement ad, Map<String, Integer> tagFrequency) {
        int weight = 0;
        for (String tag : ad.getTags()) {
            weight += tagFrequency.getOrDefault(tag.trim(), 0);
        }
        return weight;
    }
}