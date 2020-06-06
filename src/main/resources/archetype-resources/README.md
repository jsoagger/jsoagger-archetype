

How to generate build and run a web application based on reactjs and spring boot.


# Prerequisites

Install following software:

1. maven
2. node.js
3. JAVA 11+
4. Docker desktop
 
 
# Configure maven

Add Nexitia's maven public repository in your maven settings:


```
<repository>
	<id>nexitia.com</id>
	<name>Nexitia releases</name>
	<url>http://nexus.nexitia.com/repository/nexitia-releases/</url>
	<snapshots>
		<enabled>true</enabled>
		<checksumPolicy>ignore</checksumPolicy>
	</snapshots>
	<releases>
		<enabled>true</enabled>
		<checksumPolicy>ignore</checksumPolicy>
	</releases>
</repository>
```
 
# Configure docker

Create following docker network and volume:

```
> docker volume create helloworld_data_volume

> docker network create test_network

```

# Build

First generate a docker image:

```
> cd helloWorld
> mvn clean install -P fatjar,docker
```

Then run and populate an h2 database engine inside docker: 

```
> mvn clean install -P integration-test

> docker ps -a

```


# Run web UI

Copy javascript files:

```
> cd helloworld-ui

> mvn clean install -P install-coreui

> mvn install -P copy-frontend

```

Install npm dependies:

```
> cd frontWorking

> npm install
```


Launch node server:

```
> package.json

```


# Enjoy

http://localhost:3000/

http://localhost:81/login.jsp


# Deploy to AWS

TODO

# Deploy to GCP

TODO

# Deploy to Azure

TODO





