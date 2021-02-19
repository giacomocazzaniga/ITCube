package itcube.consulting.monitoraggioClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import itcube.consulting.monitoraggioClient.security.JWTAuthorizationFilter;

@SpringBootApplication
public class MonitoraggioClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoraggioClientApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				/*.antMatchers(HttpMethod.POST, "/be/main/login").permitAll()
				.antMatchers(HttpMethod.POST, "/be/main/registrazione").permitAll()
				.anyRequest().authenticated();*/
				.anyRequest().permitAll();
		}
	}

}
