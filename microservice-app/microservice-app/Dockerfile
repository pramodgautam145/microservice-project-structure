FROM openjdk:8-jdk-alpine
EXPOSE 8093
ARG JAR_FILE=target/microservice-app-0.0.1-SNAPSHOT.jar
# Add the application's jar to the container
ADD ${JAR_FILE} microservice-app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/microservice-app.jar"]


