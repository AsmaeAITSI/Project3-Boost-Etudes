package fr.eql.aaitsi.project.boostEtudes.spring.security;


import fr.eql.aaitsi.project.boostEtudes.spring.jwt.JWTAuthenticationFilter;
import fr.eql.aaitsi.project.boostEtudes.spring.jwt.JwtAuthEntryPoint;
import fr.eql.aaitsi.project.boostEtudes.spring.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // informe SB que config de security ici
public class SecurityConfig {
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    private SecurityService securityService;


    @Autowired
    public SecurityConfig(JwtAuthEntryPoint jwtAuthEntryPoint, SecurityService securityService) {
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.securityService = securityService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/user").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // for http only

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
    /*@Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user")).roles("USER").build();
        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("USER","ADMIN").build();
        return new InMemoryUserDetailsManager(user,admin);
    }*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }

}
