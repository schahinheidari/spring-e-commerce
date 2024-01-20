FROM openjdk:17


WORKDIR /project
COPY target/i311-tp-fil-rouge-schahinheidari-1.0-SNAPSHOT.jar .
COPY src/main/resources .

CMD java -jar i311-tp-fil-rouge-schahinheidari-1.0-SNAPSHOT.jar --spring.config.location=application.properties



