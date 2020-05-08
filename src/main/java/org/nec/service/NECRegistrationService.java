package org.nec.service;

import org.nec.payload.NECEmployeePayload;
import org.nec.payload.NECRegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface NECRegistrationService {
	NECRegistrationResponse registerUser(NECEmployeePayload necEmployeePayload);
}
