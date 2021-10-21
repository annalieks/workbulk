FROM openjdk:11 AS build
COPY . /backend
WORKDIR /backend
RUN ./gradlew clean build

FROM openjdk:11

RUN mkdir /app

COPY --from=build /backend/build/libs/*.jar /app/workbulk.jar

ENTRYPOINT ["java", "-jar","/app/workbulk.jar"]