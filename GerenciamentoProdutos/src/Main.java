import com.loja.ui.MenuProdutos;

/**
 * A classe Main é o ponto de entrada para a aplicação de gerenciamento de produtos de uma loja.
 * Ela inicializa o menu principal da aplicação e exibe as opções para o usuário interagir com
 * o sistema de gerenciamento de produtos.
 */
public class Main {

    /**
     * Método principal que inicia a execução do programa.
     * Ele cria uma instância de MenuProdutos e exibe o menu principal,
     * permitindo que o usuário interaja com o sistema.
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Cria uma instância de MenuProdutos, que representa a interface de usuário
        MenuProdutos menu = new MenuProdutos();

        // Exibe o menu principal e permite que o usuário interaja com o sistema
        menu.exibirMenu();
    }
}