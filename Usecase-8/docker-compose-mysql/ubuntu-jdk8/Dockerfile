FROM ubuntu:16.04
MAINTAINER pictolearn

RUN apt-get update && apt-get install -y python-software-properties software-properties-common
RUN apt-get install -y iputils-ping

RUN apt-get install -y vim
RUN apt-get install -y mysql-client

RUN apt-get update && apt-get install -y openjdk-8-jdk maven