This directory contains a *Dockerfile* to build a Docker image containing the
application. Look in *../db* for the Docker image containing the database.

## Requirements

Download the MySQL JDBC driver mysql-connector-java-5.1.40-bin.jar: https://dev.mysql.com/downloads/connector/j/

## Starting the Container

Note that in the example below *db* is the name of the database container.

docker run --name app -P -d --link db:container-db docproto/app