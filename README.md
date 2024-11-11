# 🛒 Sistema de Gerenciamento de Produtos

## Descrição
O sistema permite o gerenciamento de produtos em uma loja, incluindo operações como cadastro, atualização, remoção e busca de produtos por diferentes critérios. Foi desenvolvido utilizando Java Puro, aplicando boas práticas de programação, organização e uso de exceções personalizadas para tratamento de erros.

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

## Funcionalidades
O sistema oferece as seguintes funcionalidades:

- **Cadastrar Produto:** Permite o registro de novos produtos no sistema, incluindo a definição de suas características (como nome, preço e quantidade).
- **Buscar Produto por ID:** Realiza a busca de um produto específico utilizando seu identificador único (ID).
- **Listar Todos os Produtos:** Exibe uma lista completa de todos os produtos cadastrados no sistema, incluindo suas informações básicas.
- **Atualizar Produto:** Permite a modificação das informações de um produto previamente cadastrado, como preço, nome ou quantidade.
- **Deletar Produto:** Exclui um produto do sistema com base no seu identificador (ID).
- **Buscar por Nome:** Realiza a busca de produtos que correspondam a um nome ou parte dele informado pelo usuário.
- **Buscar por Categoria:** Permite a busca de produtos pertencentes a uma categoria específica, facilitando a filtragem de itens.
- **Buscar por Faixa de Preço:** Exibe produtos cujo preço esteja dentro de uma faixa de valores definida pelo usuário.
- **Sair:** Finaliza a execução do programa, encerrando a interação com o usuário.

## Tecnologias Utilizadas
- **Java 8+:** Linguagem de programação principal.
- **Scanner:** Para capturar entradas do usuário através do terminal.
- **Optional:** Utilizado para evitar erros de nulidade ao buscar produtos.
- **Exceptions:** Tratamento de exceções para validação de entradas e operações.

## Exemplos de Uso

**Cadastrar Produto:**
```yaml
=== Cadastro de Produto ===
Digite o nome: Notebook
Digite o preço: 2500.00
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

## Autor
[<img alt="Profile Pic" src="https://avatars.githubusercontent.com/u/154456749?v=4" width="115" style="border-radius:50%"><br><sub>Tainá Estefani Martins</sub>](https://github.com/tainaestefani)
