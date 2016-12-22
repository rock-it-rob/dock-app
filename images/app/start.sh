#!/bin/bash

docker run --name app -p 8080:8080 -d --link db:container-db docproto/app
