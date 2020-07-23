package org.nec.controller;

import java.util.List;

import org.nec.payload.NECEmployeeDetails;
import org.nec.payload.request.NECEmployeePayload;
import org.nec.payload.request.NECLoginCred;
import org.nec.payload.response.NECRegistrationResponse;
import org.nec.payload.response.TokenResponse;
import org.nec.service.NECLoginService;
import org.nec.service.NECRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/user")
public class NECUserController {
	
	@Autowired
	private NECRegistrationService necRegistrationService;
	
	@Autowired
	private NECLoginService necLoginService;
	
	@PostMapping(path = "/register", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<NECRegistrationResponse> registerNEC(@RequestBody NECEmployeePayload necEmployeePayload) {
		NECRegistrationResponse registeredUser = necRegistrationService.registerUser(necEmployeePayload);
		return new ResponseEntity<>(registeredUser, HttpStatus.OK);
	}
	
	@PostMapping(path = "/login", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<TokenResponse> loginNEC(@RequestBody NECLoginCred necLoginCred) {
		TokenResponse tokenResponse;
		try {
			tokenResponse = necLoginService.authenticateUser(necLoginCred.getEmail(), necLoginCred.getPassword());
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "/users",  produces = {"application/json"})
	public ResponseEntity<List<NECEmployeeDetails>> getUsersNEC() {
		List<NECEmployeeDetails> users = necRegistrationService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
