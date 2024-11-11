package com.loja.gerenciador;

import com.loja.exception.ProdutoException;
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
        throw new ProdutoException("Produto com id " + id + " não encontrado.");
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
            if (produto.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    public List<Produto> buscarPorFaixaPreco(double precoMin, double precoMax) {
        List<Produto> produtosFiltrados = new ArrayList<>();

        for (Produto produto : produtos) {
            if (produto.getPreco() >= precoMin && produto.getPreco() <= precoMax) {
                produtosFiltrados.add(produto);
            }
        }
        return produtosFiltrados;
    }

    public void validarProduto(Produto produto){
        validarNome(produto.getNome());
        validarPreco(produto.getPreco());
        validarPreco(produto.getPreco());
        validarCategoria(produto.getCategoria());
    }

    public void validarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoException("O nome do produto não pode ser vazio.");
        }

        if (nome.length() < 2) {
            throw new ValidacaoException("O nome do produto deve ter pelo menos 2 caracteres.");
        }
    }

    public void validarPreco(double preco) {
        if (preco <= 0) {
            throw new ValidacaoException("O preço do produto deve ser um valor positivo.");
        }
    }

    public void validarQuantidade(int quantidadeEstoque) {
        if (quantidadeEstoque < 0) {
            throw new ValidacaoException("A quantidade em estoque não pode ser negativa.");
        }
    }
    public void validarCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            throw new ValidacaoException("A categoria do produto não pode ser vazia.");
        }
    }
}
