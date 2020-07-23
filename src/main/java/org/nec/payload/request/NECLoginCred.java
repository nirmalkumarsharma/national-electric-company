package org.nec.payload.request;

import lombok.Data;

@Data
public class NECLoginCred {
	private String email;
	private String password;
}
