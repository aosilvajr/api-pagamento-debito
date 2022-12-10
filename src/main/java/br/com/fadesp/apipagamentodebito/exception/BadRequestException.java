package br.com.fadesp.apipagamentodebito.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -1304081848634371217L;

    public BadRequestException(String message) {
        super(message);
    }

}
