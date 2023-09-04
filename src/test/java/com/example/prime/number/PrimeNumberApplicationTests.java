package com.example.prime.number;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrimeNumberApplicationTests {

	@Test
	void contextLoads() {
	}

	/**
	 *  Test class added ONLY to cover main() invocation not covered by application tests.
	 */
	@Test
	public void main() {
		PrimeNumberApplication.main(new String[] {});
	}

}
