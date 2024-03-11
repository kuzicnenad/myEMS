package rs.energymanagementsystem.energymanagementsystem.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    /**Security configuration for Authentication Failure exception
     * Using custom failureHandler to be able to display custom /error page
     * **/
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String username = request.getParameter("username");
        String error = exception.getMessage();
        System.out.println("A login attempt for " + username + " failed.");
        System.out.println("Reason: " + error);
        String errMsg = ("A login attempt for " + username + " failed. Reason: " + error);
        throw new ServletException(errMsg); // automatically redirects to /error -> ExceptionControllerAdvice
    }
}
