# Package This Project Using Maven Running this Command on Terminal
# > mvn clean
# > mvn package
# Run this Command on Terminal to Create the Docker Image
# > docker build -t healthcare:1.1 .
FROM amazoncorretto:17.0.8-alpine
VOLUME /tmp
# Cloud Environment
EXPOSE 8080
# Local Environment
# EXPOSE 8080
ARG JAR_FILE=target/healthcare-1.1.jar
ADD ${JAR_FILE} healthcare.jar
LABEL name="springboot-colors"
LABEL authors="Angel Hincho"
LABEL mainteiner="ahincho"
COPY target/healthcare-1.1.jar healthcare.jar
ENTRYPOINT ["java","-jar","/healthcare.jar"]