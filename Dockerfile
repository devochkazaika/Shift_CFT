FROM openjdk:17-jdk-slim
COPY ./build/libs/template-0.0.1.jar /opt/service.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://database:5430/wallet
ENV POSTGRES_USER=wallet
ENV POSTGRES_PASSWORD=wallet
EXPOSE 8080
CMD java -jar /opt/service.jar