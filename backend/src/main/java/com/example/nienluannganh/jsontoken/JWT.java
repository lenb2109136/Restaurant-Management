package com.example.nienluannganh.jsontoken;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Component
public class JWT {
	private static String serectkey="6fonEJ2Cji1pFH0Vyun41iOFwHK0kzhRAjOKuzhG1eK3VXF7iC2r6bndfBtEjulJ\r\n";
	
	
	
	public static String getSerectkey() {
		return serectkey;
	}

	public static void setSerectkey(String serectkey) {
		JWT.serectkey = serectkey;
	}

	public String createToken(String id, String role) {
		JWSHeader header= new JWSHeader(JWSAlgorithm.HS512);
		JWTClaimsSet claim= new JWTClaimsSet.Builder()
				.subject(id+"")
				.issuer("localhost:8080/nienluannganh")
				.issueTime(new Date())
				.expirationTime(new Date(Instant.now().plus(5,ChronoUnit.HOURS).toEpochMilli()))
				.claim("scope", "admin")
				.build();
		Payload payload= new Payload(claim.toJSONObject());
		JWSObject jwt= new JWSObject(header, payload);
		try {
			jwt.sign(new MACSigner(serectkey.getBytes()));
			return jwt.serialize();
		} catch (KeyLengthException e) {
			e.printStackTrace();
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	public boolean testtoken(String token) throws JOSEException, ParseException {
		JWSVerifier jw= new MACVerifier(serectkey.getBytes());
		SignedJWT s= SignedJWT.parse(token);
		return s.verify(jw);
	}
	
	
}
