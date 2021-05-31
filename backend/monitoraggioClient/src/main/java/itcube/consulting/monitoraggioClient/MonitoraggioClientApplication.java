package itcube.consulting.monitoraggioClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import itcube.consulting.monitoraggioClient.security.JWTAuthorizationFilter;
import itcube.consulting.monitoraggioClient.services.StartMailAfterDownBE;

@SpringBootApplication
@EnableScheduling
public class MonitoraggioClientApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MonitoraggioClientApplication.class, args);
		
//		StartMailAfterDownBE start = new StartMailAfterDownBE();
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				/*.antMatchers(HttpMethod.POST, "/be/main/login").permitAll()
				.antMatchers(HttpMethod.POST, "/be/main/registrazione").permitAll()
				.anyRequest().authenticated();*/
				.anyRequest().permitAll();
		}
	}
}
