#FROM openjdk:17-slim
#
#ADD ./build/libs/deploy-0.0.1-SNAPSHOT.jar a.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "a.jar"]

FROM openjdk:17-slim as builder
WORKDIR application
ARG JAR_FILE=./build/libs/deploy-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:jre-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]