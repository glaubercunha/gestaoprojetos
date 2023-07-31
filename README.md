Para compilar o projeto execute `mvn clean install`

Para rodar a aplicatação execute `java -jar target/gestaoprojetos-0.0.1-SNAPSHOT.jar`

Para inserir membros execute `curl -X POST http://localhost:8080/projeto/v1 -H 'Content-Type: application/json' -d '{ "pessoaId": 1, "projetoId": 33 }'`



