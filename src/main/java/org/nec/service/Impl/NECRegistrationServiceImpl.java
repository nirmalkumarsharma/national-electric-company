package org.nec.service.Impl;

import org.nec.entity.NECEmployee;
import org.nec.payload.NECEmployeePayload;
import org.nec.payload.NECRegistrationResponse;
import org.nec.repository.NECEmployeeRepository;
import org.nec.service.NECRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class NECRegistrationServiceImpl implements NECRegistrationService {
	
	@Autowired
	private NECEmployeeRepository necEmployeeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public NECRegistrationResponse registerUser(NECEmployeePayload necEmployeePayload) {
		NECEmployee necEmployee = new NECEmployee();
		necEmployee.setNecName(necEmployeePayload.getNecName());
		necEmployee.setNecEmail(necEmployeePayload.getNecEmail());
		necEmployee.setNecPassword(passwordEncoder.encode(necEmployeePayload.getNecPassword()));
		try {
			necEmployeeRepository.save(necEmployee);
		} catch (Exception ex) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		NECRegistrationResponse necRegistrationResponse = new NECRegistrationResponse();
		necRegistrationResponse.setStatusCode(HttpStatus.OK.toString());
		necRegistrationResponse.setMessage("User Registered Successfully");
		return necRegistrationResponse;
	}
}
