package org.nec.service;

import org.nec.payload.response.TokenResponse;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface NECLoginService {
	TokenResponse authenticateUser(String email, String password) throws JsonProcessingException;
}
