FROM openjdk:17-jdk-slim
WORKDIR /app
COPY gradlew gradlew.bat build.gradle ./
COPY gradle gradle
RUN ./gradlew dependencies
COPY src src
RUN ./gradlew bot.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/APP-0.0.1-SNAPSHOT.jar"]
