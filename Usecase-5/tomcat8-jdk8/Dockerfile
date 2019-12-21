FROM ubuntu:16.04

#Author of the file
MAINTAINER "paulo"="pauleta"

#Download the latest version of 8.5.x
# Verify the version exists here or else change it: http://apache.mirrors.tds.net/tomcat/tomcat-8/
ENV TOMCAT_VER 8.5.50

# Run an update to get all the security patches
RUN apt-get update && apt-get -y upgrade && \
    apt-get -y install software-properties-common && \
    apt-get -y update && \
    apt-get -y install curl vim wget && \
    apt-get -y update

# Just in case:
# -Accept the license (type 'Y'ES, to accept that)
# -RUN echo "oracle-java7-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections
RUN add-apt-repository ppa:openjdk-r/ppa && \
    apt-get update -y && \
    apt-get install -y openjdk-8-jdk && \
    apt-get -y install maven lsof && \
    apt-get update -y

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk-amd64

# Get Tomcat
# notice that i can add the same RUN command across multiple lines
RUN wget --quiet --no-cookies http://apache.mirrors.tds.net/tomcat/tomcat-8/v${TOMCAT_VER}/bin/apache-tomcat-${TOMCAT_VER}.tar.gz -O /tmp/tomcat.tgz && \
    tar xzvf /tmp/tomcat.tgz -C /opt && \
    mv /opt/apache-tomcat-${TOMCAT_VER} /opt/tomcat && \
    rm /tmp/tomcat.tgz  && \
    rm -rf /opt/tomcat/webapps/examples && \
    rm -rf /opt/tomcat/webapps/docs && \
    rm -rf /opt/tomcat/webapps/ROOT

RUN echo "PROCESS CONCLUDED - JDK 8"
