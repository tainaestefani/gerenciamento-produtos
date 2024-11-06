package com.loja.ui;

import com.loja.gerenciador.GerenciadorProdutos;
import java.util.Scanner;

public class MenuProdutos {
    private Scanner scanner = new Scanner(System.in);
    private GerenciadorProdutos gerenciador = new GerenciadorProdutos();

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

            if (opcao == 9) {
                System.out.println("Saindo do sistema!");
                break;
            }

            /*switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> buscarProduto();
                case 3 -> listarProdutos();
                case 4 -> atualizarProduto();
                case 5 -> deletarProduto();
                case 6 -> buscarPorNome();
                case 7 -> buscarPorCategoria();
                case 8 -> buscarPorFaixaPreco();
                default -> System.out.println("Opção inválida");
            }*/
        }
    }

    public int lerEntradaInteira(String mensagem) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                break;
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                scanner.next();
            }
        }
        return opcao;
    }

}
