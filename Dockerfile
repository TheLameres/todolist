FROM bellsoft/liberica-openjre-debian:11.0.11-9
LABEL location="/"
EXPOSE 8080/tcp
COPY "target/todolist-0.0.1-SNAPSHOT.jar" "/opt/app.jar"
ENTRYPOINT [ "java", "-jar", "/opt/app.jar" ]