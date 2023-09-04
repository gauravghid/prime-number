package com.example.prime.number.service;

import java.util.List;

public interface PrimeNumberService {

    List<Integer> getPrimes(int number) ;

    String getAlgorithmName();
}

