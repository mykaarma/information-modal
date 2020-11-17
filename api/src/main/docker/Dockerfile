FROM openjdk:11-jre-slim-buster
RUN apt-get update && apt-get install curl -y
ADD information-modal-api*.jar information-modal-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/information-modal-api.jar"]