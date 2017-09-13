package com.ge.pdx;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author T S Karthikeyan
 *
 */

@Configuration
public class GatewayConfigurer {
	
	@Value("${ge.proxy.url}")
	private String proxyURL;

	@Value("${ge.proxy.port}")
	private String restProxyPort;
	
	@Bean(name = "restTemplate")
	@Profile("local")
	public RestTemplate serviceNowRestTemplateLocal() {
		System.out.println("Creating RestTemplate Object in Local..");
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		System.out.println("Setting Proxy for Local Profile");
		Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyURL, Integer.parseInt(restProxyPort)));
		requestFactory.setProxy(proxy);
		requestFactory.setConnectTimeout(0);
		requestFactory.setReadTimeout(30*1000);
		RestTemplate restTemplateObj = new RestTemplate();
		restTemplateObj.setRequestFactory(requestFactory);
		System.out.println("Returning RestTemplate for Local Profile...");
		return restTemplateObj;
	}
	
	@Bean(name = "restTemplate")
	@Profile("!local")
	public RestTemplate serviceNowRestTemplateCloud() {
		System.out.println("Creating RestTemplate Object in Cloud....");
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(0);
		requestFactory.setReadTimeout(30*1000);
		RestTemplate restTemplateObj = new RestTemplate();
		restTemplateObj.setRequestFactory(requestFactory);
		System.out.println("Returning RestTemplate for Cloud Profile...");
		return restTemplateObj;
	}

}
