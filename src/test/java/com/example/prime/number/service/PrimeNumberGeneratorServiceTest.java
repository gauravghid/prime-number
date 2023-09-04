package com.example.prime.number.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PrimeNumberGeneratorServiceTest {
    @Mock
    PrimeNumberGeneratorService primeNumberGeneratorService;

    @BeforeEach
    public void init() {
    }

    @Test
    public void testGetPrimes() {
        Map<String, PrimeNumberService> serviceMap = new HashMap<>();
        serviceMap.put("SA", new SieveAlgorithmServiceImpl());
        serviceMap.put("BFA", new BruteForceAlgorithmServiceImpl());
        ReflectionTestUtils.setField(primeNumberGeneratorService, "primeNumberServiceMap", serviceMap);
        List<Integer> primeList = primeNumberGeneratorService.getPrimes(1, "SA");
        assertEquals(new ArrayList<Integer>(), primeList);
    }


}
