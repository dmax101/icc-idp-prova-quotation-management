package br.inatel.quotationmanagement.services;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.CachingHttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.inatel.quotationmanagement.controllers.form.post.StockManagerPostForm;

@Service
public class RestApiService {

    private RestApiService() {
    }
    
    private static RestTemplate buildCachingRestTemplate() {
        CacheConfig cacheConfig = CacheConfig.custom()
        .build();
        HttpClient cachingClient = CachingHttpClientBuilder.create()
        .setCacheConfig(cacheConfig)
        .build();
        
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(cachingClient);
        
        return new RestTemplate(requestFactory);
    }
    
    public static List<?> getResponseFromStockManager() {

        RestTemplate restTemplate = buildCachingRestTemplate();
        String url = "http://localhost:8080/stock";

        return restTemplate.getForObject(url, List.class);
    }

    public static ResponseEntity<List<StockManagerPostForm>> postRequestToStockManagerNotification() {

        RestTemplate restTemplate = buildCachingRestTemplate();

		List<StockManagerPostForm> posts = restTemplate.postForObject(
			"http://localhost:8080/notification",
			new StockManagerPostForm("localhost", 8081),
			List.class);

        return ResponseEntity.ok().body(posts);
    }
}
