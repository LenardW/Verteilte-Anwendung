#define base docker image 
FROM openjdk:19
LABEL maintainer="serviceDiscovery.microservice"
WORKDIR /
COPY target/shopServiceDiscovery-0.0.1-SNAPSHOT.jar serviceDiscovery-docker.jar
#ADD target/shopServiceDiscovery-0.0.1-SNAPSHOT.jar serviceDiscovery-docker.jar
#Entrypoint = Befehle, wie man lokal die jar ausführen würde
#ENTRYPOINT ["java", "-jar", "serviceDiscovery-docker.jar"]

#ARG JAR_FILE=target/*.jar
#ADD ${JAR_FILE} app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "serviceDiscovery-docker.jar"]
