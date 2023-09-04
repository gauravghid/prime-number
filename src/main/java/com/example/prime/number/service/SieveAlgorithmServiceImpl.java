package com.example.prime.number.service;

import com.example.prime.number.util.AlgorithmEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("SieveAlgorithmService")
@Slf4j
public class SieveAlgorithmServiceImpl implements PrimeNumberService {
    /**
     * Sieve is a simple and ancient algorithm used to find the prime numbers up to any given limit.
     * It is one of the most efficient ways to find small prime numbers.
     * For a given upper limit n the algorithm works by iteratively marking the multiples of primes as composite, starting from 2.
     * @param number
     * @return List<Integers>
     */
    @Override
    @Cacheable(value="result")
    public List<Integer> getPrimes(int number) {
        log.info("In Sieve algorithm");
        IntStream primes = IntStream.range(2, number+1);
        IntFunction<IntPredicate> sieve = n -> i -> i == n || i % n != 0;
        primes = primes.filter(sieve.apply(2));
        for (int i = 3; i * i <= number; i += 2)
            primes = primes.filter(sieve.apply(i));
        return primes.boxed().collect(Collectors.toList());
    }

    /**
     * method to return the algorithm name.
     * @return String
     */
    @Override
    public String getAlgorithmName() {
        return AlgorithmEnum.SA.getValue();
    }
}
