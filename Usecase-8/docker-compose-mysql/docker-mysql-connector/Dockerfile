FROM pauloportfolio/ubuntu-jdk8
LABEL "Maintainer"="PauloDockerHub"
ADD . /usr/local/docker-mysql-connector
RUN cd /usr/local/docker-mysql-connector && mvn assembly:assembly
CMD ["java", "-cp", "/usr/local/docker-mysql-connector/target/docker-mysql-connector-1.0.0-SNAPSHOT-jar-with-dependencies.jar", "org.pictolearn.docker.mysql.MySQLConnection"]

