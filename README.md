

How to generate build and run a web application based on reactjs and spring boot.


# Prerequisites

Install following software:

1. maven
2. node.js
3. JAVA 11+
4. Docker desktop
 
 
# Generate a web project

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
 
Generate a project:

```
> mvn archetype:generate -DarchetypeGroupId=io.github.jsoagger -DarchetypeArtifactId=jsoagger-web-archetype -DarchetypeVersion=LATEST -DartifactId=helloWorld

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

> mvn install -P install-coreui

> npm install -P copy-frontend

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


# Enjoy

http://localhost:3000/

http://localhost:81/login.jsp




