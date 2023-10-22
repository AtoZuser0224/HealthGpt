FROM openjdk:11-jdk

ARG JAR_FILE_PATH=build/libs/demo-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE_PATH} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]