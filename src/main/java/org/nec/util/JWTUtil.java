package org.nec.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import org.nec.config.NECKeyConfig;
import org.nec.payload.NECEmployeeDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTUtil {
	
	public static String generateBearerToken(NECKeyConfig necKeyConfig, NECEmployeeDetails necEmployeeDetails) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		byte[] secret = Base64.getDecoder().decode(necKeyConfig.getJwtSecret());
		Instant now = Instant.now();
		String jwtToken = Jwts.builder().setHeaderParam("typ", "JWT")
										.setSubject("NEC Login")
										.setAudience("NEC Portal Users")
										.setIssuer("National Electric Company")
										.setIssuedAt(Date.from(now))
										.setExpiration(Date.from(now.plus(necKeyConfig.getJwtExpirationMs(), ChronoUnit.MILLIS)))
										.setSubject(mapper.writeValueAsString(necEmployeeDetails))
										.signWith(Keys.hmacShaKeyFor(secret))
										.compact();
		return jwtToken;
	}
}
