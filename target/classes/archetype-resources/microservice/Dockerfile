# RHEL centos is our base image
FROM centos

LABEL maintainer="Ramilafananana VONJISOA"
LABEL mailTo="rmvonji@gmail.com"
LABEL image="helloworld/app-foundation-srv"
LABEL description="Foundation microservice for JSoagger quick start"
LABEL howToRun="Before running check the docker network on which the service is connected"


# install openjdk 11 and set JAVA_HOME
RUN yum install -y \
       java-11-openjdk \
       java-11-openjdk-devel


ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk/
ENV JAVA_VER 11


# Create all log directories
RUN mkdir -p /tmp/jsoagger/APP_TMP_HOME/INSTALL/Logs
RUN mkdir -p /tmp/jsoagger/APP_TMP_HOME/INSTALL/Batch_Logs
RUN mkdir -p /tmp/jsoagger/APP_TMP_HOME/INSTALL/Batch_Discarded
RUN mkdir -p /tmp/jsoagger/APP_TMP_HOME/Batch_Logs
RUN mkdir -p /tmp/jsoagger/APP_TMP_HOME/RemoteImport

RUN mkdir -p /spring-config/

# Adding a /tmp volume. Docker will map this to to /var/lib/docker on the host system
VOLUME /tmp

# make it accessible through this port
EXPOSE 8080

COPY target/microservice-1.0.0-SNAPSHOT-sb.jar  /app/app.jar

ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:MaxRAMFraction=1","-XshowSettings:vm","-jar","/app/app.jar"]

CMD ["--spring.config.location=file:/spring-config/application.properties"]

