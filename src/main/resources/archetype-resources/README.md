> mvn archetype:generate -DarchetypeGroupId=io.github.jsoagger -DarchetypeArtifactId=jsoagger-fullstack-archetype -DarchetypeVersion=LATEST -DartifactId=helloWorld

> cd helloWorld

> mvn clean install -P install-reactapp,npm-install

> mvn clean install -P h2,docker

> docker-compose -f compose-dev.yaml up

> http://localhost:3000/#/c/login


java -jar desktop/target/desktop-1.0-SNAPSHOT.jar --jsoagger.client.mode=desktop
java -jar desktop/target/desktop-1.0-SNAPSHOT.jar --jsoagger.client.mode=simul_mobile



1. BUILD
> mvn clean install -P h2,docker

2. HOW TO RUN
>> You must provide file : spring-app.admin.properties
>> This file contains the spring properties

> java -jar manager-sb.jar --ep.remote.host=http://localhost:8080/jsoagger --spring.config.location=file:./_docker-compose/server/application.properties
> docker run -d --rm -e MANAGED_BACKEND_REST_URL='http://soagg-foundation-srv:8080/jsoagger' -v ./_docker-compose/server/:/spring-config/ helloworld/starter-manager:1.0-SNAPSHOT


