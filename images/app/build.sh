#!/bin/bash

cp -v ../../dock-app/target/dock-app-1.0-SNAPSHOT.ear .
docker build -t docproto/app .