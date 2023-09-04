FROM eclipse-temurin
VOLUME /tmp
EXPOSE 9092
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]