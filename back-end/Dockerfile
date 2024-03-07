FROM openjdk:17


WORKDIR /project
COPY target/i311-tp-fil-rouge-schahinheidari-1.0-SNAPSHOT.jar .
COPY src/main/resources .

CMD java -jar i311-tp-fil-rouge-schahinheidari-1.0-SNAPSHOT.jar --spring.config.location=application.properties

FROM python:3.12

WORKDIR /pythonFolder
COPY . /pythonFolder
RUN python --version && \
    pip3 --version

CMD ["python", "hello.py"]



