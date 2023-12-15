# TrailB

## [Modelo de Relacionamento] 
![image](https://github.com/joaomn/TrailB/assets/97440058/68de1ec7-62c2-4776-828b-fd385293529e)


## Descrição
API de gerenciamento de uma plataforma de ensino, onde ela faz todo o gerenciamento desde o usuario até a criação e envio automarico de certificados por email proprio da API


## Tecnologias Utilizadas
- ![Java](https://img.shields.io/badge/Java-17-brightgreen.svg)
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-brightgreen.svg)
- ![MySQL](https://img.shields.io/badge/MySQL-8-blue.svg)
- ![Docker](https://img.shields.io/badge/Docker-24-blue.svg)

## Estrutura do Projeto

O projeto está estruturado no padrao MVC, utilizando os conceitos do SOLID e está dividido em:

> Pcacote de classes de entidades
> Pacote de classes de controller
> Pacote de Classes de servico
> Pacote de classes de excessões
> Pacote de classes de configurações
> Pacote de classes de repositorio
>

A documentação mais detalhada de cada endpoit está disponivel quando rodar o projeto e acessar : http://localhost:8080/swagger-ui/index.html#/ nos eu navegador

## Configuração do Banco de Dados

> Antes de rodar o projeto, nao esqueça de configurar o aplication.propetis com as suas credenciais do banco local (linhas 2 e 3 mais especificadamente)
>
> tambem disponibilizei um dump do banco com dados de teste para melhor usabilidade na sua primeira experiencia.

## Docker

### Dockerfile
 se for usar o docker compose, lembre-se de alterar o aplication.propetis no endpoint do banco para este do exemplo abaixo


```aplication.propetis
# jdbc:mysql://mysql-service:3306/trailb?createDatabaseIfNotExist=true
