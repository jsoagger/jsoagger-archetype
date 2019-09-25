
Maven achertype for starting quickly a full stack Java application with spring boot, React.js, JavaFX, docker, JPA, Hibernate and more famous java libraries.

# Prerquisites

1. Install maven
2. Install docker container
3. Intall JAVA 11

# Build
> mvn clean install


# Create a new multimodule project
> mvn archetype:generate -DarchetypeGroupId=io.github.jsoagger -DarchetypeArtifactId=jsoagger-fullstack-archetype -DarchetypeVersion=LATEST -DartifactId=helloWorld
 
 
# Build
> cd helloWorld
> mvn clean install -P install-reactapp,npm-install
> mvn clean install -P h2,docker


# Run with docker compose

> docker-compose -f compose-dev.yaml up

Browse http://localhost:3000/jsoagger/#c/login


