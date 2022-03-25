O projeto foi feito com java 8 e spring 2.2.13.RELEASE;  
Os gets e sets são definidos via anotação lombok.  

Seguem os passos para executar o projeto, considerando que maven e java estão configurados no path e o console está no diretório do projeto.  
1 - mvn clean install (adicionar -DskipTests caso queira ignorar os testes)  
	1.1 - mvn clean test (para rodar somente os testes)  
2 - java -jar target/GoldenAwards-0.0.1-SNAPSHOT.jar  

Para acessar os endpoints, basta executar no navegador ou postman as seguintes URLs:  

GET: http://localhost:8080/awards --> Retorna os produtores com maior e menor intevalo entre os prêmios;  

GET: http://localhost:8080/movies --> Retorna todos os filmes  
GET: http://localhost:8080/movies/{id} --> Retorna o filme pelo ID  
GET: http://localhost:8080/movies/winners --> Retorna todos os filmes vencedores  

GET: http://localhost:8080/producers --> Retorna todos os produtores  
GET: http://localhost:8080/producers/{id} --> Retorna o produtor pelo ID  
GET: http://localhost:8080/producers/more_than_one_award --> Retorna todos os produtores que têm mais de um prêmio  

Para acessar o console do banco H2, deve-se utilizar as seguintes credencias, que estão especificadas no arquivo do projeto application.properties  
usuario: pa  
senha: sa  