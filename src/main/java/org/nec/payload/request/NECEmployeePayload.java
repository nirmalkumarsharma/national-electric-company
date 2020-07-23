package org.nec.payload.request;

import lombok.Data;

@Data
public class NECEmployeePayload {
	private String necName;
	private String necEmail;
	private String necPassword;
}
