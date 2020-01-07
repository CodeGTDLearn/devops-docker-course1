FROM pauloportfolio/jdk7:latest

COPY docker-spring-mvc.war /opt/tomcat/webapps/ROOT.war

#Set Catalina HOME and JAVA_OPTS
ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Xss8192k -XX:PermSize=500m -XX:CMSInitiatingOccupancyFraction=50 -XX:+ExplicitGCInvokesConcurrent -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:NewRatio=1 -XX:SurvivorRatio=1  -Dorg.apache.cxf.JDKBugHacks.imageIO=false"

EXPOSE 8080
EXPOSE 8009

# Need a war file from the host to be mounted you can do so here.
# This instruction tells docker that content in those directories
# does not go in images and
# can be accessed from other containers using the --volumes-from command line parameter.
# You have to run the container using -v /path/on/host:/path/in/container
# to access directories from the host.
VOLUME "/opt/tomcat/webapps"
VOLUME "/opt/tomcat/logs"

WORKDIR /opt/tomcat

CMD ["/opt/tomcat/bin/catalina.sh", "run"]

#docker container run -itd --name jdk8 -P
#-v C:/Users/SERVIDOR/Projects/docker-tutorial/Usecase-7/logs:/opt/tomcat/logs
#-v C:/Users/SERVIDOR/Projects/docker-tutorial/Usecase-7/webapps:/opt/tomcat/webapps
#jdk8
