package com.br.restaurante.order.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException() {
    }

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}