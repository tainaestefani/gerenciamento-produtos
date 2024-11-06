package com.loja.gerenciador;

import com.loja.exception.ValidacaoException;
import com.loja.modelo.Produto;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    public void criar(Produto produto) {
        validarProduto(produto);
        produto.setId(proximoId);
        produtos.add(produto);
        proximoId++;
    }

    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
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

            produtoEncontrado.setNome(produto.getNome());
            produtoEncontrado.setCategoria(produto.getCategoria());
            produtoEncontrado.setPreco(produto.getPreco());
            produtoEncontrado.setQuantidadeEstoque(produto.getQuantidadeEstoque());

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
        List<Produto> resultado = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getCategoria().toLowerCase().equals(categoria.toLowerCase())) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new ValidacaoException("O nome do produto não pode ser vazio.");
        }

        if (produto.getNome().length() < 2) {
            throw new ValidacaoException("O nome do produto deve ter pelo menos 2 caracteres.");
        }

        if (produto.getPreco() <= 0) {
            throw new ValidacaoException("O preço do produto deve ser um valor positivo.");
        }

        if (produto.getQuantidadeEstoque() < 0) {
            throw new ValidacaoException("A quantidade em estoque não pode ser negativa.");
        }

        if (produto.getCategoria() == null || produto.getCategoria().isEmpty()) {
            throw new ValidacaoException("A categoria do produto não pode ser vazia.");
        }
    }
}
