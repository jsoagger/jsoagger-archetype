#!/bin/bash

# _B_ACKEND_REST_URL is replaced i sed script before copying in it inside image
# _S_PRING_APPLICATION_JSON is replaced i sed script before copying in it inside image
#
#

java -Djava.security.egd=file:/dev/./urandom -jar /myproject-cli/app.jar --ep.remote.host=BACKEND_REST_URL --spring.config.location=file:/spring-config/application.properties


