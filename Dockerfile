FROM maven:3.9.5-eclipse-temurin-17-alpine AS build

# Define um diretório de trabalho padrão
WORKDIR /app

# Copia apenas o arquivo POM e o arquivo de definição de dependências
COPY pom.xml .
COPY src src

# Executa o build do Maven
RUN mvn clean package

FROM eclipse-temurin:17-jre-alpine

# Defina um diretório de trabalho padrão
WORKDIR /app

# Cria um grupo e usuário javauser
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser

# Expoem a porta 8080
EXPOSE 8080

# Copia o arquivo JAR construído a partir da fase de build
COPY --from=build /app/target/todolist-1.0.0.jar app.jar

# Define as permissões de um diretório dentro do contêiner
RUN chown -R javauser:javauser /app

# Define o usuário padrão que será usado a partir de agora (não pode ser o root)
USER javauser

# ENTRYPOINT com o comando Javapara executar
ENTRYPOINT ["java", "-jar", "app.jar"]