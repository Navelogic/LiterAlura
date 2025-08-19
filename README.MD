# Praticando Spring Boot: Desafio Literalura
Esta é uma aplicação de console desenvolvida em Java com o framework Spring Boot. O principal objetivo do projeto é construir um catálogo de livros interativo que se comunica com a API Gutendex para buscar e exibir informações sobre obras literárias. Todos os dados consultados são persistidos em um banco de dados em memória (H2) para permitir consultas locais e análises posteriores.

## Requisitos 💡
Para executar este projeto, é necessário ter os seguintes componentes instalados e configurados no seu ambiente de desenvolvimento:

Java 17 ou superior: Versão do kit de desenvolvimento Java.

Maven: Ferramenta de automação de compilação e gerenciamento de dependências do projeto.

## Funcionalidades ✨
A aplicação oferece um menu interativo no console com as seguintes opções:

* Buscar Livro pelo Título:
  * Como funciona: O usuário digita o nome de um livro. A aplicação faz uma requisição HTTP para a API pública Gutendex. O resultado (em formato JSON) é processado, e o primeiro livro encontrado na busca é selecionado. Seus dados, como título, autor, idioma e número de downloads, são extraídos e salvos no banco de dados. O sistema verifica se o livro já existe no banco para evitar duplicatas.

* Listar Livros Registrados:

    * Como funciona: Esta opção realiza uma consulta diretamente no banco de dados local para recuperar todos os livros que foram previamente buscados e salvos. As informações de cada livro são formatadas e exibidas de forma organizada no console.

* Listar Autores Registrados:

    * Como funciona: De forma semelhante à listagem de livros, esta funcionalidade consulta o banco de dados para buscar todos os autores associados aos livros registrados. Em seguida, imprime no console uma lista com o nome, ano de nascimento e ano de falecimento de cada autor.

* Listar Autores Vivos em um Determinado Ano:

    * Como funciona: O usuário informa um ano específico. A aplicação executa uma consulta no banco de dados que filtra os autores com base em duas condições: o ano de nascimento deve ser menor ou igual ao ano fornecido, e o ano de falecimento deve ser maior ou igual ao ano fornecido. Isso garante que apenas os autores que estavam vivos naquele período sejam exibidos.

* Listar Livros por Idioma:

    * Como funciona: O usuário é solicitado a escolher um idioma (por exemplo, pt para português, en para inglês). A aplicação então filtra a tabela de livros no banco de dados, retornando e exibindo no console apenas as obras que correspondem ao idioma especificado.

## Tecnologias Utilizadas
Este projeto foi construído utilizando um conjunto de tecnologias modernas e robustas do ecossistema Java:

* Java: Linguagem de programação principal.

* Spring Boot: Framework que simplifica a criação de aplicações Java, gerenciando a configuração, injeção de dependências e o ciclo de vida da aplicação.

* Spring Data JPA: Facilita a comunicação com o banco de dados.

* H2 Database: Banco de dados em memória utilizado para garantir a persistência dos dados de forma simples e rápida durante a execução.

* Jackson: Biblioteca para converter os dados da API (JSON) em objetos Java.

* Maven: Ferramenta de automação de compilação e gerenciamento de dependências.