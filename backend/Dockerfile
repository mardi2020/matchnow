FROM openjdk:11

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY gradlew $APP_HOME
COPY build.gradle $APP_HOME
COPY settings.gradle $APP_HOME
COPY gradle $APP_HOME/gradle

RUN ./gradlew build || return 0

COPY src src

RUN ./gradlew bootjar

FROM openjdk:11

ARG JAR_FILE=build/libs/MatchNow-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY --from=0 $APP_HOME/${JAR_FILE} /app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]