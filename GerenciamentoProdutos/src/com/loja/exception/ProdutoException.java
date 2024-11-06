package com.loja.exception;

public class ProdutoException extends RuntimeException{
    public ProdutoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}