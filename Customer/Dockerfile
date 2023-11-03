#define base docker image 
FROM openjdk:19
LABEL maintainer="customer.microservice"
WORKDIR /
#COPY target/customer-0.0.1-SNAPSHOT.jar customer-docker.jar
#EXPOSE 8010
ADD target/customer-0.0.1-SNAPSHOT.jar customer-docker.jar
ENV MYSQL_USER=shop
ENV MYSQL_PASSWORD=shop
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/customer-docker.jar"]
