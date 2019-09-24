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

java -jar desktop/target/desktop-1.0-SNAPSHOT.jar --jsoagger.client.mode=desktop
java -jar desktop/target/desktop-1.0-SNAPSHOT.jar --jsoagger.client.mode=simul_mobile


# Load datas

You can delete the generated h2 database and load datas by running following container.

Be aware of network 'helloworld_the_soagg_network', server is reachable only in a scpecific network. 

> docker run --rm --name soagg-foundation-data --network=helloworld_the_soagg_network -v  ./_docker-compose/manager:/spring-config/ jsoagger/soagg-foundation-data:1.0.1



