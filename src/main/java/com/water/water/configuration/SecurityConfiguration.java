package com.water.water.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity
				.csrf(csrf -> csrf.ignoringRequestMatchers("/update/upload", "/api/**"))
				.requiresChannel( channel -> channel.anyRequest().requiresSecure())
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults())
				.logout(Customizer.withDefaults())
				.exceptionHandling(Customizer.withDefaults())
				.authorizeHttpRequests(auths -> auths
						.requestMatchers("/update/upload").hasRole("JENKINS")
						.requestMatchers("/update/latest/**").hasRole("ARDUINO_USER")
						.requestMatchers("/api/**").hasRole("ARDUINO_USER")
						.requestMatchers("/webjars/**").authenticated()
						.requestMatchers("/h2-console/**").authenticated()
						.anyRequest().authenticated())
				.build();
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {

		UserDetails user = User.withUsername("user")
			.passwordEncoder(passwordEncoder::encode)
			.password("user")
			.roles("USER")
			.build();

		UserDetails admin = User.withUsername("admin")
			.passwordEncoder(passwordEncoder::encode)
			.password("admin")
			.roles("USER", "ADMIN")
			.build();

		UserDetails jenkins = User.withUsername("jenkins")
			.passwordEncoder(passwordEncoder::encode)
			.password("jenkins")
			.roles("JENKINS")
			.build();

		UserDetails arduinoUser = User.withUsername("arduino_user")
				.passwordEncoder(passwordEncoder::encode)
				.password("arduino_user")
				.roles("ARDUINO_USER")
				.build();

		return new InMemoryUserDetailsManager(user, admin, jenkins, arduinoUser);
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/webjars/**", "/h2-console/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
