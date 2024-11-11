package com.loja.ui;

import com.loja.exception.ProdutoException;
import com.loja.exception.ValidacaoException;
import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;

import java.util.Optional;
import java.util.Scanner;

/**
 * Classe de interface do usuário que fornece um menu para gerenciar produtos na loja.
 * Permite as operações de criação, leitura, atualização e exclusão de produtos.
 */
public class MenuProdutos {
    private final Scanner scanner = new Scanner(System.in);
    private final GerenciadorProdutos gerenciador = new GerenciadorProdutos();

    /**
     * Exibe o menu principal para o usuário, permitindo a navegação entre as
     * diferentes funcionalidades do sistema de gerenciamento de produtos.
     */
    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Cadastrar Produto\n" +
                    "2. Buscar Produto por ID\n" +
                    "3. Listar Todos os Produtos\n" +
                    "4. Atualizar Produto\n" +
                    "5. Deletar Produto\n" +
                    "6. Buscar por Nome\n" +
                    "7. Buscar por Categoria\n" +
                    "8. Buscar por Faixa de Preço\n" +
                    "9. Sair");

            int opcao = lerEntradaInteira("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> buscarProduto();
                case 3 -> listarProdutos();
                case 4 -> atualizarProduto();
                case 5 -> deletarProduto();
                case 6 -> buscarPorNome();
                case 7 -> buscarPorCategoria();
                case 8 -> buscarPorFaixaPreco();
                case 9 -> {
                    System.out.println("Saindo do sistema!");
                    return;
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    /**
     * Realiza o cadastro de um novo produto, solicitando dados ao usuário e
     * validando-os antes de inserir no gerenciador de produtos.
     */
    private void cadastrarProduto() {
        boolean produtoCadastrado = false;

        while (!produtoCadastrado) {
            System.out.println("\n=== Cadastro de Produto ===");
            try {
                Produto produto = solicitarDados();
                gerenciador.criar(produto);
                System.out.println("Produto cadastrado com sucesso!");
                System.out.println("Id gerado: " + produto.getId());
                produtoCadastrado = true;
            } catch (ValidacaoException e) {
                System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            }
        }
    }

    /**
     * Solicita ao usuário os dados para criação ou atualização de um produto.
     * Realiza também as validações dos dados.
     * @return um objeto Produto com os dados fornecidos pelo usuário
     */
    public Produto solicitarDados() {
        scanner.nextLine();

        String nome = lerEntradaString("Digite o nome: ");
        String categoria = lerEntradaString("Digite a categoria: ");
        double preco = lerEntradaDouble("Digite o preço: ");
        int quantidadeEstoque = lerEntradaInteira("Digite a quantidade: ");

        Produto produto = new Produto(nome, preco, quantidadeEstoque, categoria);
        gerenciador.validarNome(nome);
        gerenciador.validarCategoria(categoria);
        gerenciador.validarPreco(preco);
        gerenciador.validarQuantidade(quantidadeEstoque);

        return produto;
    }

    /**
     * Lê uma entrada inteira do usuário e lida com valores inválidos.
     * @param mensagem a mensagem exibida para o usuário
     * @return o valor inteiro lido
     */
    private int lerEntradaInteira(String mensagem) {
        int valor;

        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                break;
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                scanner.next();
            }
        }
        return valor;
    }

    /**
     * Lê uma entrada de texto do usuário.
     * @param mensagem a mensagem exibida para o usuário
     * @return a string lida
     */
    private String lerEntradaString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    /**
     * Lê uma entrada de número decimal do usuário e lida com valores inválidos.
     * @param mensagem a mensagem exibida para o usuário
     * @return o valor decimal lido
     */
    private double lerEntradaDouble(String mensagem) {
        double valor;

        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                break;
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número decimal.");
                scanner.next();
            }
        }
        return valor;
    }

    /**
     * Realiza a busca de um produto pelo ID e exibe as informações, caso encontrado.
     */
    private void buscarProduto() {
        System.out.println("\n=== Busca de Produto ===");
        try {
            Optional<Produto> produto = buscarProdutoPorId();
            if (produto.isEmpty()) {
                System.out.println("Produto não encontrado.");
            } else {
                System.out.println("Produto encontrado: " + produto.get());
            }
        } catch (ProdutoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Busca um produto pelo ID, com a opção de cancelar a busca.
     * @return um Optional contendo o produto encontrado ou vazio se cancelado
     * @throws ProdutoException se o produto não for encontrado
     */
    public Optional<Produto> buscarProdutoPorId() throws ProdutoException {
        while (true) {
            int id = lerEntradaInteira("Id do produto (ou 0 para cancelar): ");
            if (id == 0) {
                System.out.println("Busca cancelada!");
                return Optional.empty();
            }

            Optional<Produto> produto = Optional.ofNullable(gerenciador.buscarPorId(id));
            if (produto.isEmpty()) {
                throw new ProdutoException("Produto com id " + id + " não encontrado.");
            }

            return produto;
        }
    }

    /**
     * Lista todos os produtos cadastrados.
     */
    private void listarProdutos() {
        System.out.println("\n=== Lista de Produtos ===");
        if (gerenciador.listarTodos().isEmpty()) {
            System.out.println("Lista de produtos vazia");
        } else {
            gerenciador.listarTodos().forEach(System.out::println);
        }
    }

    /**
     * Atualiza as informações de um produto existente com base no ID.
     */
    private void atualizarProduto() {
        System.out.println("\n=== Atualizar Produto ===");
        Optional<Produto> produtoExistente = buscarProdutoPorId();
        Produto novoProduto = solicitarDados();
        if (produtoExistente.isPresent()) {
            novoProduto.setId(produtoExistente.get().getId());

            if (gerenciador.atualizar(novoProduto)) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar produto");
            }
        }
    }

    /**
     * Exclui um produto existente com base no ID, após confirmação do usuário.
     */
    private void deletarProduto() {
        System.out.println("\n=== Deletar Produto ===");
        Optional<Produto> produto = buscarProdutoPorId();

        if (produto.isPresent()) {
            int entrada = lerEntradaInteira("Deseja deletar o produto com id "
                    + produto.get().getId()
                    + "? Digite 1 para 'sim' ou 2 para 'não': ");

            if (entrada == 1) {
                if (gerenciador.deletar(produto.get().getId())) {
                    System.out.println("Produto deletado com sucesso!");
                } else {
                    System.out.println("Erro ao deletar o produto. Tente novamente.");
                }
            } else if (entrada == 2) {
                System.out.println("Remoção cancelada!");
            } else {
                System.out.println("Entrada inválida! Digite 1 para remover o produto e 2 para cancelar.");
            }
        } else {
            System.out.println("Produto não encontrado. Remoção cancelada!");
        }
    }

    /**
     * Realiza a busca de produtos com um nome específico.
     */
    private void buscarPorNome() {
        scanner.nextLine();

        System.out.println("\n=== Buscar Produto por Nome ===");
        String nome = lerEntradaString("Nome do produto: ");

        if (gerenciador.buscarPorNome(nome).isEmpty()) {
            System.out.println("Não há produtos com o nome informado");
        } else {
            gerenciador.buscarPorNome(nome).forEach(System.out::println);
        }
    }

    /**
     * Realiza a busca de produtos dentro de uma categoria específica.
     */
    private void buscarPorCategoria() {
        scanner.nextLine();

        System.out.println("\n=== Buscar Produto por Categoria ===");
        String categoria = lerEntradaString("Categoria do produto: ");

        if (gerenciador.buscarPorCategoria(categoria).isEmpty()) {
            System.out.println("Não há produtos com a categoria informada");
        } else {
            gerenciador.buscarPorCategoria(categoria).forEach(System.out::println);
        }
    }

    /**
     * Realiza a busca de produtos dentro de uma faixa de preço especificada.
     */
    private void buscarPorFaixaPreco() {
        System.out.println("\n=== Buscar Produto por Faixa de Preço ===");
        double precoMin = lerEntradaDouble("Preço mínimo: ");
        double precoMax = lerEntradaDouble("Preço máximo: ");

        if (gerenciador.buscarPorFaixaPreco(precoMin, precoMax).isEmpty()) {
            System.out.println("Não há produtos com o preço entre R$" + precoMin + " e R$" + precoMax);
        } else {
            gerenciador.buscarPorFaixaPreco(precoMin, precoMax).forEach(System.out::println);
        }
    }
}