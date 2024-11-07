package com.loja.ui;

import com.loja.exception.ProdutoException;
import com.loja.exception.ValidacaoException;
import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;

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

        while(!produtoCadastrado) {
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
        String nome = lerEntradaString("Digite o nome: ");
        String categoria = lerEntradaString("Digite a categoria: ");
        double preco = lerEntradaDouble("Digite o preço: ");
        int quantidadeEstoque = lerEntradaInteira("Digite a quantidade: ");

        Produto produto = new Produto(nome, preco, quantidadeEstoque, categoria);
        gerenciador.validarProduto(produto);

        return produto;
    }

    public int lerEntradaInteira(String mensagem) {
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

    public String lerEntradaString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public double lerEntradaDouble(String mensagem) {
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

}
