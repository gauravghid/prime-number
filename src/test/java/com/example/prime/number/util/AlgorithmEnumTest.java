package com.example.prime.number.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class AlgorithmEnumTest {

    @Test
    void testGetTextValue() {
        assertAll(
                () -> assertEquals("BruteForceAlgorithm", AlgorithmEnum.BFA.getValue()),
                () -> assertEquals("SieveAlgorithm", AlgorithmEnum.SA.getValue())
        );
    }
}
