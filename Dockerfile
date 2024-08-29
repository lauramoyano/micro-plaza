FROM openjdk:17
COPY build/libs/plaza-micro.jar /app.jar
EXPOSE 8090
CMD ["java", "-jar", "/app.jar"]
