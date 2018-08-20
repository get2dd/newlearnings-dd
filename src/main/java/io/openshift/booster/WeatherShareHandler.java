package io.openshift.booster;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.test.web.reactive.server.WebTestClient;

@Component
public class WeatherShareHandler {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherShareHandler.class);
    
    
	public Mono<ServerResponse> weather(ServerRequest request) {
	    LOG.info("Got request for Weather: {}", request.queryParam("city").get());
	    String city = request.queryParam("city").get();
	    //http://api.openweathermap.org/data/2.5/weather?zip=23059,us&APPID=44286fd45949007ec21a96cb364762d9
	    
         RestTemplate restTemplate = new RestTemplate();
         String fooResourceUrl = "http://api.openweathermap.org/data/2.5/weather?APPID=44286fd45949007ec21a96cb364762d9&zip=";
         ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + city, String.class);
         LOG.info("ResponseBode: " + response.getBody());
            
		return ServerResponse.ok().body(BodyInserters.fromObject(response.getBody()));
	}

	public Mono<ServerResponse> sharePrice(ServerRequest request) {
	    LOG.info("Got request for Share Price: {}", request.queryParams());
	    String symbol = request.queryParam("symbol").get();
	    //https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=UPIJQEO6WZMJFN1P
	     RestTemplate restTemplate = new RestTemplate();
         String fooResourceUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&apikey=UPIJQEO6WZMJFN1P&symbol=";
         ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + symbol, String.class);
         LOG.info("ResponseBode: " + response.getBody());
         
		return ServerResponse.ok().body(BodyInserters.fromObject("World"));
	}
}