package com.loja.exception;

/**
 * A classe ValidacaoException representa uma exceção personalizada para erros de validação
 * de dados no sistema de loja. É uma subclasse de RuntimeException, permitindo que seja usada
 * como uma exceção não verificada, o que significa que pode ser lançada sem a necessidade
 * de ser explicitamente capturada ou declarada.
 */
public class ValidacaoException extends RuntimeException {

    /**
     * Construtor que cria uma nova instância de ValidacaoException com uma mensagem de erro específica.
     * @param mensagem a mensagem descritiva do erro ocorrido durante a validação
     */
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}