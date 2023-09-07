package rs.energymanagementsystem.energymanagementsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /** adding used resources to enable them before used is logged in**/
    String[] staticResources  =  {
            "/css/**",
            "/css",
            "/images/**",
            "/image"
    };

    /**Spring security configuration
     * used variables
     * authorisation and authentication **/
    public static final String LOGIN_URL = "/login";
    public static final String SESSION_ID = "JSESSIONID";
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String DEFAULT_SUCCESS_URL = "/index";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        //authorize.anyRequest().authenticated()
                        .requestMatchers(staticResources).permitAll()
                        .requestMatchers(LOGIN_URL).permitAll()
                        .requestMatchers(DEFAULT_SUCCESS_URL).hasAnyAuthority("USER","ADMIN")
                        .requestMatchers("/api").hasAuthority("ADMIN")
                        .requestMatchers("/api/**").hasAuthority("ADMIN")
                        .requestMatchers("/index").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/liveData").hasAnyAuthority("USER","ADMIN")
                        .requestMatchers("/historyDataElectricity").hasAnyAuthority("USER","ADMIN")
                        .requestMatchers("/historyDataGas").hasAnyAuthority("USER","ADMIN")
                        .requestMatchers("/historyDataWater").hasAnyAuthority("USER","ADMIN")
                        //.requestMatchers("/analysis").hasAuthority("ADMIN")
                        //.requestMatchers("/reports").hasAuthority("ADMIN")
                        .requestMatchers("/settings").hasAuthority("ADMIN")
                        .requestMatchers("/devices/**").hasAuthority("ADMIN")
                        .requestMatchers("/users/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage(LOGIN_URL)
                        .defaultSuccessUrl(DEFAULT_SUCCESS_URL)
                        .failureUrl(LOGIN_FAIL_URL)
                )
                .logout(logout -> logout
                        .logoutUrl(LOGOUT_URL)
                        .invalidateHttpSession(true)
                        .deleteCookies(SESSION_ID)
                        .logoutSuccessUrl(LOGIN_URL)
                );

        return http.build();
    }
}
