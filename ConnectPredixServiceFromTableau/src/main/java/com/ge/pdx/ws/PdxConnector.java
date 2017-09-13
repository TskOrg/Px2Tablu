package com.ge.pdx.ws;

import java.net.URI;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ge.pdx.bean.BearerToken;
import com.ge.pdx.util.RestUtil;
import com.google.common.base.Strings;

/**
 * @author T S Karthikeyan
 *
 */

@Component
public class PdxConnector {

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	public BearerToken fetchAPMToken(String loginUrl, String authUserName, String authPassword, String username, String password, String grantType) throws Exception {
		BearerToken result = null;

		try {
			
			System.out.println("Fetching Bearer Token ................");

			//restTemplate = new RestTemplate();

			LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
			params.add("grant_type", grantType);
			params.add("username", username);
			params.add("password", password);

			// UriComponentsBuilder builder =
			// UriComponentsBuilder.fromHttpUrl(loginUrl);
			/*
			 * JSONObject requestJson = new JSONObject();
			 * requestJson.put("grant_type", "password");
			 * requestJson.put("username",
			 * "0694ad33-3977-4b27-9c14-82acf07b3da1_ingestor");
			 * requestJson.put("password", "Password1");
			 */

			//String requestJson = "{\"grant_type\":\"password\",\"username\":\"0694ad33-3977-4b27-9c14-82acf07b3da1_ingestor\",\"password\":\"Password1\"}";

			//System.out.println(requestJson.toString());

			// URI serviceNowURI = builder.build(true).encode().toUri();
			HttpHeaders headers = RestUtil.createHeaders(authUserName, authPassword);

			// set the content type to application/x-www-form-urlencoded
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			// set the content type to form-data
			// headers.add("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);

			// this is for sending data in form-data
			HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);

			// this is for sending data in request body as Raw type
			// HttpEntity<String> request = new
			// HttpEntity<>(requestJson,headers);

			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			result = restTemplate.exchange(loginUrl, HttpMethod.POST, requestEntity, BearerToken.class).getBody();

			if (result == null || Strings.isNullOrEmpty(result.getAccess_token())) {
				throw new Exception("Exception Occurred during fetchOAuth2Token. No Access Token fetched.");
			}
		} catch (Exception e) {
			throw new Exception("Exception Occurred during fetchOAuth2Token. " + e.getMessage());
		}
		return result;
	}

	public com.ge.pdx.bean.Component[] fetchComponents(String assetURL, String assetTenant, String componentName, String token,
			String tokenType) throws Exception {

		com.ge.pdx.bean.Component[] component = null;

		try {
			
			System.out.println("Fetching Asset Components ...........");

			RestTemplate compTemplate = new RestTemplate();

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(assetURL).queryParam("components", componentName);
			
			URI assetURI = builder.build(true).encode().toUri();

			HttpHeaders header = RestUtil.createAuthHeaders(token, tokenType);
			
			header.setContentType(MediaType.APPLICATION_JSON);	
			header.add("tenant", assetTenant);

			HttpEntity<String> requestEntity = new HttpEntity<>("", header);

			header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			
			requestEntity.getHeaders();

			// SKIP SSL CERTIFICATE VERIFICATION IN SPRING REST TEMPLATE - START
			// Reference http://blog.codeleak.pl/2016/02/skip-ssl-certificate-verification-in.html
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
			        .loadTrustMaterial(null, acceptingTrustStrategy)
			        .build();
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
			
			HttpHost proxy = new HttpHost("iss-americas-pitc-alpharettaz.proxy.corporate.ge.com", 80);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
			
			CloseableHttpClient httpClient = HttpClients.custom().setRoutePlanner(routePlanner)
			        .setSSLSocketFactory(csf)
			        .build();
			
			
			HttpComponentsClientHttpRequestFactory requestFactory =
			        new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(httpClient);
			
			compTemplate.setRequestFactory(requestFactory);
			
			// SKIP SSL CERTIFICATE VERIFICATION IN SPRING REST TEMPLATE - END
			
			component = compTemplate.exchange(assetURI, HttpMethod.GET, requestEntity, com.ge.pdx.bean.Component[].class)
					.getBody();

			if (component == null || Strings.isNullOrEmpty(component[0].getSourceKey())) {
				throw new Exception("Exception Occurred during fetchComponent. No Asset component fetched.");
			}

		} catch (Exception e) {
			throw new Exception("Exception Occurred during fetchComponents. " + e.getMessage());
		}

		return component;

	}
	
	
	public com.ge.pdx.bean.Alarms fetchContents(String alarmURL, String alarmTenant, String token,
			String tokenType) throws Exception {

		com.ge.pdx.bean.Alarms alarm = null;

		try {
			
			System.out.println("Fetching Alarm Content ...........");

			RestTemplate compTemplate = new RestTemplate();

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(alarmURL);
			
			URI alarmURI = builder.build(true).encode().toUri();

			HttpHeaders header = RestUtil.createAuthHeaders(token, tokenType);
			
			header.setContentType(MediaType.APPLICATION_JSON);	
			header.add("tenant", alarmTenant);
			
			// this is for sending data in request body as Raw type
			String requestJson = "{\"filters\":[]}";
			HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, header);

			header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			
			requestEntity.getHeaders();
			

			// SKIP SSL CERTIFICATE VERIFICATION IN SPRING REST TEMPLATE - START
			// Reference http://blog.codeleak.pl/2016/02/skip-ssl-certificate-verification-in.html
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
			        .loadTrustMaterial(null, acceptingTrustStrategy)
			        .build();
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
			
			HttpHost proxy = new HttpHost("iss-americas-pitc-alpharettaz.proxy.corporate.ge.com", 80);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
			
			CloseableHttpClient httpClient = HttpClients.custom().setRoutePlanner(routePlanner)
			        .setSSLSocketFactory(csf)
			        .build();
			
			
			HttpComponentsClientHttpRequestFactory requestFactory =
			        new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(httpClient);
			
			compTemplate.setRequestFactory(requestFactory);
			
			// SKIP SSL CERTIFICATE VERIFICATION IN SPRING REST TEMPLATE - END
			
			alarm = compTemplate.exchange(alarmURI, HttpMethod.POST, requestEntity, com.ge.pdx.bean.Alarms.class)
					.getBody();

			if (alarm == null || Strings.isNullOrEmpty(alarm.getTotalElements())) {
				throw new Exception("Exception Occurred during fetchContent. No Alarm Content fetched.");
			}

		} catch (Exception e) {
			throw new Exception("Exception Occurred during fetchContents. " + e.getMessage());
		}

		return alarm;

	}
	
	public static void main(String args[]){
		
		String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImxlZ2FjeS10b2tlbi1rZXkiLCJ0eXAiOiJKV1QifQ.eyJqdGkiOiI0NzFjZDIyOTA3NjM0YzkyYWFjZWFhNzFmMDI0NDc1MCIsInN1YiI6ImEwN2E1NWM5LTFiYzEtNDc1My1iZjFkLTQwMDc5ZDFjNTBjYyIsInNjb3BlIjpbInBhc3N3b3JkLndyaXRlIiwib3BlbmlkIl0sImNsaWVudF9pZCI6ImluZ2VzdG9yLjI2YjMwNWVjLWY4MDEtNGU3Ni1iMDNhLWVmNDA5NDAzNTQ2ZS4zNTlhODJmNi01MDBhLTRmMjctYjYzYS02YWRmYzFlODE5ZjEiLCJjaWQiOiJpbmdlc3Rvci4yNmIzMDVlYy1mODAxLTRlNzYtYjAzYS1lZjQwOTQwMzU0NmUuMzU5YTgyZjYtNTAwYS00ZjI3LWI2M2EtNmFkZmMxZTgxOWYxIiwiYXpwIjoiaW5nZXN0b3IuMjZiMzA1ZWMtZjgwMS00ZTc2LWIwM2EtZWY0MDk0MDM1NDZlLjM1OWE4MmY2LTUwMGEtNGYyNy1iNjNhLTZhZGZjMWU4MTlmMSIsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsInVzZXJfaWQiOiJhMDdhNTVjOS0xYmMxLTQ3NTMtYmYxZC00MDA3OWQxYzUwY2MiLCJvcmlnaW4iOiJ1YWEiLCJ1c2VyX25hbWUiOiIwNjk0YWQzMy0zOTc3LTRiMjctOWMxNC04MmFjZjA3YjNkYTFfaW5nZXN0b3IiLCJlbWFpbCI6IjA2OTRhZDMzLTM5NzctNGIyNy05YzE0LTgyYWNmMDdiM2RhMV9pbmdlc3RvckBhcG1wcmVwcm9kLmFwbS5hd3MtdXN3MDItcHIucHJlZGl4LmlvIiwiYXV0aF90aW1lIjoxNDk4ODM2MDUzLCJyZXZfc2lnIjoiODM2Mjc0YWYiLCJpYXQiOjE0OTg4MzYwNTMsImV4cCI6MTQ5ODkyMjQ1MywiaXNzIjoiaHR0cHM6Ly9mNmQwNTI0ZC0yOGQxLTRhZjgtYTIxYy0zYzc3OTc5MGFmZjQucHJlZGl4LXVhYS5ydW4uYXdzLXVzdzAyLXByLmljZS5wcmVkaXguaW8vb2F1dGgvdG9rZW4iLCJ6aWQiOiJmNmQwNTI0ZC0yOGQxLTRhZjgtYTIxYy0zYzc3OTc5MGFmZjQiLCJhdWQiOlsicGFzc3dvcmQiLCJpbmdlc3Rvci4yNmIzMDVlYy1mODAxLTRlNzYtYjAzYS1lZjQwOTQwMzU0NmUuMzU5YTgyZjYtNTAwYS00ZjI3LWI2M2EtNmFkZmMxZTgxOWYxIiwib3BlbmlkIl19.uJF9CvraSdSgcZEhudim3SkieH_09cFLvwSMM1F__RvFiFVFZ9jVmQ_MX0g4p9uY3z-SBqluo5d2hLSVzZXnNKqPUcB25efi6rgUFW-nxfkVTA4RAdLhZLXr4ece-UDH7BnrJGHaiU0Frgce_ouRiNRFyTriTFPcHqOAMsY95dfDOBhoQHyVGQhDF1JeKTQgeAxi8iGCtPE8KmTq_gg_vaZ82Dv6vIWbWO4X-VmS-DRzsaMdBQ9I-zZQgAivz0CybUkbBATMXvvVL9DE7tA6jaoLrYQlnasINJPvkVR74XlwkkL56E1BDyDjXQGqrA_3wAWNMa1hO12F9i3MoMwgyw";
		try {
			System.out.println(new PdxConnector().fetchComponents("https://apm-asset-apmpreprod.apm.aws-usw02-pr.predix.io/v1/assets", "0694ad33-3977-4b27-9c14-82acf07b3da1", "BASIC", token, "bearer"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
