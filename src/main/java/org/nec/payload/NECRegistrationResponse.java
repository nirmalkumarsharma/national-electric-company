package org.nec.payload;

import lombok.Data;

@Data
public class NECRegistrationResponse {
	private String statusCode;
	private String message;
}
