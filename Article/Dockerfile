#define base docker image 
FROM openjdk:19
LABEL maintainer="article.microservice"
WORKDIR /
#COPY target/article-0.0.1-SNAPSHOT.jar article-docker.jar
#EXPOSE 8010
ADD target/article-0.0.1-SNAPSHOT.jar article-docker.jar
ENV MYSQL_USER=shop
ENV MYSQL_PASSWORD=shop
EXPOSE 8761
#Entrypoint = Befehle, wie man lokal die jar ausführen würde
ENTRYPOINT ["java", "-jar", "/article-docker.jar"]
