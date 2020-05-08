package org.nec.service.Impl;

import java.util.List;

import org.nec.config.NECKeyConfig;
import org.nec.entity.NECEmployee;
import org.nec.payload.NECEmployeeDetails;
import org.nec.payload.TokenResponse;
import org.nec.repository.NECEmployeeRepository;
import org.nec.service.NECLoginService;
import org.nec.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class NECLoginServiceImpl implements NECLoginService {

	@Autowired
	private NECEmployeeRepository necEmployeeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private NECKeyConfig necKeyConfig;
	
	@Override
	public TokenResponse authenticateUser(String email, String password) throws JsonProcessingException {
		List<NECEmployee> necEmployees = necEmployeeRepository.findByNecEmail(email);//, passwordEncoder.encode(password));
		if(necEmployees == null || necEmployees.isEmpty()) {
			throw new BadCredentialsException("Invalid username");
		}
		NECEmployee necEmployee = necEmployees.get(0);
		if(!passwordEncoder.matches(password, necEmployee.getNecPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
		NECEmployeeDetails necEmployeeDetails = new NECEmployeeDetails();
		necEmployeeDetails.setEmail(necEmployee.getNecEmail());
		necEmployeeDetails.setName(necEmployee.getNecName());
		String bearerToken = JWTUtil.generateBearerToken(necKeyConfig, necEmployeeDetails);
		TokenResponse tokenResponse = new TokenResponse();
		tokenResponse.setBearerToken("Bearer "+bearerToken);
		return tokenResponse;
	}
}
