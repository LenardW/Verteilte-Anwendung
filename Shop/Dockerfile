#define base docker image 
FROM openjdk:19
LABEL maintainer="shop.microservice"
WORKDIR /
COPY target/shop-0.0.1-SNAPSHOT.jar shop-docker.jar
#EXPOSE 8010
ADD target/shop-0.0.1-SNAPSHOT.jar shop-docker.jar
ENV MYSQL_USER=shop
ENV MYSQL_PASSWORD=shop
EXPOSE 8761
#Entrypoint = Befehle, wie man lokal die jar ausführen würde
ENTRYPOINT ["java", "-jar", "/shop-docker.jar"]
