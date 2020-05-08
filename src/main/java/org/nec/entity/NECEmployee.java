package org.nec.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import lombok.Data;

@Entity
@Data
public class NECEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID necUUID;
	
	@Column(nullable = false)
	private String necName;
	
	@Email
	@Column(nullable = false, unique = true)
	private String necEmail;
	
	@Column(nullable = false)
	private String necPassword;
}
