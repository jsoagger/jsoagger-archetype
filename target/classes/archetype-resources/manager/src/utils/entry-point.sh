#!/bin/bash

set -e

file=runcli.sh
chmod +x "$file"

echo "Replacing __B_ACKEND_REST_URL_ by $_M_ANAGED_BACKEND_REST_URL"
sed -i -e "s#BACKEND_REST_URL#$MANAGED_BACKEND_REST_URL#g" "$file"

echo "Replacing __S_PRING_APPLICATION_JSON by __$__S__PRING_APPLICATION_JSON"
sed -i -e "s#SPRING_APPLICATION_JSON#$SPRING_APPLICATION_JSON#g" "$file"

trap : TERM INT; (while true; do sleep 1000; done) & wait
