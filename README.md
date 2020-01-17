
Maven achertype for  quickly start full stack Java application development with spring boot, React.js, JavaFX, docker, JPA, Hibernate and more famous java libraries.


# Prerequisites

1. Install maven
2. Install node.js
3. Install JAVA 11

 
# Project quick start

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
> cd helloWorld

> chmod 777 ./_docker-compose/h2/bin/h2.sh
> ./_docker-compose/h2/bin/h2.sh &
```

Be aware, you must run exactly above command (under helloWorld directory), otherwise, h2 will not find the database file.


## Run the backend

```
> cd helloWorld

> java -jar ./microservice/target/microservice-1.0.0-SNAPSHOT-sb.jar --spring.config.location=file:_docker-compose/server/application-local.properties 
```

## Run the front end

The front end must be ran on top of the web folder:

```
> cd helloWorld/web
```

```
> REACT_APP_BACKEND_HOST=http://localhost:8080/jsoagger/ npm start
```

And browse the application at url: http://localhost:3000/#/login


## Run the mobile application

```
> cd helloWorld
> java -jar mobile/target/mobile-1.0.0-SNAPSHOT.jar --jsoagger.client.mode=simul_mobile
```




# Dockerize the application

## Build the project

```
> cd helloWorld
```

Build the docker containers:

```
> mvn clean install -P h2,docker
```


## Start the backend and the database with docker-compose

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


# Add ECommerce module example

## Web ecommerce UI

Add following XML fragment inside pom.xml of web module

```
<profile>
			<id>install-soaggyshop-web</id>
			<properties>
				<skip.docker.build>true</skip.docker.build>
			</properties>	
			<build>
				<plugins>
					<plugin>
						<groupId>org.apache.maven.plugins</groupId>
						<artifactId>maven-dependency-plugin</artifactId>
						<executions>
							<execution>
								<id>unpack</id>
								<phase>generate-sources</phase>
								<goals>
									<goal>unpack</goal>
								</goals>
								<configuration>
									<artifactItems>
										<artifactItem>
											<groupId>io.github.jsoagger</groupId>
											<artifactId>soaggyshop-web</artifactId>
											<version>1.0.0-SNAPSHOT</version>
											<outputDirectory>${project.build.directory}/soaggyshop-src</outputDirectory>
										</artifactItem>
									</artifactItems>
								</configuration>
							</execution>
						</executions>
					</plugin>
					<plugin>
						<artifactId>maven-resources-plugin</artifactId>
						<version>3.1.0</version>
						<executions>
							<execution>
								<id>copy-public-content</id>
								<phase>process-classes</phase>
								<goals>
									<goal>copy-resources</goal>
								</goals>
								<configuration>
									<outputDirectory>${basedir}/public</outputDirectory>
									<encoding>UTF-8</encoding>
									<resources>
										<resource>
											<directory>${project.build.directory}/soaggyshop-src/_public</directory>
											<includes>
												<include>**/*.*</include>
												<include>**/*.json</include>
												<include>**/*.css</include>
												<include>**/*.scc</include>
												<include>**/*.scss</include>												
												<include>**/assets/**</include>
											</includes>
										</resource>
									</resources>
								</configuration>
							</execution>
							<execution>
								<id>copy-soaggyShopSrc</id>
								<phase>process-classes</phase>
								<goals>
									<goal>copy-resources</goal>
								</goals>
								<configuration>
									<outputDirectory>${basedir}/src</outputDirectory>
									<encoding>UTF-8</encoding>
									<resources>
										<resource>
											<directory>${project.build.directory}/soaggyshop-src/_src</directory>
											<includes>
												<include>**/*</include>
												<include>**/*.js</include>												
												<include>**/*.json</include>
												<include>**/*.css</include>
												<include>**/*.scc</include>
												<include>**/*.scss</include>												
											</includes>
										</resource>
									</resources>
								</configuration>
							</execution>
						</executions>
					</plugin>
				</plugins>
			</build>
		</profile>
```


## Mobile ecommerce UI

Add following fragment inside pom.xml mobile module

```

```


## Build

```
> mvn clean install -P install-soaggyshop-web
```

## Run ecommerce applications

Web : 
````
> REACT_APP_BACKEND_HOST=http://localhost:8080/jsoagger/serv/core npm start
````

And browse the application at url: http://localhost:3000/#/login

Mobile:
```
>
```


