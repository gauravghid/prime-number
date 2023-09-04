package com.example.prime.number;

import com.example.prime.number.model.ResponseModel;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeNumberApiIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/v1.0/primes/");
    }

    /**
     * Testing prime number generation for Input 15.
     */
    @Test
    public void shouldReturnPrimeNumberTill15() throws UnsupportedEncodingException {
        String url = baseUrl + "15";
        List<Integer> expectedPrimeNumberList = Arrays.asList(2,3,5,7,11,13);
        HttpEntity<Void> requestEntity = new HttpEntity<>(createBasicAuthHeaders());
        Map<String, String> params = new HashMap<>();
        ResponseEntity<ResponseModel> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, ResponseModel.class, params);
        assertEquals(expectedPrimeNumberList,response.getBody().getPrimes());
    }


    /**
     * Testing prime number generation for Input 23 to test the inclusion of upperbound prime number i.e 23.
     */
    @Test
    public void shouldReturnPrimeNumbersIncludingTheNumberProvided () throws UnsupportedEncodingException {
        String url = baseUrl + "23";
        List<Integer> expectedPrimeNumberList = Arrays.asList(2,3,5,7,11,13,17,19,23);
        HttpEntity<Void> requestEntity = new HttpEntity<>(createBasicAuthHeaders());
        Map<String, String> params = new HashMap<>();
        ResponseEntity<ResponseModel> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, ResponseModel.class, params);
        assertEquals(expectedPrimeNumberList,response.getBody().getPrimes());
    }

    /**
     * Testing prime number generation for input 17 using Sieve Algorithm
     */
    @Test
    public void shouldReturnPrimeNumbersUsingSieveAlgorithm () throws UnsupportedEncodingException {
        String url = baseUrl + "17?algorithm=SA";
        List<Integer> expectedPrimeNumberList = Arrays.asList(2,3,5,7,11,13,17);
        HttpEntity<Void> requestEntity = new HttpEntity<>(createBasicAuthHeaders());
        Map<String, String> params = new HashMap<>();
        ResponseEntity<ResponseModel> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, ResponseModel.class, params);
        assertEquals(expectedPrimeNumberList,response.getBody().getPrimes());
    }

    /**
     * Testing prime number generation for input 35 using Brute Force Algorithm
     */
    @Test
    public void shouldReturnPrimeNumbersUsingBruteForceAlgorithm () throws UnsupportedEncodingException {
        String url = baseUrl + "35?algorithm=BFA";
        List<Integer> expectedPrimeNumberList = Arrays.asList(2,3,5,7,11,13,17,19,23,29,31);
        HttpEntity<Void> requestEntity = new HttpEntity<>(createBasicAuthHeaders());
        Map<String, String> params = new HashMap<>();
        ResponseEntity<ResponseModel> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, ResponseModel.class, params);
        assertEquals(expectedPrimeNumberList,response.getBody().getPrimes());
    }

    /**
     * Testing prime number generation for input 1 using Brute Force Algorithm
     */
    @Test
    public void shouldNotReturnAnyPrimeNumbersForInput1 () throws UnsupportedEncodingException {
        String url = baseUrl + "1?algorithm=BFA";
        List<Integer> emptyList = new ArrayList<>() ;
        HttpEntity<Void> requestEntity = new HttpEntity<>(createBasicAuthHeaders());
        Map<String, String> params = new HashMap<>();
        ResponseEntity<ResponseModel> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, ResponseModel.class, params);
        assertEquals(emptyList,response.getBody().getPrimes());
    }

    /**
     * Testing prime number generation for negative integer
     */
    @Test
    public void shouldNotReturnPrimeNumbersForNegativeInput () throws UnsupportedEncodingException {
        String url = baseUrl + "-15?algorithm=BFA";
        List<Integer> emptyList = new ArrayList<>() ;
        HttpEntity<Void> requestEntity = new HttpEntity<>(createBasicAuthHeaders());
        Map<String, String> params = new HashMap<>();
        ResponseEntity<ResponseModel> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, ResponseModel.class, params);
        assertEquals(emptyList,response.getBody().getPrimes());
    }

    /**
     * This method creates HTTP headers for basic Auth
     * @return
     * @throws UnsupportedEncodingException
     */
    HttpHeaders createBasicAuthHeaders() throws UnsupportedEncodingException {
        return new HttpHeaders() {{
            String auth = "user:password";
            byte[] encodedAuth;
            encodedAuth = Base64.encodeBase64(auth.getBytes("US-ASCII"),false);
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }


}
