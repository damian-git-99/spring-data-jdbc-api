FROM maven:3.6-jdk-11 as maven_build
WORKDIR /app
COPY pom.xml .
RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target/
COPY src ./src
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:11-slim-buster
WORKDIR /app
COPY --from=maven_build /app/target/jdbc_example-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar","jdbc_example-0.0.1-SNAPSHOT.jar"]