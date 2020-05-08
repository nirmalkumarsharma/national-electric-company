package org.nec.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NECKeyConfigProvider {

	@Bean
	@ConfigurationProperties(prefix = "nec")
	public NECKeyConfig necKeyConfig() {
		return new NECKeyConfig();
	}
}
