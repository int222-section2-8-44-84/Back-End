FROM openjdk:16-jdk-alpine
VOLUME /tmp
EXPOSE 3000
ARG JAR_FILE=target/intregratedbackend-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"] 