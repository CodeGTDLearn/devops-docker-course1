FROM pauloportfolio/jdk7:latest

#COPY docker-spring-mvc.war /opt/tomcat/webapps/ROOT.war

ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Xss8192k -XX:PermSize=500m -XX:CMSInitiatingOccupancyFraction=50 -XX:+ExplicitGCInvokesConcurrent -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:NewRatio=1 -XX:SurvivorRatio=1  -Dorg.apache.cxf.JDKBugHacks.imageIO=false"

EXPOSE 8080
EXPOSE 8009

VOLUME "/opt/tomcat/webapps"
VOLUME "/opt/tomcat/logs"

WORKDIR /opt/tomcat

CMD ["/opt/tomcat/bin/catalina.sh", "run"]
