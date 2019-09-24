# jsoagger-archetype

# Build
> mvn clean install

# Create a new multimodule project
> mvn archetype:generate -DarchetypeGroupId=io.github.jsoagger -DarchetypeArtifactId=jsoagger-fullstack-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DartifactId=sicommander
 
# Build an run
> cd helloWorld
> mvn clean install -P install-reactapp,npm-install
> mvn clean install -P h2,docker

> docker-compose -f compose-dev.yaml up


# How to generate archetype from existing project
## Remove node_module before generating achetype!!!

> mvn archetype:create-from-project


