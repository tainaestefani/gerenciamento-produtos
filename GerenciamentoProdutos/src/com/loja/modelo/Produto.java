package com.loja.modelo;

import java.util.Objects;

/**
 * A classe Produto representa um item no catálogo de uma loja, contendo informações
 * sobre seu nome, preço, quantidade em estoque e categoria.
 */
public class Produto {
    private Integer id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private String categoria;

    /**
     * Construtor para inicializar um produto com os atributos principais, exceto o ID.
     * @param nome o nome do produto
     * @param preco o preço do produto
     * @param quantidadeEstoque a quantidade disponível em estoque
     * @param categoria a categoria do produto
     */
    public Produto(String nome, double preco, int quantidadeEstoque, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    /**
     * Retorna o ID do produto.
     * @return o ID do produto
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     * @param id o ID a ser definido para o produto
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o nome do produto.
     * @return o nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     * @param nome o nome a ser definido para o produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço do produto.
     * @return o preço do produto
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     * @param preco o preço a ser definido para o produto
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna a quantidade em estoque do produto.
     * @return a quantidade em estoque
     */
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    /**
     * Define a quantidade em estoque do produto.
     * @param quantidadeEstoque a quantidade de estoque a ser definida para o produto
     */
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    /**
     * Retorna a categoria do produto.
     * @return a categoria do produto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     * @param categoria a categoria a ser definida para o produto
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Retorna uma representação textual do produto com seus principais atributos.
     * @return uma string formatada contendo ID, nome, preço, quantidade em estoque e categoria
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nome: %s | Preço: R$ %.2f | Estoque: %d | Categoria: %s",
                id, nome, preco, quantidadeEstoque, categoria
        );
    }

    /**
     * Compara se dois objetos Produto são iguais com base no ID.
     * @param o o objeto a ser comparado
     * @return true se os produtos têm o mesmo ID; false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return getId().equals(produto.getId());
    }

    /**
     * Retorna o código hash do produto com base no ID.
     * @return o código hash do produto
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}