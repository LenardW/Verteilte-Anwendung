# VerteilteAnwendung


##Local 
1. Start XAMPP with MYSQL & Apache

###Start as Spring boot app: 
2. Eureka 
3. ShopAPIGateway, Shop, Order, Article, Customer
4. open Postman to send HTTP-Requests 

##Via Docker 
Start DB (not in Docker yet)
1. Run each Project with Maven build package or clean install 

2. a VerteilteAnwendung im Terminal "docker-compose build" & "docker-compose up" 

   b for each Project im Terminal "docker build -t COMPONENT:latest ." & "docker run -p 80xx:80xx COMPONENT" 

