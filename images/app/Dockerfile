# Begin with the 4.1.1-web version of the official Glassfish image.
FROM glassfish/server:4.1.1

MAINTAINER Rob Benton <rob.benton@arcourts.gov>


# We replace the jboss-logging.jar included with glassfish to fix a bug
# working with hibernate 5.
RUN rm /glassfish4/glassfish/modules/jboss-logging.jar
COPY jboss-logging-3.3.0.Final.jar /glassfish4/glassfish/modules


# Copy the MySQL JDBC driver to the glassfish install directory.
ENV MYSQL=mysql-connector-java-5.1.40-bin.jar
COPY $MYSQL /glassfish4/glassfish/domains/domain1/lib


# Start glassfish and install dock-app
ENV APP=dock-app-1.0-SNAPSHOT.ear \
  ASADMIN=/glassfish4/glassfish/bin/asadmin \
  PASSWORD=glassfish \
  USER=admin

COPY $APP /
RUN $ASADMIN start-domain && \
  echo "AS_ADMIN_PASSWORD=${PASSWORD}" > /tmp/pass && \
  $ASADMIN --user ${USER} --passwordfile /tmp/pass deploy --name dock-app-1.0 /$APP && \
  rm /tmp/pass