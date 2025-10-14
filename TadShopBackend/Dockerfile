FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} TadShopBackend.jar
ENTRYPOINT ["java", "-jar", "TadShopBackend.jar"]
EXPOSE 8080