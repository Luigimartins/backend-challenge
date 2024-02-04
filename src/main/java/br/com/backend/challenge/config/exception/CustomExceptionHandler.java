package br.com.backend.challenge.config.exception;

import br.com.backend.challenge.application.exception.ProductNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(status, errors);
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(status, ex.getMessage());
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(ProductNotFoundException exception) {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatusCode.valueOf(404), exception.getMessage());
        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

}
