FROM openjdk:17


WORKDIR /App1
EXPOSE 9096
COPY /target/App1.jar /App1
CMD [ "java","-jar","App1.jar" ]

