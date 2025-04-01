FROM maven:3.9.9-amazoncorretto-17-debian-bookworm AS maven_deps

WORKDIR /MYECOMMERCE

COPY ./pom.xml /MYECOMMERCE/pom.xml

RUN mvn dependency:go-offline

FROM maven:3.9.9-amazoncorretto-17-debian-bookworm AS maven_build

WORKDIR /MYECOMMERCE

COPY --from=maven_deps /MYECOMMERCE/target/dependency /MYECOMMERCE/target/dependency

COPY ./pom.xml /MYECOMMERCE/pom.xml

COPY ./src/ /MYECOMMERCE/src/

ARG MAVEN_OPTS="clean package"

RUN mvn $MAVEN_OPTS

# FROM tomcat:11.0.5-jdk17-temurin-jammy
FROM amazoncorretto:17.0.14-alpine3.21

COPY --from=maven_build /MYECOMMERCE/target/*.jar ./app.jar

EXPOSE 8383

HEALTHCHECK --interval=5s --timeout=10s --start-period=5s --retries=5 CMD [ "curl", "-f", "http://localhost:8080/" ]

CMD ["sh", "-c", "java -Dserver.port=8080 -jar app.jar"]



