package com.example.prime.number.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PrimeNumberGeneratorService {
    private final Map<String,PrimeNumberService> primeNumberServiceMap;

    /**
     * Constructor to set all the algorithm beans in the map.
     * @param primeNumberServiceList
     */
    @Autowired
    public PrimeNumberGeneratorService(List<PrimeNumberService> primeNumberServiceList) {
        this.primeNumberServiceMap = primeNumberServiceList.stream().collect(Collectors.toMap(PrimeNumberService::getAlgorithmName, Function.identity()));
    }

    /**
     * Method to call the relevant Algorithm for generating prime numbers based on the params provide.
     * @param number
     * @param algorithmName
     * @return
     */
    public List<Integer> getPrimes(int number, String algorithmName)
    {
        PrimeNumberService primeNumberService = primeNumberServiceMap.get(algorithmName);
        return primeNumberService.getPrimes(number);
    }
}
