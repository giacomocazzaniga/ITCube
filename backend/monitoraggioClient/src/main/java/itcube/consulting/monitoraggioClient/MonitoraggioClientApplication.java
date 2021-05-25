package itcube.consulting.monitoraggioClient;

import java.sql.Time;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import itcube.consulting.monitoraggioClient.entities.database.Task;
import itcube.consulting.monitoraggioClient.repositories.ElencoAlertRepository;
import itcube.consulting.monitoraggioClient.security.JWTAuthorizationFilter;

@SpringBootApplication
@EnableScheduling
public class MonitoraggioClientApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MonitoraggioClientApplication.class, args);
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
