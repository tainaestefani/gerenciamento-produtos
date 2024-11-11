package com.loja.exception;

/**
 * A classe ProdutoException representa uma exceção personalizada para erros específicos
 * relacionados ao gerenciamento de produtos no sistema de loja. É uma subclasse de RuntimeException,
 * o que permite que seja usada como uma exceção não verificada, podendo ser lançada
 * durante a execução sem a necessidade de ser declarada ou capturada explicitamente.
 */
public class ProdutoException extends RuntimeException {

    /**
     * Construtor que cria uma nova instância de ProdutoException com uma mensagem de erro específica.
     * @param mensagem a mensagem descritiva do erro ocorrido
     */
    public ProdutoException(String mensagem) {
        super(mensagem);
    }
}