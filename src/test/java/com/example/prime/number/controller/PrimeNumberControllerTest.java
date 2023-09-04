package com.example.prime.number.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.prime.number.service.PrimeNumberGeneratorService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;

@WebMvcTest(controllers = {PrimeNumberController.class})
public class PrimeNumberControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    PrimeNumberGeneratorService primeNumberGeneratorService;

    @Test
    void shouldReturnSuccessfulJsonResponse() throws Exception {
        final ResultActions result =
                mvc.perform(
                        get("/api/v1.0/primes/9").headers(createBasicAuthHeaders()).accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void shouldReturnSuccessfulXmlResponse() throws Exception {
        final ResultActions result =
                mvc.perform(
                        get("/api/v1.0/primes/9").headers(createBasicAuthHeaders()).accept(MediaType.APPLICATION_XML));
        result
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML));

    }


    @Test
    void shouldReturnUnauthorizedError() throws Exception {
        final ResultActions result =
                mvc.perform(
                        get("/api/v1.0/primes/9").accept(MediaType.APPLICATION_XML));
        result
                .andExpect(status().is4xxClientError());

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
