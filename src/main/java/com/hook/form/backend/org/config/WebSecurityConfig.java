package com.hook.form.backend.org.config;

// import static com.hook.form.backend.org.model.Permission.ADMIN_CREATE;
// import static com.hook.form.backend.org.model.Permission.ADMIN_DELETE;
// import static com.hook.form.backend.org.model.Permission.ADMIN_READ;
// import static com.hook.form.backend.org.model.Permission.ADMIN_UPDATE;
// import static com.hook.form.backend.org.model.Permission.DEFAULT_CREATE;
// import static com.hook.form.backend.org.model.Permission.DEFAULT_DELETE;
// import static com.hook.form.backend.org.model.Permission.DEFAULT_READ;
// import static com.hook.form.backend.org.model.Permission.DEFAULT_UPDATE;
// import static com.hook.form.backend.org.model.Role.ADMIN;
// import static com.hook.form.backend.org.model.Role.DEFAULT;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig{

	@Autowired
	private UserDetailsService jwtUserDetailsService;

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final SimpleCORSFilter simpleCORSFilter;
    private final AuthenticationProvider authenticationProvider;
    @Autowired
    @Qualifier("delegatedAuthenticationEntryPoint")
	AuthenticationEntryPoint authEntryPoint;
    @Autowired
	private  LogoutHandler logoutHandler;

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
                http.cors(cors -> cors.disable())                .csrf(csrf -> csrf.disable())
                .authorizeRequests(requests -> requests
                                .antMatchers("/auth/authenticate", "/auth/signup",
                                        "/api/v1/auth/**/",
                                        "/v2/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources/**",
                                        "/swagger-resources",
                                        "/configuration/ui",
                                        "/configuration/security",
                                         "/swagger-ui/*",
                                        "/webjars/**",
                                          
                "/swagger-ui.html").permitAll()
                // .antMatchers("/gmp/default/*").hasAnyRole(ADMIN.name(),DEFAULT.name())
            
                // .antMatchers(GET, "/gmp/default/**").hasAnyAuthority(ADMIN_READ.name(), DEFAULT_READ.name())
                // .antMatchers(POST,"/gmp/default/**" ).hasAnyAuthority(ADMIN_CREATE.name(), DEFAULT_CREATE.name())
                // .antMatchers(PUT,"/gmp/default/**" ).hasAnyAuthority(ADMIN_UPDATE.name(), DEFAULT_UPDATE.name())
                // .antMatchers(DELETE, "/gmp/default/**").hasAnyAuthority(ADMIN_DELETE.name(), DEFAULT_DELETE.name())
                
                // .antMatchers("/gmp/admin/**").hasRole(ADMIN.name())
                
                // .antMatchers(GET, "/gmp/admin/**").hasAuthority(ADMIN_READ.name())
                // .antMatchers(POST,"/gmpo/admin/**" ).hasAuthority(ADMIN_CREATE.name())
                // .antMatchers(PUT,"/gmp/admin/**" ).hasAuthority(ADMIN_UPDATE.name())
                // .antMatchers(DELETE, "/gmp/admin/**").hasAuthority(ADMIN_DELETE.name())

                .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)                
                .addFilterBefore(simpleCORSFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthFilter, SimpleCORSFilter.class)
                .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults())
                
                .exceptionHandling(handling -> handling
                       .authenticationEntryPoint(authEntryPoint))
                        
				.logout()
				.logoutUrl("/security/gmp/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(((req, res, auth) -> SecurityContextHolder.clearContext()));

       return http.build();

}




	

		
}
