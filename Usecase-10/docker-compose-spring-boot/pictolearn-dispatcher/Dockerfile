# use-case 05
FROM pictolearn/tomcat8-jdk8
MAINTAINER pictolearn

#install maven for debug
RUN apt-get -y install maven lsof

#Set TOMCAT: Catalina HOME and JAVA_OPTS
ENV CATALINA_BASE /opt/tomcat
ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Xss8192k -XX:PermSize=500m -XX:CMSInitiatingOccupancyFraction=50 -XX:+ExplicitGCInvokesConcurrent -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:NewRatio=1 -XX:SurvivorRatio=1  -Dorg.apache.cxf.JDKBugHacks.imageIO=false"
EXPOSE 8080
EXPOSE 8009
WORKDIR /opt/tomcat

#General procedures
ADD . /usr/local/pictolearn
RUN mkdir -p /usr/local/pictolearn/logs

RUN cd /usr/local/pictolearn && mvn clean package

RUN  cp /usr/local/pictolearn/target/pictolearn-dispatcher.war /opt/tomcat/webapps/router.war

#run TOMCAT
CMD ["/opt/tomcat/bin/catalina.sh","run"]

