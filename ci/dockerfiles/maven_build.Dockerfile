FROM maven:3.9.9-amazoncorretto-17-debian-bookworm AS maven_build

WORKDIR /MYECOMMERCE

COPY ./pom.xml /MYECOMMERCE/pom.xml

COPY ./src/ /MYECOMMERCE/src/

RUN mvn clean package

# FROM tomcat:11.0.5-jdk17-temurin-jammy
FROM amazoncorretto:17.0.14-alpine3.21

COPY --from=maven_build /MYECOMMERCE/target/*.jar ./app.jar

EXPOSE 8383

HEALTHCHECK --interval=5s --timeout=10s --start-period=5s --retries=5 CMD [ "curl", "-f", "http://localhost:8383/" ]

CMD ["sh", "-c", "java -Dserver.port=8383 -jar app.jar"]



