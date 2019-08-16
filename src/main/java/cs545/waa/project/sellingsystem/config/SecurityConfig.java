package cs545.waa.project.sellingsystem.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/home", "/index", "/signup", "/not-found", "/js/**", "/css/**", "/img/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/seller/**").hasRole("SELLER")
				.antMatchers("/buyer/**").hasRole("BUYER")
				.anyRequest().authenticated()
				//.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.successHandler(successHandler)
				.failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.permitAll()
				.and()
			.exceptionHandling()
				.accessDeniedPage("/access-denied");
		
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	auth.jdbcAuthentication()
    		.usersByUsernameQuery(usersQuery)
            .authoritiesByUsernameQuery(rolesQuery)
            .dataSource(dataSource)
            .passwordEncoder(bCryptPasswordEncoder);
    }

}
