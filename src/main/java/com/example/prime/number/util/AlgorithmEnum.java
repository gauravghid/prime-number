package com.example.prime.number.util;

/**
 * Enum containing algorithm keys and names.
 */
public enum AlgorithmEnum {
    BFA("BruteForceAlgorithm"),
    SA("SieveAlgorithm");
    private String value;
    AlgorithmEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
