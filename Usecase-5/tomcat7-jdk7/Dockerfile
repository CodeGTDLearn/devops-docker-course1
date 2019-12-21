FROM centos:latest
LABEL "Maintainer"="Paulao avancando bem!!!"

#Verify the version exists here or else change it: http://apache.rediris.es/tomcat/tomcat-7
ENV TOMCAT_PAULO 7.0.99

RUN apt-get -y update && \
    apt-get -y upgrade && \
    apt-get -y install && \
    apt-get -y install curl wget vim

# CASE OF ACCEPT ANY AGREEMENT/CONTRACT - TYPE SOME KEY
# to Accept the license/ANYTHING
# RUN echo "oracle-java7-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections

# ENV JAVA_HOME /usr/lib/jvm/java-7-oracle
RUN add-apt-repository ppa:openjdk-r/ppa && \
    apt-get update -y && \
    apt-get install -y openjdk-7-jdk && \
    apt-get update -y

ENV JAVA_HOME /usr/lib/jvm/java-1.7.0-openjdk-amd64

RUN wget --quiet --no-cookies http://apache.rediris.es/tomcat/tomcat-7/v${TOMCAT_PAULO}/bin/apache-tomcat-${TOMCAT_PAULO}.tar.gz -O /tmp/tomcat.tgz && \
    tar xzvf /tmp/tomcat.tgz -C /opt && \
    mv /opt/apache-tomcat-${TOMCAT_PAULO} /opt/tomcat && \
    rm /tmp/tomcat.tgz && \
    rm -rf /opt/tomcat/webapps/examples && \
    rm -rf /opt/tomcat/webapps/docs && \
    rm -rf /opt/tomcat/webapps/ROOT
