# 🛒 Sistema de Gerenciamento de Produtos

## Descrição
O sistema permite o gerenciamento de produtos em uma loja, incluindo operações como cadastro, atualização, remoção e busca de produtos por diferentes critérios. Foi desenvolvido utilizando Java Puro, aplicando boas práticas de programação, organização e uso de exceções personalizadas para tratamento de erros.

## Funcionalidades
O sistema oferece as seguintes funcionalidades:

1. **Cadastrar Produto:** Permite o registro de novos produtos no sistema, incluindo a definição de suas características (como nome, preço e quantidade).
2. **Buscar Produto por ID:** Realiza a busca de um produto específico utilizando seu identificador único (ID).
3. **Listar Todos os Produtos:** Exibe uma lista completa de todos os produtos cadastrados no sistema, incluindo suas informações básicas.
4. **Atualizar Produto:** Permite a modificação das informações de um produto previamente cadastrado, como preço, nome ou quantidade.
5. **Deletar Produto:** Exclui um produto do sistema com base no seu identificador (ID).
6. **Buscar por Nome:** Realiza a busca de produtos que correspondam a um nome ou parte dele informado pelo usuário.
7. **Buscar por Categoria:** Permite a busca de produtos pertencentes a uma categoria específica, facilitando a filtragem de itens.
8. **Buscar por Faixa de Preço:** Exibe produtos cujo preço esteja dentro de uma faixa de valores definida pelo usuário.
9. **Sair:** Finaliza a execução do programa, encerrando a interação com o usuário.

## Tecnologias Utilizadas
- **Java 8+:** Linguagem de programação principal.
- **Scanner:** Para capturar entradas do usuário no terminal.
- **Optional:** Para evitar erros de nulidade ao buscar produtos.
- **Exceções Personalizadas:** Para tratar erros de validação e de operações relacionadas aos produtos.
- **Coleções (List, ArrayList):** Para armazenar e gerenciar os produtos no sistema.

## Estrutura do Projeto
O projeto está organizado nos seguintes pacotes:
```
├── com/
│   └── loja/
│       ├── exception/               # Exceções personalizadas
│       ├── gerenciador/             # Classes de gerenciamento de produtos
│       ├── modelo/                  # Modelo da classe Produto
│       ├── ui/                      # Interface de usuário (MenuProdutos)
│       └── Main                     # Classe principal
 ```

## Instruções de Execução
1. **Clone o repositório**:
   ```
   https://github.com/tainaestefani/gerenciamento-produtos.git
   ```
2. **Abra o projeto na sua IDE de escolha**:
Após clonar o repositório, abra o projeto na sua IDE de sua escolha (como IntelliJ IDEA, Eclipse, VS Code, etc.).
3. **Execute o projeto**:
Depois de abrir o projeto, clique no botão de **Run** ou **Build** para compilar e rodar o sistema. O projeto já está configurado para ser executado a partir da classe `Main`.
- **IntelliJ IDEA**: Clique no botão verde de **Run** no canto superior direito ou use o atalho **Shift + F10**.
- **Eclipse**: Clique no botão verde de **Run** ou use o atalho **Ctrl + F11**.
- **VS Code**: Clique no ícone de **Play** no canto superior direito ou use o terminal para executar o comando:
  ```
  java com.loja.Main
  ```
4. **Interaja com o Menu**:
O sistema será iniciado e apresentará um menu interativo no terminal. Basta seguir as instruções na tela para gerenciar os produtos.


## Exemplos de Uso

**Cadastrar Produto:**
```yaml
=== Cadastro de Produto ===
Digite o nome: Notebook
Digite o preço: 2500,00
Digite a quantidade: 10
Digite a categoria: Eletrônicos
Produto cadastrado com sucesso! ID gerado: 1
```

**Buscar Produto por ID:**
```yaml
=== Busca de Produto ===
Digite o ID: 1
Produto encontrado: ID: 1 | Nome: Notebook | Preço: R$ 2500,00 | Estoque: 10 | Categoria: Eletrônicos
```

## Decisões de Implementação

- **Uso de Exceções Personalizadas**: Foram criadas as exceções `ProdutoException` e `ValidacaoException` para fornecer um tratamento de erros mais específico. Isso permite que, ao ocorrer um erro, como ao tentar cadastrar um produto com dados inválidos ou ao buscar um produto que não existe, a aplicação forneça mensagens claras e controladas, sem expor falhas inesperadas.
- **Estrutura de Código**: A organização do código foi feita em pacotes para garantir a modularidade e facilitar a manutenção. Cada pacote tem uma responsabilidade clara:
  - `exception`: Armazena as exceções personalizadas, que são essenciais para o controle de erros no sistema.
  - `gerenciador`:  Contém a lógica de negócios, como cadastro, busca e atualização de produtos, facilitando a evolução do sistema.
  - `modelo`: Armazena o modelo da classe `Produto`, representando os dados que são manipulados.
  - `ui`: Contém a interface de usuário, garantindo que o código de interação com o usuário seja separado da lógica de negócios.
  - `Main`: Centraliza a execução do sistema, iniciando o menu de interação.
- **Uso de `Optional`**: A classe `MenuProdutos` utiliza `Optional` para evitar exceções de nulidade ao buscar produtos. Isso torna a busca mais segura, pois garante que, mesmo que o produto não seja encontrado, a aplicação não quebre, retornando um valor vazio de forma controlada.

## Autor
<div align="left">
  <a href="https://github.com/tainaestefani">
    <img alt="Tainá Estefani Martins" src="https://avatars.githubusercontent.com/u/154456749?v=4" width="115" style="border-radius:50%">
  </a>
  <br>
  <sub><b>Tainá Estefani Martins</b></sub><br>
  <sub>RA: 1778354</sub><br>
</div>

## Licença
Este projeto é licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para obter mais detalhes.
