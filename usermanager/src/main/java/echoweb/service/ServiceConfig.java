package echoweb.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
	
	@Bean
	public WebUserService webUserService() {
		return new WebUserServiceNoDBImpl();
	}

}
