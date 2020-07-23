package org.nec.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.nec.entity.NECEmployee;
import org.nec.payload.NECEmployeeDetails;
import org.nec.payload.request.NECEmployeePayload;
import org.nec.payload.response.NECRegistrationResponse;
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

	@Override
	public List<NECEmployeeDetails> getUsers() {
		List<NECEmployee> employees = necEmployeeRepository.findAll();
		List<NECEmployeeDetails> necEmployeeDetails = new ArrayList<>();
		employees.stream().forEach(x-> necEmployeeDetails.add( new NECEmployeeDetails(x.getNecName(), x.getNecEmail())));
		return necEmployeeDetails;
	}
}
