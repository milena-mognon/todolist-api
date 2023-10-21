# API TodoList - Gerenciamento de Tarefas

Esta API é um projeto desenvolvido visando explorar os princípios fundamentais do Framework Spring Boot (
Java), servindo como uma sólida base para estudo e aprimoramento profissional.

## Ferramentas e Tecnologias Utilizadas

* Java 17
* Maven (Gerenciador de dependências)
* Spring Boot
* JPA (hibernate)
* H2 (Banco de dados em Memória)
* Docker
* Tomcat
* Lombok

## Instalação

Para conseguir executar o projeto é necessário ter Java 17 e Maven instalado

### Sem Docker

```bash
mvn spring-boot:run   # Iniciar a aplicação Spring Boot (modo de desenvolvimento)

mvn clean install     # Utilizado para Compilar, empacotar e instalar, permitindo implantação em produção
```

### Com Docker (Recomendado)

**Desenvolvimento**

```bash
# Cria a imagem docker da aplicação
docker build -t todolist:0.1 -f Dockerfile.dev .

# Cria o container com a imagem da aplicação
docker run --rm -p 8080:8080 todolist:0.1
```

**Produção**

Essa imagem docker da aplicação possui mais camadas de segurança e é mais otimizada que a imagem de desenvolvimento.

É uma melhoria feita no projeto após o curso para aprimorar os conhecimentos em Docker e conhecer as melhores práticas
para construção de imagens para produção.

Melhorias Aplicadas:

- Uso de uma imagem Maven no lugar de Ubuntu. Essa Imagem não possui vulnerabilidades conhecidas e já inclui as
  dependências necessárias para criar o executável do projeto;
- Uso de uma imagem base JRE no lugar de JDK. JRE é usado apenas para executar aplicativos Java, portanto é mais leve
  pois não possui as ferramentas de desenvolvimento encontradas no JDK
- Criação de um usuário com privilégios limitados. Não é recomendado usar o usuário root.
- Utilizado Multi-Stage Building para reduzir o tamanho final da imagem. O Multi Stage permite construir ou aplicativo
  em uma imagem de compilação e depois copiar apenas os binários necessários para uma imagem de produção.

```bash
# Cria a imagem docker da aplicação
docker build -t todolist:1.0 .

# Cria o container com a imagem da aplicação
docker run --rm -p 8080:8080 todolist:1.0
```

## License

[MIT](https://choosealicense.com/licenses/mit/)
