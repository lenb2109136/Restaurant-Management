package com.example.nienluannganh.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.example.nienluannganh.jsontoken.JWT;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Security {
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity hc) throws Exception {
		System.out.println("khỏi tạo đối tượng security thành công");
		
		hc.csrf().disable ()
			.authorizeHttpRequests(auth -> auth
					
//	                .requestMatchers("/login").permitAll()
//	                .requestMatchers("/xinchao").hasAuthority("admin")
//	                .anyRequest().authenticated()
					.anyRequest().permitAll()
					
	            );
//		hc.oauth2ResourceServer(t -> 
//		t.jwt(t2 -> t2.decoder(decoder()))
////				);
		return hc.build();
	}
	@Bean
	public JwtDecoder decoder() {
		JWT j= new JWT();
		SecretKeySpec s= new SecretKeySpec(j.getSerectkey().getBytes(), "HS512");
		return NimbusJwtDecoder
				.withSecretKey(s)
				.macAlgorithm(MacAlgorithm.HS512)
				.build();
				
	}
}
