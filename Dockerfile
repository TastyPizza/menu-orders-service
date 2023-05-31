FROM openjdk:19-slim

ARG JAR_FILE=build/libs/*SNAPSHOT.jar

COPY $JAR_FILE /menu-orders-service.jar

ENTRYPOINT exec java -jar /menu-orders-service.jar