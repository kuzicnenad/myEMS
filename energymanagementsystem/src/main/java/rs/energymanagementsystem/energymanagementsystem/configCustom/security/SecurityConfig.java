package rs.energymanagementsystem.energymanagementsystem.configCustom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import rs.energymanagementsystem.energymanagementsystem.exceptions.ExceptionControllerAdvice;
import rs.energymanagementsystem.energymanagementsystem.exceptions.LoginFailureHandler;

@Configuration
@EnableMethodSecurity
//@EnableWebSecurity
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /** adding used resources to enable them before user is logged in**/
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
    public static final String LOGIN_FAIL_URL = "/error";
    public static final String DEFAULT_SUCCESS_URL = "/index";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Autowired
    private LoginFailureHandler failureHandler;

    ExceptionControllerAdvice exceptionControllerAdvice = new ExceptionControllerAdvice();
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        //authorize.anyRequest().authenticated()
                        .requestMatchers(staticResources).permitAll()
                        .requestMatchers(LOGIN_URL).permitAll()
                        .requestMatchers(DEFAULT_SUCCESS_URL).hasAnyAuthority("USER","ADMIN","ROOT")
                        .requestMatchers("/api").hasAnyAuthority("ADMIN","ROOT")
                        .requestMatchers("/api/**").hasAnyAuthority("ADMIN","ROOT")
                        .requestMatchers("/index").hasAnyAuthority("USER","ADMIN","ROOT")
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/liveData").hasAnyAuthority("USER","ADMIN","ROOT")
                        .requestMatchers("/historyDataElectricity").hasAnyAuthority("USER","ADMIN","ROOT")
                        .requestMatchers("/historyDataGas").hasAnyAuthority("USER","ADMIN","ROOT")
                        .requestMatchers("/historyDataWater").hasAnyAuthority("USER","ADMIN","ROOT")
                        //.requestMatchers("/analysis").hasAuthority("ADMIN")
                        //.requestMatchers("/reports").hasAuthority("ADMIN")
                        .requestMatchers("/settings").hasAnyAuthority("USER","ADMIN","ROOT")
                        .requestMatchers("/activeDevices/**").hasAnyAuthority("ADMIN","ROOT")
                        .requestMatchers("/availableDevices/**").hasAnyAuthority("ADMIN","ROOT")
                        .requestMatchers("/users/**").hasAnyAuthority("ADMIN","ROOT")
                        .requestMatchers("/changePasswordForm").hasAnyAuthority("USER","ADMIN","ROOT")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage(LOGIN_URL)
                        .defaultSuccessUrl(DEFAULT_SUCCESS_URL)
                        // Provide custom failed login message.
                        .failureHandler(failureHandler)
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
