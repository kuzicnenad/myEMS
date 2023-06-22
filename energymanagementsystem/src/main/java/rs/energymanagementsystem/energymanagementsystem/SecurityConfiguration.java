package rs.energymanagementsystem.energymanagementsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /** Password Encoder definition **/
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /** Necessary configurations fo Authorize Requests
     * More specific rules must come first, followed by the more general ones.
     **/
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {
        /**
         * This section defines the user accounts which can be used for
         * authentication as well as the roles each user has.
         */
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/**").hasAuthority("USER")
                            .anyRequest().authenticated()
                    )
                    .formLogin(formLogin -> formLogin
                            .loginPage("/login")
                            .permitAll()
                    )
                    .rememberMe(Customizer.withDefaults());

            return http.build();
        }
    }

}
