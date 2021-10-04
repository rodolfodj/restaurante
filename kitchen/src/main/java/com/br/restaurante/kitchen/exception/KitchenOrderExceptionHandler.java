package com.br.restaurante.kitchen.exception;

import com.br.restaurante.kitchen.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class KitchenOrderExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({PedidoNaoEncontradoException.class})
    public @ResponseBody
    ErrorResponse handleBusinessException(PedidoNaoEncontradoException exception) {
        return new ErrorResponse("", "Order not found");
    }

}