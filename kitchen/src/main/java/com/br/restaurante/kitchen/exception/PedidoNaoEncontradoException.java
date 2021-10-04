package com.br.restaurante.kitchen.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException() {
    }

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}