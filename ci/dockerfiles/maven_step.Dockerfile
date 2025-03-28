FROM maven:3.9.9-amazoncorretto-17-debian-bookworm 

WORKDIR /MYECOMMERCE

VOLUME ./../source/src/
VOLUME /root/.m2
VOLUME ./../source/pom/

VOLUME ./../source/output/


CMD ["sh", "-c", " cp -r ../source/src/. ./src/ ; cp -r ../source/pom/pom.xml ./pom.xml  ; ls ; mkdir -p ./target  ; mvn clean package ; cp ./target/*.jar ../source/output/my-ecommerce.jar"]

