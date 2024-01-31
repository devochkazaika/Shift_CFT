FROM openjdk:17-jdk-slim

# Debugging: Print the contents of the build context
COPY ./build/libs/template-0.0.1.jar /opt/service.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/wallet
ENV POSTGRES_USER=wallet
ENV POSTGRES_PASSWORD=wallet
EXPOSE 8080
CMD exec java -jar /opt/service.jar