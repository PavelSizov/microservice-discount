FROM openjdk:8-jre-alpine
EXPOSE 8082
COPY /target/discount-0.0.1-SNAPSHOT.jar discount.jar
ENTRYPOINT ["java", "-jar", "discount.jar"]