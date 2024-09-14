FROM openjdk:17


WORKDIR /app1
EXPOSE 9090
COPY ./target/App1.jar /app1
CMD [ "java","-jar","App1.jar" ]