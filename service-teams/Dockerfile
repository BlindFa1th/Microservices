FROM openjdk:13-alpine

WORKDIR /opt/server

COPY ./target/service-teams-0.0.1-SNAPSHOT.jar teamService.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar","userService.jar"]