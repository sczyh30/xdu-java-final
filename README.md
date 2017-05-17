# XDU Java Final Project

[![Build Status](https://travis-ci.org/sczyh30/xdu-java-final.svg?branch=master)](https://travis-ci.org/sczyh30/xdu-java-final)

This is the repository of XDU Java Final Project (2017) - PIM.

## Compile/Run

To compile the project you need to install **Gradle**.
In the root directory, execute the command to compile the project:

```bash
gradle clean build
```

### Start the server with Docker Compose

It's highly recommended to start the PIM server with Docker Compose:

```bash
cd pim-http-server/docker
docker-compose up --build
```

### Start the server in local

The default server implementation uses MongoDB as the backend so
ensure your MongoDB instance running in the default port (or any other address, but needs configuration).
Then execute the command to start the PIM server: 

```bash
java -jar pim-http-server/build/libs/pim-http-server.jar -conf pim-http-server/config/local.json
```

### Run the GUI

Finally you can run the PIM GUI application (or directly open it):

```bash
java -jar pim-gui/build/libs/pim-gui.jar
```
