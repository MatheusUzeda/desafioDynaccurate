# Desafio Dynaccurate 
API com crud completo de usuário e captura de eventos gerados por um usuário específico
> Tecnologias: Spring-boot, MongoDb, e RabbitMq

# <h3> Instalação </h3>

Requisitos: Java11, MongoDb e RabbitMq

> Para executar e testar essa Api, são necessários alguns passos:

> Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.

1. Clone o projeto desafioDynaccurate (Api)

2. No mesmo work-space, clone o projeto consumidorEvento (Trabalhador), repositório: https://github.com/MatheusUzeda/consumerEvento.git 

3. Clone também o projeto biblioteca (contém os objetos que serão serializados e desserializados), repositório: https://github.com/MatheusUzeda/bibliotecaDynaccurate.git

> Importe esses três projetos para o eclipse ou sua IDE de preferência.

4. Tenha o mongoDb instalado na sua máquina, e configure sua porta para 27017(porta padrão).

MongoDb Instalação: https://docs.mongodb.com/manual/installation/

5. Tenha o rabbitMQ instalado e em execução na sua máquina, e configure sua porta para 5672(porta padrão)

RabbitMQ Instalação: https://www.rabbitmq.com/download.html

> Com os três projetos importados e esses passos realizados, podemos inicializar a nossa aplicação

6. Execute a classe de inicialização do projeto desafioDynaccurate, se o rabbitMQ não estiver em execução e configurado corretamente não será possivel inicializar, o mesmo vale para o mongoBD.  

7. Execute também o projeto consumerEvento, nele está o nosso consumidor da fila Evento, só será possivel realizar um cadastro de evento, se ele estiver em execução.

> Com a aplicação e o Trabalhador(consumerEvento) em execução, podemos iniciar os serviços da Aplicação

8. Para isso acesse em: http://localhost:8080/swagger-ui.html#/

> Nesse endereço está a documentação da Api e seus endpoints, e exemplos de testes. 


