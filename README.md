
Maven achertype for  quickly start full stack Java application development with spring boot, React.js, JavaFX, docker, JPA, Hibernate and more famous java libraries.

# Prerquisites

1. Install maven
2. Install docker container
3. Intall JAVA 11


# Create project

```
> mvn archetype:generate -DarchetypeGroupId=io.github.jsoagger -DarchetypeArtifactId=jsoagger-fullstack-archetype -DarchetypeVersion=LATEST -DartifactId=helloWorld
 ```
 
# Build

```
> cd helloWorld
> mvn clean install -P install-reactapp,npm-install
> mvn clean install -P h2,docker
```


# Run with docker compose

```
> docker-compose -f compose-dev.yaml up
```

## Web application

Browse http://localhost:3000/jsoagger/#c/login


## Desktop application

```
java -jar desktop/target/desktop-1.0.0-SNAPSHOT.jar --jsoagger.client.mode=desktop
```


## Mobile application
```
Mobile UI: java -jar desktop/target/desktop-1.0.0-SNAPSHOT.jar --jsoagger.client.mode=simul_mobile
```


Mobile UI: java -jar desktop/target/desktop-1.0.0-SNAPSHOT.jar --jsoagger.client.mode=simul_mobile
