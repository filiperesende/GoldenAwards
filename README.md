O projeto foi feito com java 8 e spring 2.2.13.RELEASE;

Seguem os passos para executar o projeto, considerando que maven e java estão configurados no path e o console está no diretório do projeto.
1 - mvn clean install (adicionar -DskipTests caso queira ignorar os testes)
2 - java -jar target/GoldenAwards-0.0.1-SNAPSHOT.jar

Para acessar o endpoint, basta executar no navegador ou postman a seguinte URL:
GET: http://localhost:8080/awards

Para acessar o console do banco H2 utilizar as seguintes credencias, que estão especificadas no arquivo do projeto application.properties
usuario: pa
senha: sa