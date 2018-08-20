package io.openshift.booster;                                                    

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BoosterTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void testEcho() {
		this.webClient.get().uri("/weather?city={city}", "23059")
				.accept(MediaType.TEXT_PLAIN)
                .exchange()
				.expectBody(String.class);
	}

	@Test
	public void testKiran() {
		this.webClient.get().uri("/shareprice?symbol={symbol}", "COF")
				.accept(MediaType.ALL)
				.exchange()
				.expectBody(String.class);
	}


}