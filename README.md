> this repo was derived/separated from [This one](https://github.com/ubmagh/springboot-microservices)


# Communicating microservices using eureka (REST apis) With  a gateway
 
## Quick notes

> for this project, you'll have to start these apps in the order : eureka server -> customer-service -> billing-service -> gateway. <br> this is because the billing service needs to query some data from customer service, and both of them need to be registered on eureka.

⭐ have a look on exceptions management and Http codes returning.

<br>

##  Project architecture : 

<img src="./images/1.png" width="500px">

<br>

<img src="./images/2.png" width="500px">

<br>
<br>

# Project repport

## Creating microservices 

[👉 Creating Eureka discovery service/micro-service (& dockerizing it)](./eureka-discovery-service/)


[👉 Creating Customer micro-service (& dockerizing it)](./customer-service/)


[👉 Creating Billing micro-service (& dockerizing it)](./billing-service/)


[👉 Creating GateWay micro-service (& dockerizing it)](./gateway/)


<br>

## Running microservices on docker compose :

The [Docker-compose.yaml](./Docker-compose.yaml) is configured properly to run all the microservices together, note that microservices `CUSTOMER-SERVICE` and `BILLING-SERVICE` are not accessible directly, you need either to uncomment port mapping or pass through gateway, ex : http://localhost:8888/CUSTOMER-SERVICE/api/customers

> `docker-compose up`

* running containers : 

<p align="center">
    <img src="./images/3.png">
</p>

<br>

## Running microservices on kubernetes (K8s) :




<br>
<br>

## Project deep explanation (in french)  :

https://www.youtube.com/watch?v=tpCIvZ5QSAs

https://www.youtube.com/watch?v=-fzjrCjTZ6o

https://www.youtube.com/watch?v=_f-LS0Z2CTM

https://www.youtube.com/watch?v=fXYlKpI_XNk

https://www.youtube.com/watch?v=XRUf6k6YCzA
