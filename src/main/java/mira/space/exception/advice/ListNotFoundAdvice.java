package mira.space.exception.advice;

import mira.space.exception.ListNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ListNotFoundAdvice {

    @ExceptionHandler(ListNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String listNotFoundHandler(ListNotFoundException ex) {
        return ex.getMessage();
    }
}
