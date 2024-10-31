package com.loja.gerenciador;

import com.loja.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {
    private List<Produto> produtos;
    private int proximoId = 1;

    public void criar(Produto produto) {
        produto.setId(proximoId);
        produtos.add(produto);
        proximoId++;
    }

    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                return produto;
            }
        }
        return null;
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public boolean atualizar(Produto produto) {
        Produto produtoEncontrado = buscarPorId(produto.getId());

        if (produtoEncontrado != null) {
            validarProduto(produto);

            produto.setId(produto.getId());
            produto.setNome(produto.getNome());
            produto.setCategoria(produto.getCategoria());
            produto.setPreco(produto.getQuantidadeEstoque());
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque());

            return true;
            }
        return false;
    }

    public boolean deletar(int id) {
        Produto produto = buscarPorId(id);
            if (produto != null) {
                produtos.remove(produto);
                return true;
            }
        return false;
    }

    public List<Produto> buscarPorNome(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produtos;
            }
        }
        return null;
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        for (Produto produto : produtos) {
            if (produto.getCategoria().equalsIgnoreCase(categoria)) {
                return produtos;
            }
        }
        return null;
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }

        if (produto.getNome().length() < 2) {
            throw new IllegalArgumentException("O nome do produto deve ter pelo menos 2 caracteres.");
        }

        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser um valor positivo.");
        }

        if (produto.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }

        if (produto.getCategoria() == null || produto.getCategoria().isEmpty()) {
            throw new IllegalArgumentException("A categoria do produto não pode ser vazia.");
        }
    }
}
