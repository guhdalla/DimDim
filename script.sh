#!/bin/bash
#sh 'nohup java -jar target/DimDim-0.0.1-SNAPSHOT.jar > /tmp/dimdimlog/log.log 2>&1 &'
export BUILD_ID=dontKillMe
export JENKINS_NODE_COOKIE=dontKillMe
nohup java -jar target/DimDim-0.0.1-SNAPSHOT.jar > /tmp/dimdimlog/log.log 2>&1 &
