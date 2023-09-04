package com.example.prime.number.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BruteForceAlgorithmServiceImplTest {

    @InjectMocks
    private BruteForceAlgorithmServiceImpl bruteForceAlgorithmService;

    @Test
    void testGetPrimes()
    {
        List<Integer> primeList = bruteForceAlgorithmService.getPrimes(24);
        assertEquals(Arrays.asList(2,3,5,7,11,13,17,19,23),primeList);
    }

    @Test
    void testGetPrimesForUpperBoundInclusion () {
        List<Integer> primeList = bruteForceAlgorithmService.getPrimes(29);
        assertEquals(Arrays.asList(2,3,5,7,11,13,17,19,23,29),primeList);
    }

    @Test
    public  void shouldReturnAlgorithmName() {
        String name = bruteForceAlgorithmService.getAlgorithmName();
        assertEquals("BruteForceAlgorithm",name);
    }

}
