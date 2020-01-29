FROM pauloportfolio/ubuntu16-njdk8
ENV PICTOLEARN_HOME /usr/local/pictolearn
ADD . /usr/local/pictolearn
RUN mkdir -p /usr/local/pictolearn/logs
RUN cd /usr/local/pictolearn && mvn clean package
CMD ["java","-jar","-DlogPath=/usr/local/pictolearn/logs","/usr/local/pictolearn/target/pictolearn-web-1.0.0-SNAPSHOT.jar"]
