FROM tomcat:10-jdk17-openjdk

RUN apt-get update

RUN apt-get install vim -y

RUN rm -rf /usr/local/tomcat/webapps/*

COPY ./build/libs/back-end-assignment-0.0.1.war /usr/local/tomcat/webapps/ROOT.war