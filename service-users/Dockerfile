FROM openjdk:13-alpine

WORKDIR /opt/server

COPY ./target/service-users-0.0.1-SNAPSHOT.jar userService.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar","userService.jar"]