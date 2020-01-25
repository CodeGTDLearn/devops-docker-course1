FROM pauloportfolio/ubuntu-jdk8
ADD . /usr/local/pictolearn
RUN cd /usr/local/pictolearn && mvn clean package
CMD ["java","-jar","-DlogPath=/usr/local/pictolearn", "/usr/local/pictolearn/target/docker-compose-pictolearn-1.0.0-SNAPSHOT.jar"]
