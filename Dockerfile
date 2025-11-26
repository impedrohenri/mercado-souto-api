# Use imagem oficial do OpenJDK (escolha a versão compatível com seu projeto)
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o pom.xml e os arquivos de configuração necessários
COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./

# Copie o restante do código-fonte
COPY src src

# Conceda permissão de execução ao mvnw (caso use o wrapper Maven)
RUN chmod +x mvnw

# Compile / empacote a aplicação — por exemplo, usando Maven wrapper
RUN ./mvnw clean package -DskipTests

# Aplique variáveis de ambiente se você usar um .env (opcional)
# ENV SPRING_PROFILES_ACTIVE=prod
DB_PASSWORD='teste'
DB_NAME='mercado-souto-database'
DB_PORT='4378'
DB_USER='root'

# Exponha a porta que sua aplicação usa
EXPOSE 8080

# Comando para iniciar a aplicação — ajuste conforme o nome do JAR gerado
CMD ["java", "-jar", "target/mercado-souto-api-0.0.1-SNAPSHOT.jar"]
