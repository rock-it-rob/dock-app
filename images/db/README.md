This directory contains a *Dockerfile* to build the database Docker image. Look
in *../app* for the build file for the application.

## Starting the container

docker run --name db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=rob -d docproto/db