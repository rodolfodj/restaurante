package com.br.restaurante.bar.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException() {
    }

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}
