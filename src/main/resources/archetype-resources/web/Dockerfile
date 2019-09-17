# docker build -t jsoagger/jsoagger-web:latest

# base image
FROM node:12.2.0-alpine

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install and cache app dependencies
COPY package.json /app/package.json
RUN npm install --silent
RUN npm install react-scripts@3.0.1 -g --silent

COPY public/ /app/public
COPY src/ /app/src

RUN ls -la /app/

RUN npm rebuild node-sass
RUN npm run build

# start app
# JSOAGGER_API_URL value is PLEASE_SET_JSOAGGER_API_URL_IN_YOUR_ENV at build time
ARG JSOAGGER_API_URL="PLEASE_SET_JSOAGGER_API_URL_IN_YOUR_ENV"

# a run time it will be overriden by JSOAGGER_API_URL for env
ENV JSOAGGER_API_URL="${JSOAGGER_API_URL}"

# start app
ENTRYPOINT REACT_APP_BACKEND_HOST=$JSOAGGER_API_URL npm start