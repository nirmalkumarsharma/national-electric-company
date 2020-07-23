package org.nec.service;

import java.util.List;

import org.nec.payload.NECEmployeeDetails;
import org.nec.payload.request.NECEmployeePayload;
import org.nec.payload.response.NECRegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface NECRegistrationService {
	NECRegistrationResponse registerUser(NECEmployeePayload necEmployeePayload);

	List<NECEmployeeDetails> getUsers();
}
