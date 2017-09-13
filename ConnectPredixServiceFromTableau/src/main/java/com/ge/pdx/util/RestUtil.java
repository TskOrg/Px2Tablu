package com.ge.pdx.util;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author T S Karthikeyan
 *
 */

public class RestUtil {

	public static RestTemplate restTemplate;

	static {
		restTemplate = new RestTemplate();
	}

	public static <T> T get(String url, Map<String, String> params,
			Class<T> classType) {
		if (params == null) {
			return restTemplate.getForObject(url, classType);
		}

		return restTemplate.getForObject(url, classType, params);
	}

	public static <T> Object getWithAuthorizationHeaders(String url,
			Map<String, String> params,Class<T> classType, String userName, String password) {
		Object result = null;
		ResponseEntity<T> response = null;
		try {
			HttpHeaders headers = createHeaders(userName, password);
			//headers.add(CommonConstant.AUTHORIZATION_HEADER_KEY, CommonConstant.API_USER_NAME);
			HttpEntity entity = new HttpEntity(headers);
			response = restTemplate.exchange(url, HttpMethod.GET, entity,
					classType);
			result = getResponseStatus(response);
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}
	public static <T> Object postWithAuthorizationHeaders(String url,
			Map<String, String> params,Class<T> classType, String userName, String password) {
		Object result = null;
		ResponseEntity<T> response = null;
		try {
			HttpHeaders headers = createHeaders(userName, password);
			//headers.add(CommonConstant.AUTHORIZATION_HEADER_KEY, CommonConstant.API_USER_NAME);
			HttpEntity entity = new HttpEntity(headers);
			response = restTemplate.exchange(url, HttpMethod.POST, entity,
					classType);
			result = getResponseStatus(response);
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}
	public static String post(String url, String data) {
		return call(url, data, HttpMethod.POST);
	}

	public static String put(String url, String data) {
		return call(url, data, HttpMethod.PUT);
	}

	private static String call(String url, String data, HttpMethod method) {

		String result = null;
		HttpEntity<String> entity = null;
		ResponseEntity<String> response = null;
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			requestHeaders.add("Authorization", "Basic "); // TODO needs to be changed as per the authentication parameter

			entity = new HttpEntity<String>(data, requestHeaders);

			response = restTemplate.exchange(url, method, entity, String.class);
			result = String.valueOf(getResponseStatus(response));

		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}

	public static String delete(String url) {
		String result = "SUCCESS";
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.delete(url);
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}

	private static Object getResponseStatus(ResponseEntity<?> response) {
		Object responseStatus = null;
		if (response.getStatusCode() == HttpStatus.OK) {
			responseStatus = response.getBody();
		} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			responseStatus = "Bad Credentials";
		} else if (response.getStatusCode() == HttpStatus.BAD_GATEWAY) {
			responseStatus = "Bad Gateway";
		} else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			responseStatus = "Bad request";
		} else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			responseStatus = "Not Found";
		} else {
			responseStatus = "Failed due to problem .. please try again";
		}
		return responseStatus;
	}
	
	public static void main(String args[])
	{
		String url = "http://localhost:8088/usercrew/settings/8593";
		/*SettingsDTO response = (SettingsDTO) Rest.getWithAuthorizationHeaders(url, null, SettingsDTO.class);
		System.out.println(response);*/
	}
	
	public static HttpHeaders createHeaders(String username, String password){
		   return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		      }};
		}
	
	public static HttpHeaders createAuthHeaders(String token, String tokenType){
		   return new HttpHeaders() {{
		        
		         String authHeader = tokenType + " " + token;
		         set( "Authorization", authHeader );
		      }};
		}
}
