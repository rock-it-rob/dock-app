# Begin with the debian image.
FROM debian:latest

MAINTAINER Rob Benton <rob.benton@arcourts.gov>


# Set passwords first so apt will not prompt for them.
RUN echo "mysql-server-5.5 mysql-server/root_password password root" | debconf-set-selections && \
  echo "mysql-server-5.5 mysql-server/root_password_again password root" | debconf-set-selections


# Install needed apps, tools, and utilities
# 
RUN apt-get update && apt-get install -y \
  mysql-server-5.5 \
  unzip


# Install the JRE.
# 
ENV JRE=jre-8u112-linux-x64.tar.gz
RUN mkdir /usr/local/java
COPY $JRE /usr/local/java
RUN cd /usr/local/java && \
  tar zxvf $JRE && \
  rm $JRE


# Install Glassfish
#
# We replace the jboss-logging.jar included with glassfish to fix a bug
# working with hibernate 5.
ENV GLASSFISH=glassfish-4.1.1-web.zip
COPY $GLASSFISH /opt
RUN cd /opt && \
  unzip $GLASSFISH && \
  rm $GLASSFISH && \
  rm /opt/glassfish4/glassfish/modules/jboss-logging.jar
COPY jboss-logging-3.3.0.Final.jar /opt/glassfish4/glassfish/modules


# Setup MySQL
#
# Turn off autocommit
RUN cd /etc/mysql && \
  sed -e 's/^\(\[mysqld\]\)/\1\nautocommit=0/' my.cnf > /tmp/my.cnf && \
  mv /tmp/my.cnf .
# Create the table to be used by the application.
ENV SQL=create_table.sql
COPY $SQL /tmp
RUN /etc/init.d/mysql start && \
  mysql --host=localhost --user=root --password=root < /tmp/$SQL && \
  rm /tmp/$SQL





## install dock-app


# Ports to open:
#   Glassfish: 8080, 8181
#   MySQL: 3306
#   
EXPOSE 8080 8181 3306


# Create a mysql user and a gfish user
# and use them to start the services.

