FROM ubuntu:16.04
LABEL "Maintainer"="Pictolearn"

#Verify the version exists here or else change it: http://apache.rediris.es/tomcat/tomcat-7
ENV TOMCAT_VER 7.0.99

# Run an update to get all the security patches
RUN apt-get -y update && \
    apt-get -y upgrade

#Install curl/vim incase you want to debug.
#VIM - vi editor
#curl is a utility which acts as http client which can be run from the unix prompt.
RUN apt-get -y install software-properties-common && \
    apt-get -y install curl wget vim && \
    apt-get update -y

# Just in case:
# -Accept the license (type 'Y'ES, to accept that)
# -RUN echo "oracle-java7-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections

# Install OPEN JDK - without any 'Y'es typing agreement
RUN add-apt-repository ppa:openjdk-r/ppa && \
    apt-get update -y && \
    apt-get install -y openjdk-7-jdk && \
    apt-get update -y

ENV JAVA_HOME /usr/lib/jvm/java-1.7.0-openjdk-amd64

# Get Tomcat
RUN wget --quiet --no-cookies http://apache.rediris.es/tomcat/tomcat-7/v${TOMCAT_VER}/bin/apache-tomcat-${TOMCAT_VER}.tar.gz -O /tmp/tomcat.tgz && \
    tar xzvf /tmp/tomcat.tgz -C /opt && \
    mv /opt/apache-tomcat-${TOMCAT_VER} /opt/tomcat && \
    rm /tmp/tomcat.tgz && \
    rm -rf /opt/tomcat/webapps/examples && \
    rm -rf /opt/tomcat/webapps/docs && \
    rm -rf /opt/tomcat/webapps/ROOT && \
    apt-get update -y

RUN echo 'process concluded'
