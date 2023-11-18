FROM openjdk:17-oracle
EXPOSE 8080 9251

ADD target/authentication-server.jar authentication-server.jar
ADD docker-image-debug-entrypoint.sh docker-image-debug-entrypoint.sh

ENTRYPOINT ["sh", "/docker-image-debug-entrypoint.sh"]
