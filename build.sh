#!/bin/sh

sudo docker-compose down

./firstservice/gradlew :assemble -p ./firstservice/
./secondservice/gradlew :assemble -p ./secondservice/
./thirdservice/gradlew :assemble -p ./thirdservice/

sudo docker-compose up --build -d
