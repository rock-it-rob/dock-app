#!/bin/bash

docker run -p 3306:3306 --name db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=rob -d docproto/db
