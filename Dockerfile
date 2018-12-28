FROM openjdk:11
VOLUME /tmp
WORKDIR /service
COPY target/*.jar service.jar
ENTRYPOINT exec java \
-Djava.security.egd=file:/dev/./urandom \
--add-modules java.se --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/sun.nio.ch=ALL-UNNAMED --add-opens java.management/sun.management=ALL-UNNAMED --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED \
$JAVA_OPTS \
-jar service.jar