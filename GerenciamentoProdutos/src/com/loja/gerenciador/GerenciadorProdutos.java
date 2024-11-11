package com.loja.gerenciador;

import com.loja.exception.ProdutoException;
import com.loja.exception.ValidacaoException;
import com.loja.modelo.Produto;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe GerenciadorProdutos realiza a gestão de um catálogo de produtos, oferecendo
 * funcionalidades como criação, atualização, exclusão e busca de produtos.
 * Também inclui métodos para validação de atributos dos produtos, garantindo consistência nos dados.
 */
public class GerenciadorProdutos {
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    /**
     * Adiciona um novo produto ao catálogo após validar seus atributos.
     * O produto é registrado com um ID único e incremental.
     * @param produto o produto a ser adicionado
     * @throws ValidacaoException se algum dos atributos do produto for inválido
     */
    public void criar(Produto produto) {
        validarProduto(produto);  // Valida atributos do produto
        produto.setId(proximoId); // Define o ID do produto
        produtos.add(produto);    // Adiciona produto ao catálogo
        proximoId++;              // Incrementa o ID para o próximo produto
    }

    /**
     * Busca um produto pelo ID.
     * @param id o ID do produto a ser encontrado
     * @return o produto encontrado
     * @throws ProdutoException se o produto não for encontrado
     */
    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        throw new ProdutoException("Produto com id " + id + " não encontrado.");
    }

    /**
     * Retorna uma lista com todos os produtos do catálogo
     * @return lista de produtos
     */
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos); // Retorna cópia para evitar alterações diretas
    }

    /**
     * Atualiza os dados de um produto existente, substituindo os atributos pelos valores novos.
     * @param produto o produto atualizado com o ID correspondente ao produto existente
     * @return true se a atualização foi bem-sucedida; false se o produto não foi encontrado
     */
    public boolean atualizar(Produto produto) {
        Produto produtoEncontrado = buscarPorId(produto.getId());

        if (produtoEncontrado != null) {
            validarProduto(produto); // Valida os novos atributos do produto

            // Atualiza os atributos do produto encontrado
            produtoEncontrado.setNome(produto.getNome());
            produtoEncontrado.setCategoria(produto.getCategoria());
            produtoEncontrado.setPreco(produto.getPreco());
            produtoEncontrado.setQuantidadeEstoque(produto.getQuantidadeEstoque());

            return true;
        }
        return false;
    }

    /**
     * Remove um produto do catálogo pelo ID.
     * @param id o ID do produto a ser removido
     * @return true se a exclusão foi bem-sucedida; false se o produto não foi encontrado
     */
    public boolean deletar(int id) {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            produtos.remove(produto);
            return true;
        }
        return false;
    }

    /**
     * Busca produtos pelo nome, realizando uma busca parcial (insensível a maiúsculas).
     * @param nome o nome (ou parte dele) a ser buscado
     * @return lista de produtos que contêm o nome especificado
     */
    public List<Produto> buscarPorNome(String nome) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    /**
     * Busca produtos pela categoria especificada.
     * @param categoria a categoria dos produtos a serem buscados
     * @return lista de produtos da categoria especificada
     */
    public List<Produto> buscarPorCategoria(String categoria) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    /**
     * Busca produtos por uma faixa de preço especificada (inclusiva).
     * @param precoMin o preço mínimo
     * @param precoMax o preço máximo
     * @return lista de produtos dentro da faixa de preço
     */
    public List<Produto> buscarPorFaixaPreco(double precoMin, double precoMax) {
        List<Produto> produtosFiltrados = new ArrayList<>();

        for (Produto produto : produtos) {
            if (produto.getPreco() >= precoMin && produto.getPreco() <= precoMax) {
                produtosFiltrados.add(produto);
            }
        }
        return produtosFiltrados;
    }

    /**
     * Valida todos os atributos do produto.
     * @param produto o produto a ser validado
     * @throws ValidacaoException se algum atributo for inválido
     */
    public void validarProduto(Produto produto) {
        validarNome(produto.getNome());
        validarPreco(produto.getPreco());
        validarCategoria(produto.getCategoria());
    }

    /**
     * Valida o nome do produto, garantindo que não é vazio e tem pelo menos dois caracteres.
     * @param nome o nome do produto
     * @throws ValidacaoException se o nome for inválido
     */
    public void validarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoException("O nome do produto não pode ser vazio.");
        }

        if (nome.length() < 2) {
            throw new ValidacaoException("O nome do produto deve ter pelo menos 2 caracteres.");
        }
    }

    /**
     * Valida o preço do produto, garantindo que é positivo.
     * @param preco o preço do produto
     * @throws ValidacaoException se o preço for inválido
     */
    public void validarPreco(double preco) {
        if (preco <= 0) {
            throw new ValidacaoException("O preço do produto deve ser um valor positivo.");
        }
    }

    /**
     * Valida a quantidade em estoque do produto, garantindo que não seja negativa.
     * @param quantidadeEstoque a quantidade em estoque do produto
     * @throws ValidacaoException se a quantidade for negativa
     */
    public void validarQuantidade(int quantidadeEstoque) {
        if (quantidadeEstoque < 0) {
            throw new ValidacaoException("A quantidade em estoque não pode ser negativa.");
        }
    }

    /**
     * Valida a categoria do produto, garantindo que não seja vazia.
     * @param categoria a categoria do produto
     * @throws ValidacaoException se a categoria for inválida
     */
    public void validarCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            throw new ValidacaoException("A categoria do produto não pode ser vazia.");
        }
    }
}
