package com.zalinius.jishoapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.zalinius.jishoapi.data.ApiResponse;
import com.zalinius.jishoapi.data.Entry;

public class JishoDataSource {
	
	private final String baseUrl = "https://jisho.org/api/v1/search/";
	
	
	private RestTemplate restTemplate;

	public JishoDataSource(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}	

	public List<Entry> getAllEntries(String endpoint, String searchQuery){
		List<Entry> entries = new ArrayList<>();
		boolean morePages = true;
		int page = 1;
		
		while (morePages) {
			ApiResponse pageResponse = search(endpoint, searchQuery, page);
			entries.addAll(pageResponse.getData());
			
			if(pageResponse.getData().isEmpty()) {
				morePages = false;
			}
			
			page++;
		}
		
		return entries;
	}
	
	public ApiResponse quickSearch(String endpoint, String searchQuery) {
		return search(endpoint, searchQuery, 1);
	}
	
	private ApiResponse search(String endpoint, String searchQuery, int page) {
		String urlTemplate = prepFullUrl(endpoint);
		
		assert(page > 0);

		Map<String, Object> params = new HashMap<>();
		params.put("keyword", searchQuery);
		params.put("page", page);
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ApiResponse apiResponse = restTemplate.exchange(
				urlTemplate, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), ApiResponse.class, params).getBody();
		
		return apiResponse;
	}
	
	private String prepFullUrl(String endpoint) {

		String urlTemplate = UriComponentsBuilder.fromHttpUrl(baseUrl + endpoint)
				.queryParam("keyword", "{keyword}")
				.queryParam("page", "{page}")
				.encode()
				.toUriString();

		return urlTemplate;
	}	
	
}
