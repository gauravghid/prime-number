package com.example.prime.number.service;

import com.example.prime.number.util.AlgorithmEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("BruteForceAlgorithm")
@Slf4j
public class BruteForceAlgorithmServiceImpl implements PrimeNumberService{

    /**
     * method checking divisibility of a given number, n , by all numbers less than number .
     * If n is divisible by any number other than 1 and itself, it is not prime.
     * The time complexity of a brute force algorithm is often proportional to the input size. Brute force algorithms are simple and consistent, but very slow.
     * @param number
     * @return List<Integers>
     */
    @Override
    @Cacheable(value="result")
    public List<Integer> getPrimes(int number) {
        log.info("in BruteForce algorithm");
        return IntStream.rangeClosed(2, number)
                .filter(BruteForceAlgorithmServiceImpl::isPrime).boxed()
                .collect(Collectors.toList());
    }

    /**
     * method to returm algorithm
     * @return String
     */
    @Override
    public String getAlgorithmName() {
        return AlgorithmEnum.BFA.getValue();
    }

    /**
     * method to determine if given number is prime or not
     * @param number
     * @return boolean
     */
    private static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))
                .allMatch(n -> number % n != 0);
    }
}
