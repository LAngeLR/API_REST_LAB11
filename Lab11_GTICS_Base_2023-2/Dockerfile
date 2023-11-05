FROM openjdk:17.0.2-jdk
VOLUME /tmp
EXPOSE 8085
ADD ./target/Base_Proyecto_Texhnolyze-0.0.1-SNAPSHOT.jar proyecto.jar
ADD src/main/resources/static/adroit-metric-402916-a29ca20754fc.json /tmp/credenciales.json
ENTRYPOINT ["java","-jar","proyecto.jar"]