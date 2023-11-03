#define base docker image 
FROM openjdk:19
LABEL maintainer="apiGateway.microservice"
WORKDIR /
ADD target/ShopAPIGateway-0.0.1-SNAPSHOT.jar gateway-docker.jar
#Entrypoint = Befehle, wie man lokal die jar ausführen würde
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/gateway-docker.jar"]
