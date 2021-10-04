#FROM maven AS build
#RUN sudo apt install maven
#RUN mvn -f $PWD/pom.xml clean package
#RUN  pwd  mvn clean package
# Use official base image of Java Runtim
FROM maven:3.5.2-jdk-8-alpine AS MAVEN
COPY pom.xml /build/
COPY src /build/src/
RUN mvn /build/ clean package

FROM openjdk:16-jdk-alpine

# Set volume point to /tmp
VOLUME /tmp
RUN mkdir ./tmp/product-images
# Make port 8080 available to the world outside container
EXPOSE 3000

#RUN pwd /mvnw package

# Set application's JAR file
ARG JAR_FILE=MAVEN /build/target/int222-0.0.1-SNAPSHOT.jar

# Add the application's JAR file to the container
ADD ${JAR_FILE} app.jar

# Run the JAR file
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

