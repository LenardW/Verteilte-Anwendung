#define base docker image 
FROM openjdk:19
LABEL maintainer="order.microservice"
WORKDIR /
ADD target/order-0.0.1-SNAPSHOT.jar order-docker.jar
ENV MYSQL_USER=shop
ENV MYSQL_PASSWORD=shop
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/order-docker.jar"]
