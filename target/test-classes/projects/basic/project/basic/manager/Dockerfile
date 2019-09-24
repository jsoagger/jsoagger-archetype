FROM centos

LABEL maintainer="Ramilafananana VONJISOA"
LABEL mailTo="rmvonji@gmail.com"
LABEL image="helloworld/starter-manager"
LABEL description="Is a shell for managing myproject servers microservices"


RUN yum install -y \
       java-11-openjdk \
       java-11-openjdk-devel

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk/
ENV JAVA_VER 11

ARG MANAGED_BACKEND_REST_URL

#Adding a /tmp volume. Docker will map this to to /var/lib/docker on the host system
VOLUME /tmp

# Create folder inside which we will work
RUN mkdir /myproject-cli/
RUN mkdir /myproject-cli/inputs
RUN mkdir /myproject-cli/works


# Copy script for running the CLI
# variables SPRING_APPLICATION_JSON and BACKEND_REST_URL are use in run.sh
COPY src/utils/runcli.sh /runcli.sh
COPY src/utils/entry-point.sh /myproject-cli/
RUN chmod +x /myproject-cli/entry-point.sh
RUN chmod +x /runcli.sh


COPY target/starter-manager-sb.jar /myproject-cli/app.jar

ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /myproject-cli/app.jar --ep.remote.host=http://myproject-rest-service:8080/myproject" ]
#ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /myproject-cli/app.jar --ep.remote.host=http://myproject-rest-service:8080/myproject" ]


ENTRYPOINT [ "/myproject-cli/entry-point.sh"]

