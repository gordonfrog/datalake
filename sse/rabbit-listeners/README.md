Os projetos estão separado da seguinte forma:

producer-api (aplicação Spring Boot que publica mensagens no RabbitMQ)
producer-ui (aplicação Angular que enviar dados para o producer-api)
consumer-api (aplicação Spring Boot que consome mensagens do RabbitMQ)
consumer-ui (aplicação Angular que está conectada via WebSocket na consumer-api e demonstra as mensagens recebidas)