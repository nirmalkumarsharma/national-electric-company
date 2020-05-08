package org.nec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"org.nec.entity"})
@EnableJpaRepositories(basePackages = {"org.nec.repository"})
public class NationalElectricCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NationalElectricCompanyApplication.class, args);
	}

}
