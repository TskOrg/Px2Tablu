package com.ge.pdx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ge.pdx.bean.Alarms;
import com.ge.pdx.bean.BearerToken;
import com.ge.pdx.bean.Component;
import com.ge.pdx.ws.PdxConnector;

/**
 * @author T S Karthikeyan
 *
 */

@Service
public class ConnectPredixService {
	
	@Autowired
	private PdxConnector pdxCon;
	
	@Value("${pdx.asset.token.url}")
	String assetTokenUrl;
	
	@Value("${pdx.asset.auth.username}")
	String assetAuthUserName;

	@Value("${pdx.asset.auth.password}")
	String assetAuthPassword;
	
	@Value("${pdx.asset.username}")
	String assetUsername;
	
	@Value("${pdx.asset.password}")
	String assetPassword;
	
	@Value("${pdx.asset.grant_type}")
	String assetGrantType;
	
	@Value("${pdx.asset.url}") 
	String assetURL;
	
	@Value("${pdx.asset.component}")
	String componentName;
	
	@Value("${pdx.asset.tenant}")
	String tenant;
	
	@Value("${pdx.alarm.token.url}")
	String alarmTokenUrl;
	
	@Value("${pdx.alarm.auth.username}")
	String alarmAuthUserName;

	@Value("${pdx.alarm.auth.password}")
	String alarmAuthPassword;
	
	@Value("${pdx.alarm.username}")
	String alarmUsername;
	
	@Value("${pdx.alarm.password}")
	String alarmPassword;
	
	@Value("${pdx.alarm.grant_type}")
	String alarmGrantType;
	
	@Value("${pdx.alarm.url}") 
	String alarmURL;
	
	@Value("${pdx.alarm.tenant}")
	String alarmTenant;
	
	@Autowired
	private ApplicationContext applicationContext;

	@Cacheable(value = "asset_tokens")
	public BearerToken getAssetBearerToken() throws Exception {
		return pdxCon.fetchAPMToken(assetTokenUrl, assetAuthUserName, assetAuthPassword, assetUsername, assetPassword, assetGrantType);
	}
	
	@Cacheable(value = "alarm_tokens")
	public BearerToken getAlarmBearerToken() throws Exception {
		return pdxCon.fetchAPMToken(alarmTokenUrl, alarmAuthUserName, alarmAuthPassword, alarmUsername, alarmPassword, alarmGrantType);
	}
	
	public Component[] getAssetComponents() throws Exception{
		System.out.println("Fetching Asset Components");
		
		// call the cached method using Spring Proxy
		BearerToken token = getSpringProxy().getAssetBearerToken();
		
		return pdxCon.fetchComponents(assetURL, tenant, componentName, token.getAccess_token(), token.getToken_type());
	}
	
	public Alarms getAlarmContents() throws Exception{
		System.out.println("Alarm Contents");
		
		// call the cached method using Spring Proxy
		BearerToken token = getSpringProxy().getAlarmBearerToken();
		
		return pdxCon.fetchContents(alarmURL, alarmTenant, token.getAccess_token(), token.getToken_type());
	}
	
	/**
	 * Use proxy to hit cache 
	 */
	private ConnectPredixService getSpringProxy() {
	    return applicationContext.getBean(ConnectPredixService.class);
	}
	
}
