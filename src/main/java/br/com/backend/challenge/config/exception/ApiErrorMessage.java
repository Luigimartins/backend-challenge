package br.com.backend.challenge.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErrorMessage {

    private HttpStatusCode status;
    private List<String> errors;

    public ApiErrorMessage(HttpStatusCode status, List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    public ApiErrorMessage(HttpStatusCode status, String error) {
        super();
        this.status = status;
        errors = Arrays.asList(error);
    }
}
