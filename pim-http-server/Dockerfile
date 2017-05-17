FROM java:8-jre

ENV VERTICLE_FILE build/libs/pim-http-server.jar

ENV VERTICLE_HOME /usr/verticles

EXPOSE 8888

COPY $VERTICLE_FILE $VERTICLE_HOME/
COPY config/docker.json $VERTICLE_HOME/

WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar pim-http-server.jar -conf docker.json"]