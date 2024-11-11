package com.loja.ui;

import com.loja.exception.ProdutoException;
import com.loja.exception.ValidacaoException;
import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;

import java.util.Optional;
import java.util.Scanner;

public class MenuProdutos {
    private final Scanner scanner = new Scanner(System.in);
    private final GerenciadorProdutos gerenciador = new GerenciadorProdutos();

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

    private void cadastrarProduto() {
        boolean produtoCadastrado = false;

        while (!produtoCadastrado) {
            System.out.println("=== Cadastro de Produto ===");
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

    public Produto solicitarDados() {
        scanner.nextLine();

        String nome = lerEntradaString("Digite o nome: ");
        String categoria = lerEntradaString("Digite a categoria: ");
        double preco = lerEntradaDouble("Digite o preço: ");
        int quantidadeEstoque = lerEntradaInteira("Digite a quantidade: ");

        Produto produto = new Produto(nome, preco, quantidadeEstoque, categoria);
        gerenciador.validarProduto(produto);

        return produto;
    }
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

    private String lerEntradaString(String mensagem) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("A entrada não pode ser vazia. Tente novamente.");
            }
        } while (entrada.isEmpty());
        return entrada;
    }


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

    private void buscarProduto() {
        System.out.println("=== Busca de Produto ===");
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

    private void listarProdutos(){
        System.out.println("=== Lista de Produtos ===");
        if(gerenciador.listarTodos().isEmpty()){
            System.out.println("Lista de produtos vazia");
        }else{
            gerenciador.listarTodos().forEach(System.out::println);
        }
    }

    private void atualizarProduto() {
        System.out.println("=== Atualizar Produto ===");
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

    private void deletarProduto() {
        System.out.println("=== Deletar Produto ===");
        Optional<Produto> produto = buscarProdutoPorId();

        if (produto.isPresent()) {
            int entrada = lerEntradaInteira("Deseja deletar o produto com id "
                    + produto.get().getId()
                    + "? Digite 1 para 'sim' ou 2 para 'não'.");

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

    private void buscarPorNome() {
        System.out.println("=== Buscar Produto por Nome ===");
        String nome = lerEntradaString("Nome do produto: ");

        if (gerenciador.buscarPorNome(nome).isEmpty()) {
            System.out.println("Não há produtos com o nome informado");
        } else {
            gerenciador.buscarPorNome(nome).forEach(System.out::println);
        }
    }

    private void buscarPorCategoria() {
        System.out.println("=== Buscar Produto por Categoria ===");
        String categoria = lerEntradaString("Categoria do produto: ");

        if (gerenciador.buscarPorNome(categoria).isEmpty()) {
            System.out.println("Não há produtos com a categoria informada");
        } else {
            gerenciador.buscarPorCategoria(categoria).forEach(System.out::println);
        }
    }

    private void buscarPorFaixaPreco() {
        System.out.println("=== Buscar Produto por Faixa de Preço ===");
        double precoMin = lerEntradaDouble("Preço mínimo: ");
        double precoMax = lerEntradaDouble("Preço máximo: ");

        if (gerenciador.buscarPorFaixaPreco(precoMin, precoMax).isEmpty()) {
            System.out.println("Não há produtos com o preço entre R$" + precoMin + " e R$" + precoMax);
        } else {
            gerenciador.buscarPorFaixaPreco(precoMin, precoMax).forEach(System.out::println);
        }
    }
}