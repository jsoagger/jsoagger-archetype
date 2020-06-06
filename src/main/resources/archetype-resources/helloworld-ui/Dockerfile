FROM jsoagger/base

LABEL maintainer="NEXITIA Technologies"
LABEL description="HelloWorld"

EXPOSE 8080

COPY target/helloworld-ui-sb.jar  /app/app.jar 

USER admin

ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:MaxRAMFraction=1","-XshowSettings:vm","-jar","/app/app.jar"]
CMD ["--spring.config.location=file:/spring-config/application.properties"]
