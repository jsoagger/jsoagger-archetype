# Generate a fullstack project

> mvn archetype:generate -DarchetypeGroupId=io.github.jsoagger -DarchetypeArtifactId=jsoagger-fullstack-archetype -DarchetypeVersion=LATEST -DartifactId=helloWorld

# Build the project

> cd helloWorld

> mvn clean install -P install-reactapp,npm-install

> mvn clean install -P h2,docker


# Run it!

> docker-compose -f compose-dev.yaml up

# Browse

CORS must be disabled from your browser configuration.

> http://localhost:3000/#/c/login


# Launch desktop and mobile applications

java -jar desktop/target/desktop-1.0.0-SNAPSHOT.jar --jsoagger.client.mode=desktop
java -jar desktop/target/desktop-1.0.0-SNAPSHOT.jar --jsoagger.client.mode=simul_mobile

