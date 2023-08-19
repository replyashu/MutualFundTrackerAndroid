#
# Build stage
#

FROM eclipse-temurin:17-jdk-alpine AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/Mutual-Fund-Tracker-0.0.1-SNAPSHOT.jar funds.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","funds.jar"]
