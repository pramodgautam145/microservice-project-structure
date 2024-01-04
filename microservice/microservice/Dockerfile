FROM openjdk:8-jdk-alpine
EXPOSE 8092
ARG JAR_FILE=target/microservice-0.0.1-SNAPSHOT.jar
# Add the application's jar to the container
ADD ${JAR_FILE} my-application.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/my-application.jar"]


