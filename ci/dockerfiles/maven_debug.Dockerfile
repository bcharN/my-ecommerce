FROM maven:3.9.9-amazoncorretto-23-debian-bookworm AS maven_build

WORKDIR /MYECOMMERCE

VOLUME ./../source/src/
VOLUME /root/.m2
VOLUME ./../source/pom/

EXPOSE 8000
# RUN mvn clean package
# RUN /usr/share/maven/bin/mvnDebug clean package
CMD [ "/usr/share/maven/bin/mvnDebug","clean","package" ]

