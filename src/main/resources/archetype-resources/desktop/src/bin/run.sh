#!/bin/bash

SCRIPT_PATH=`dirname $0`

# no arguments passed, default desktop mode
if [[ $# -eq 0 ]]
	then
    	APP_MODE="desktop"
	else
		APP_MODE=$1
fi

echo "Running in $APP_MODE"
 
# Run in classsic classpath mode
java -jar $SCRIPT_PATH/bin/myproject.jar --jsoagger.client.mode=$APP_MODE
