

How to generate build and run a web application based on reactjs and spring boot.


# Prerequisites

Install following software:

1. maven
2. node.js
3. JAVA 11+
4. Docker desktop
 
 
 
# Configure maven

Add Nexitia's maven public repository in your maven settings.

You can check location for default setting.xml used by maven with following command:

```
> mvn help:effective-settings
```

And then add bellow repository inside a profile:

```
<repositories>
	<repository>
		<id>nexitia.com</id>
		<name>Nexitia releases</name>
		<url>http://nexus.nexitia.com/repository/nexitia-releases/</url>
		<releases>
			<enabled>true</enabled>
			<checksumPolicy>ignore</checksumPolicy>
		</releases>
	</repository>
</repositories>
```
 
# Generate a project

With maven archetype:

```
 > mvn archetype:generate -DarchetypeGroupId=io.github.jsoagger -DarchetypeArtifactId=jsoagger-web-archetype -DarchetypeVersion=LATEST -DartifactId=helloWorld
```

# Create docker image

```
> cd helloWorld
> mvn clean install -P fatjar,docker
```

Create following docker network and volume:

```
> docker volume create helloworld_data_volume

> docker network create test_network

```

Run an h2 database, start the backend and load some datas: 

```
> mvn clean install -P integration-test

> docker ps -a

```

# Build and run Reactjs web UI


```
> cd helloworld-ui

> mvn clean install -P install-coreui,copy-frontend

```

Install npm dependies:

```
> cd frontWorking

> npm install
```


Launch node server:

```
> REACT_APP_CONTEXT_ROOT=helloworld npm start

```


# Enjoy it

http://localhost:3000/

http://localhost:81/login.jsp


# Deploy to AWS

TODO

# Deploy to GCP

TODO

# Deploy to Azure

TODO



