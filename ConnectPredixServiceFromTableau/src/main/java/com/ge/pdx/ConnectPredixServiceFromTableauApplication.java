package com.ge.pdx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

/**
 * @author T S Karthikeyan
 *
 */
@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class ConnectPredixServiceFromTableauApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectPredixServiceFromTableauApplication.class, args);
	}
	
	/*@EnableGlobalAuthentication
    static class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
        }
    }*/
}
