FROM java:8-jdk-alpine

COPY ./target/crudApp-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch crudApp-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","crudApp-0.0.1-SNAPSHOT.jar"]  

EXPOSE 8090