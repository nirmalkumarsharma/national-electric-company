package org.nec.config;

import lombok.Data;

@Data
public class NECKeyConfig {
	private long jwtExpirationMs; 
	private String jwtSecret;
}
