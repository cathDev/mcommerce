package com.clientui.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoqueur, Response response) {

        System.out.println(response.body());
        if(response.status() == 400 ) {
            return new ProductBadRequestException(
                    "Requête incorrecte "
            );
        }
        else if(response.status() == 404 ) {
            return new ProductNotFoundException(
                    "produit introuvable "
            );
        }

        return defaultErrorDecoder.decode(invoqueur, response);
    }
}
