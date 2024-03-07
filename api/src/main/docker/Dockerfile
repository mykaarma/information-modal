FROM docker.io/library/amazoncorretto:11-alpine3.17
RUN apk add --update curl
ADD information-modal-api*.jar information-modal-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/information-modal-api.jar"]
