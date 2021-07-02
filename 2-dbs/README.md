# 2-DBs
Aplicação CRUD que faça conexão a 2 bancos de dados diferentes e com mais de um schema em cada uma delas.

## Requisitos
* JDK 1.8+
* Postgres
* Spring Boot

## Executando o projeto
Após criar os schemas 'migration' e 'oficial' no seu banco de Dados Postgres, execute o script contido na pasta src/main/resources/db/script.sql
Ele deverá criar as duas tabelas e incluir um registro na respectiva tabela.
Se toda configurações estiverem corretas, executar o Application, o qual deverá ser gerado as seguintes linhas no console.
ProductEntity(id=3cbbcd26-c177-4277-b3df-bf4d9389f69d, name=test-product, price=13.02)
ServiceEntity(id=67b6dcb6-c28c-4965-b9f7-5c830a04664d, name=test1, price=10.1)