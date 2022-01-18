FROM openjdk:13-alpine

WORKDIR /opt/server

COPY ./target/service-roles-0.0.1-SNAPSHOT.jar roleService.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar","userService.jar"]