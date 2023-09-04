package com.example.prime.number.controller;

import com.example.prime.number.model.ResponseModel;
import com.example.prime.number.service.PrimeNumberGeneratorService;
import com.example.prime.number.util.AlgorithmEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1.0/primes")
public class PrimeNumberController {

    @Autowired
    PrimeNumberGeneratorService primeNumberGeneratorService;

    /**
     * Rest controller to fetch the prime numbers till a given number(inclusive)
     * and return the response based on the media type requested.
     * @param number
     * @param algorithmKey
     * @return
     */
    @GetMapping(value = {"/{number}"}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseModel> fetchPrimeNumbers(@PathVariable("number") int number, @RequestParam(value = "algorithm", required = false, defaultValue = "SA") String algorithmKey) {
        List<Integer> range = primeNumberGeneratorService.getPrimes(number, AlgorithmEnum.valueOf(algorithmKey).getValue());
        return new ResponseEntity<>(ResponseModel.builder()
                .initial(number)
                .primes(range)
                .build(), HttpStatus.OK);
    }
}
