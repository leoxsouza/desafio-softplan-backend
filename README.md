# Desafio Softplan Backend
### Para rodar o projeto via docker basta rodar o comando
    docker run -p 8080:8080 leoxsouza/pessoas:1.0

### Assim ficará disponível o endpoint público:
    GET: localhost:8080/source

### E os seguintes endpoints protegidos da api.
    usuário: meta, senha: meta
    GET (Listagem de pessoas): localhost:8080/api/pessoas
    POST (Inserir pessoa): localhost:8080/api/pessoas
    GET (remover pessoa pelo id): localhost:8080/api/pessoas/remover/{id} 
    GET  (buscar pessoa pelo id): localhost:8080/api/pessoas/{id}
    
### Como alternativa a imagem do docker hub, é possível rodar o docker-compose baixando o código do projeto e executar os seguintes comandos na raíz do projeto
    mvn clean install
    docker-compose up -d --build
 
    
