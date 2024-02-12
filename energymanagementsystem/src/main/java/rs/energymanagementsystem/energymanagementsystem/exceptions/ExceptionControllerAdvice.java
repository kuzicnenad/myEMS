package rs.energymanagementsystem.energymanagementsystem.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ModelAndView showExceptionMsg(Exception e) {

        ModelAndView mav = new ModelAndView("showExceptionMsg");
        mav.addObject("message", e.getMessage());

        return mav;
    }
}


