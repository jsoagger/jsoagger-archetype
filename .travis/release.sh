#!/usr/bin/env bash

set -e

#echo "Ensuring that pom <version> matches $TRAVIS_TAG"
#mvn org.codehaus.mojo:versions-maven-plugin:2.5 -DnewVersion=$TRAVIS_TAG


echo "About to deploy release version"
mvn clean deploy --settings .maven.xml -DskipTests=true -Dmaven.javadoc.skip=true -B -U -Prelease

