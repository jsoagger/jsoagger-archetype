
Maven achertype for  quickly start full stack Java application development with spring boot, React.js, JavaFX, docker, JPA, Hibernate and more famous java libraries.


# Prerequisites

1. Install maven
2. Install node.js
3. Install JAVA 11

 
# Create and run a project without docker container

## Create a project

Create multi-module maven project with JSoagger archetype:

```
> mvn archetype:generate -DarchetypeGroupId=io.github.jsoagger -DarchetypeArtifactId=jsoagger-fullstack-archetype -DarchetypeVersion=LATEST -DartifactId=helloWorld
 ```

Move into the newly created project and execute profile for installing node.js modules and react.js modules:

```
> cd helloWorld
> mvn clean install -P install-reactapp,npm-install
```

## Launch h2 database

The artefact is delivered with ready to test h2 database, run it with the following command:

```
./h2/bin/h2.sh
```

Be aware, you must run exactly above command, otherwise, h2 will not find the database file.

H2 url inside the web console must be : jdbc:h2:tcp://localhost/./_docker-compose/data/foundation;MVCC=TRUE;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1


## Run the backend

```
java ./microservice/target/microservice-1.0.0-SNAPSHOT-sb.jar --spring.config.location=file:../_docker-compose/server/application-local.properties 
```

The backend will connect to the database via url : jdbc:h2:tcp://localhost/./_docker-compose/data/foundation

## Run the front end

The front end must be ran on top of the web folder:

```
> cd web
```

```
> REACT_APP_BACKEND_HOST=http://localhost:8080/jsoagger/ npm start
```

And browse the application at url: http://localhost:3000/#/login


## Run the mobile application
```
Mobile UI: java -jar mobile/target/mobile-1.0.0-SNAPSHOT.jar --jsoagger.client.mode=simul_mobile
```

# Create and run a project with docker container

In this case, docker desktop must be installed in your local environment.

Create multi-module maven project with Jsoagger archetype:

```
> mvn archetype:generate -DarchetypeGroupId=io.github.jsoagger -DarchetypeArtifactId=jsoagger-fullstack-archetype -DarchetypeVersion=LATEST -DartifactId=helloWorld
```

## Build the project
```
> cd helloWorld
> mvn clean install -P install-reactapp,npm-install
> mvn clean install -P h2,docker
```

## Launch them with docker-compose

```
> docker-compose -f compose-dev.yaml up
```

## Web application

```
REACT_APP_BACKEND_HOST=http://localhost:8080/jsoagger/serv/core npm start
```

And browse http://localhost:3000/#/login

## Desktop application

```
java -jar desktop/target/desktop-1.0.0-SNAPSHOT.jar --jsoagger.client.mode=desktop
```

## Mobile application
```
Mobile UI: java -jar mobile/target/mobile-1.0.0-SNAPSHOT.jar --jsoagger.client.mode=simul_mobile
```

